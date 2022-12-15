package com.andersen.hoover.oleg.service;

import com.andersen.hoover.oleg.entity.*;
import com.andersen.hoover.oleg.repository.HooverTaskResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HooverTaskResultService {

    private final HooverTaskResultRepository hooverTaskResultRepository;
    private Integer cleanCount;
    private Integer marker;

    public HooverTaskResultService(HooverTaskResultRepository hooverTaskResultRepository) {
        this.hooverTaskResultRepository = hooverTaskResultRepository;
    }

    public void save(HooverTaskResult hooverResult) {
        hooverTaskResultRepository.save(hooverResult);
    }

    public HooverTaskResult doCleaning(HooverTask hooverTask) {
        marker = 0;
        cleanCount = 0;
        moveHover(hooverTask.getRoom(), hooverTask.getHooverCoordinates(),
                hooverTask.getDirtyCoordinates(), hooverTask.getInstructions());

        return new HooverTaskResult(cleanCount, hooverTask.getInstructions());
    }

    private void moveHover(Room roomSize, Coordinates currentHoverCoordinates,
                           List<Coordinates> dirtyCoordinates, String instructions) {
        Direction direction = getDirectionByInstruction(instructions);
        Coordinates plannedCoordinates = getPlannedCoordinates(currentHoverCoordinates, direction);

        if (!hasTouchedWall(plannedCoordinates, roomSize)) {
            currentHoverCoordinates = plannedCoordinates;
            if (isDirty(currentHoverCoordinates, dirtyCoordinates)) {
                cleanCount++;
                dirtyCoordinates.remove(plannedCoordinates);
            }
        }

        if (instructions.length() - 1 > marker) {
            marker++;
            moveHover(roomSize, currentHoverCoordinates, dirtyCoordinates, instructions);
        }
    }

    private boolean isDirty(Coordinates currentHoverCoordinates, List<Coordinates> dirtyCoordinates) {
        return dirtyCoordinates.contains(currentHoverCoordinates);
    }

    private boolean hasTouchedWall(Coordinates plannedCoordinates, Room roomSize) {
        return plannedCoordinates.getXAxis() == roomSize.getLength() ||
                plannedCoordinates.getYAxis() == roomSize.getWidth() ||
                plannedCoordinates.getXAxis() < 0 ||
                plannedCoordinates.getYAxis() < 0;
    }

    private Coordinates getPlannedCoordinates(Coordinates hoverCoordinates, Direction direction) {
        Coordinates plannedCoordinates = new Coordinates();
        plannedCoordinates.setxAxis(hoverCoordinates.getXAxis() + direction.getXShift());
        plannedCoordinates.setyAxis(hoverCoordinates.getYAxis() + direction.getYShift());

        return plannedCoordinates;
    }

    private Direction getDirectionByInstruction(String instruction) {
        String point = String.valueOf(instruction.charAt(marker));
        return Direction.valueOf(point);
    }
}
