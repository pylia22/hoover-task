package com.andersen.hoover.oleg.dto.converter;

import com.andersen.hoover.oleg.dto.HooverTaskDto;
import com.andersen.hoover.oleg.entity.Coordinates;
import com.andersen.hoover.oleg.entity.HooverTask;
import com.andersen.hoover.oleg.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class HooverTaskDtoConverter {

    public HooverTask fromDto(HooverTaskDto hooverTaskDto) {
        return HooverTask.builder()
                .room(new Room(hooverTaskDto.getRoomLength(), hooverTaskDto.getRoomWidth()))
                .hooverCoordinates(new Coordinates(
                        hooverTaskDto.getHooverXAxisCoordinates(),
                        hooverTaskDto.getHooverYAxisCoordinates()))
                .build();
    }
}
