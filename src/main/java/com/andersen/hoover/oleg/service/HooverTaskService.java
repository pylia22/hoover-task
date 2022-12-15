package com.andersen.hoover.oleg.service;

import com.andersen.hoover.oleg.entity.Coordinates;
import com.andersen.hoover.oleg.entity.Direction;
import com.andersen.hoover.oleg.entity.HooverTask;
import com.andersen.hoover.oleg.entity.Room;
import com.andersen.hoover.oleg.repository.HooverTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class HooverTaskService {

    private static final Random random = new Random();
    private final HooverTaskRepository hooverTaskRepository;

    public HooverTask save(HooverTask hooverTask) {
        hooverTask.setDirtyCoordinates(getRandomCoordinates(hooverTask.getRoom()));
        hooverTask.setInstructions(getRandomInstructions());
        hooverTaskRepository.save(hooverTask);
        return hooverTask;
    }

    private List<Coordinates> getRandomCoordinates(Room room) {
        int roomLength = room.getLength();
        int roomWidth = room.getWidth();
        int dirtyPatches = random.nextInt(roomLength * roomWidth);
        List<Coordinates> dirtyCoordinates = new ArrayList<>();

        for (int i = 0; i < dirtyPatches; i++) {
            Coordinates coordinates = new Coordinates();
            coordinates.setxAxis(random.nextInt(roomLength - 1) + 1);
            coordinates.setyAxis(random.nextInt(roomWidth - 1) + 1);
            dirtyCoordinates.add(coordinates);
        }
        return dirtyCoordinates;
    }

    private String getRandomInstructions() {
        StringBuilder instructions = new StringBuilder();
        Direction[] directions = Direction.values();
        int directionLength = directions.length;

        for (int i = 0; i < 10; i++) {
            instructions.append(directions[random.nextInt(directionLength)]);
        }
        return instructions.toString();
    }
}
