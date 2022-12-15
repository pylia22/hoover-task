package com.andersen.hoover.oleg.service;

import com.andersen.hoover.oleg.entity.Coordinates;
import com.andersen.hoover.oleg.entity.HooverTask;
import com.andersen.hoover.oleg.entity.HooverTaskResult;
import com.andersen.hoover.oleg.entity.Room;
import com.andersen.hoover.oleg.repository.HooverTaskResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HooverTaskResultServiceTest {

    @Mock
    private HooverTaskResultRepository hooverTaskResultRepository;
    private HooverTaskResultService hooverTaskResultService;

    @BeforeEach
    void setUp() {
        hooverTaskResultService = new HooverTaskResultService(hooverTaskResultRepository);
    }

    @Test
    public void shouldReturnSameHooverTaskResult() {
        List<Coordinates> dirtyCoordinates = new ArrayList<>();
        dirtyCoordinates.add(new Coordinates(4, 1));
        dirtyCoordinates.add(new Coordinates(3, 3));
        dirtyCoordinates.add(new Coordinates(1, 2));

        HooverTask hooverTask = new HooverTask(
                1,
                new Coordinates(3, 3),
                new Room(5, 5),
                dirtyCoordinates,
                "LRDRLDDLRL");

        HooverTaskResult expected = new HooverTaskResult(1, "LRDRLDDLRL");

        HooverTaskResult actual = hooverTaskResultService.doCleaning(hooverTask);

        assertEquals(expected.getCleanCounter(), actual.getCleanCounter());
        assertEquals(expected.getDirectionHistory(), actual.getDirectionHistory());
    }
}