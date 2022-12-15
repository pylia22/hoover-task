package com.andersen.hoover.oleg.dto.converter;

import com.andersen.hoover.oleg.dto.HooverTaskResultDto;
import com.andersen.hoover.oleg.entity.HooverTaskResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HooverTaskResultDtoConverterTest {
    HooverTaskResultDtoConverter hooverTaskResultDtoConverter = new HooverTaskResultDtoConverter();

    @Test
    void shouldBeEqualAfterToDtoConverter() {
        HooverTaskResultDto expected = new HooverTaskResultDto(3, "RLUDRLUDRL");

        HooverTaskResult hooverTaskResult = new HooverTaskResult(3, "RLUDRLUDRL");

        HooverTaskResultDto actual = hooverTaskResultDtoConverter.toDto(hooverTaskResult);

        assertEquals(expected.getCleanCounter(), actual.getCleanCounter());
        assertEquals(expected.getDirectionHistory(), actual.getDirectionHistory());
    }
}