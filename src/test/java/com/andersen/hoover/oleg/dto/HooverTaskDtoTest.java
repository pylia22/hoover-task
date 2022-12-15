package com.andersen.hoover.oleg.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HooverTaskDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldNotFindViolations() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(5);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(0, actual.size());
    }

    @Test
    void shouldReturnErrorWhenLengthNotValid() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(0);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(5);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        String expected = "Invalid hoover position. Room length must be greater than 0";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(2, actual.size());
        assertEquals(expected, actual.stream().map(ConstraintViolation::getMessage).sorted()
                .collect(Collectors.joining(". ")));
    }

    @Test
    void shouldReturnErrorWhenLengthNull() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(5);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        String expected = "Invalid hoover position. Room length must not be null";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(2, actual.size());
        assertEquals(expected, actual.stream().map(ConstraintViolation::getMessage).sorted()
                .collect(Collectors.joining(". ")));
    }

    @Test
    void shouldReturnErrorWhenWidthNotValid() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(0);
        hooverTaskDto.setHooverXAxisCoordinates(5);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        String expected = "Invalid hoover position. Room width must be greater than 0";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(2, actual.size());
        assertEquals(expected, actual
                .stream()
                .map(ConstraintViolation::getMessage)
                .sorted()
                .collect(Collectors.joining(". ")));
    }

    @Test
    void shouldReturnErrorWhenWidthNull() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setHooverXAxisCoordinates(5);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        String expected = "Invalid hoover position. Room width must not be null";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(2, actual.size());
        assertEquals(expected, actual
                .stream()
                .map(ConstraintViolation::getMessage)
                .sorted()
                .collect(Collectors.joining(". ")));
    }

    @Test
    void shouldReturnErrorWhenHooverXAxisNotValid() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(0);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        String expected = "Hoover X Axis coordinates must be greater than 0";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(1, actual.size());
        assertEquals(expected, actual.iterator().next().getMessage());
    }

    @Test
    void shouldReturnErrorWhenHooverXAxisNull() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        String expected = "Hoover X Axis coordinates must not be null. Invalid hoover position";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(2, actual.size());
        assertEquals(expected, actual
                .stream()
                .map(ConstraintViolation::getMessage)
                .sorted()
                .collect(Collectors.joining(". ")));
    }

    @Test
    void shouldReturnErrorWhenHooverYAxisNull() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(5);

        String expected = "Invalid hoover position. Hoover Y Axis coordinates must not be null";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(2, actual.size());
        assertEquals(expected, actual
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(". ")));
    }

    @Test
    void invalidHooverYAxisCoordinatesShouldReturnError() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(5);
        hooverTaskDto.setHooverYAxisCoordinates(0);

        String expected = "Hoover Y Axis coordinates must be greater than 0";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(1, actual.size());
        assertEquals(expected, actual.iterator().next().getMessage());
    }

    @Test
    void shouldReturnErrorWhenXAxisHooverPositionInvalid() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(11);
        hooverTaskDto.setHooverYAxisCoordinates(5);

        String expected = "Invalid hoover position";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(1, actual.size());
        assertEquals(expected, actual.iterator().next().getMessage());
    }

    @Test
    void shouldReturnErrorWhenYAxisHooverPositionInvalid() {
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomLength(10);
        hooverTaskDto.setRoomWidth(10);
        hooverTaskDto.setHooverXAxisCoordinates(6);
        hooverTaskDto.setHooverYAxisCoordinates(11);

        String expected = "Invalid hoover position";

        Set<ConstraintViolation<HooverTaskDto>> actual = validator.validate(hooverTaskDto);

        assertEquals(1, actual.size());
        assertEquals(expected, actual.iterator().next().getMessage());
    }
}