package com.andersen.hoover.oleg.dto.converter;

import com.andersen.hoover.oleg.dto.HooverTaskDto;
import com.andersen.hoover.oleg.entity.Coordinates;
import com.andersen.hoover.oleg.entity.HooverTask;
import com.andersen.hoover.oleg.entity.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HooverTaskDtoConverterTest {
    private final HooverTaskDtoConverter hooverTaskDtoConverter = new HooverTaskDtoConverter();

    @Test
    void shouldBeEqualAfterFromDtoConverter() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(5);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        HooverTask expected = new HooverTask();
        expected.setRoom(new Room(10, 10));
        expected.setHooverCoordinates(new Coordinates(5, 5));

        HooverTask actual = hooverTaskDtoConverter.fromDto(hooverTaskDto);

        assertEquals(expected.getRoom().getLength(), actual.getRoom().getLength());
        assertEquals(expected.getRoom().getWidth(), actual.getRoom().getWidth());
        assertEquals(expected.getHooverCoordinates().getXAxis(), actual.getHooverCoordinates().getXAxis());
        assertEquals(expected.getHooverCoordinates().getYAxis(), actual.getHooverCoordinates().getYAxis());
    }
}