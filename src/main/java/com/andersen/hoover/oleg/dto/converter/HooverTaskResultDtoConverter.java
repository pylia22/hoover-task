package com.andersen.hoover.oleg.dto.converter;

import com.andersen.hoover.oleg.dto.HooverTaskResultDto;
import com.andersen.hoover.oleg.entity.HooverTaskResult;
import org.springframework.stereotype.Component;

@Component
public class HooverTaskResultDtoConverter {

    public HooverTaskResultDto toDto(HooverTaskResult hooverTaskResult) {
        return new HooverTaskResultDto(hooverTaskResult.getCleanCounter(), hooverTaskResult.getDirectionHistory());
    }
}
