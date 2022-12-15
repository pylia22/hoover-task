package com.andersen.hoover.oleg.service;

import com.andersen.hoover.oleg.entity.Coordinates;
import com.andersen.hoover.oleg.entity.HooverTask;
import com.andersen.hoover.oleg.entity.Room;
import com.andersen.hoover.oleg.repository.HooverTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HooverTaskServiceTest {

    @Mock
    private HooverTaskRepository hooverTaskRepository;
    private HooverTaskService hooverTaskService;

    @BeforeEach
    void setUp() {
        hooverTaskService = new HooverTaskService(hooverTaskRepository);
    }

    @Test
    void shouldGenerateDifferentFields() {
        HooverTask hooverTask = new HooverTask();
        hooverTask.setRoom(new Room(10, 10));
        hooverTask.setHooverCoordinates(new Coordinates(5, 5));

        String instruction1 = hooverTaskService.save(hooverTask).getInstructions();
        String instruction2 = hooverTaskService.save(hooverTask).getInstructions();
        List<Coordinates> coordinates1 = hooverTaskService.save(hooverTask).getDirtyCoordinates();
        List<Coordinates> coordinates2 = hooverTaskService.save(hooverTask).getDirtyCoordinates();

        assertNotEquals(instruction1, instruction2);
        assertNotEquals(coordinates1, coordinates2);
    }

    @Test
    void shouldGenerateInstructionSizeEqualTen() {
        HooverTask hooverTask = new HooverTask();
        hooverTask.setRoom(new Room(10, 10));
        hooverTask.setHooverCoordinates(new Coordinates(5, 5));

        int actualInstructionSize = hooverTaskService.save(hooverTask).getInstructions().length();

        assertEquals(10, actualInstructionSize);
    }

    @Test
    void shouldCheckIfDirtyCoordinatesSizeNotBiggerRoomSize() {
        HooverTask hooverTask = new HooverTask();
        hooverTask.setRoom(new Room(10, 10));
        hooverTask.setHooverCoordinates(new Coordinates(5, 5));

        int roomSize = hooverTask.getRoom().getLength() * hooverTask.getRoom().getWidth();

        int actualDirtySize = hooverTaskService.save(hooverTask).getDirtyCoordinates().size();

        assertThat(actualDirtySize).isLessThan(roomSize);
    }

    @Test
    void shouldCheckIfDirtyCoordinatesInsideTheRoom() {
        HooverTask hooverTask = new HooverTask();
        hooverTask.setRoom(new Room(10, 10));
        hooverTask.setHooverCoordinates(new Coordinates(5, 5));

        int roomXAxis = hooverTask.getRoom().getLength();
        int roomYAxis = hooverTask.getRoom().getWidth();

        int actualDirtyXAxis = hooverTaskService.save(hooverTask).getDirtyCoordinates().stream().mapToInt(Coordinates::getXAxis).max().orElseThrow();
        int actualDirtyYAxis = hooverTaskService.save(hooverTask).getDirtyCoordinates().stream().mapToInt(Coordinates::getYAxis).max().orElseThrow();

        assertThat(actualDirtyXAxis).isLessThan(roomXAxis);
        assertThat(actualDirtyYAxis).isLessThan(roomYAxis);
    }
}