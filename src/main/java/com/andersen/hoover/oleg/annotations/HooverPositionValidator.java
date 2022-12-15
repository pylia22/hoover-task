package com.andersen.hoover.oleg.annotations;

import com.andersen.hoover.oleg.dto.HooverTaskDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HooverPositionValidator implements ConstraintValidator<PositionValidation, HooverTaskDto> {

    @Override
    public boolean isValid(HooverTaskDto value, ConstraintValidatorContext context) {
        boolean isValid = false;
        if (value.getRoomWidth() != null && value.getRoomLength() != null) {
            if (value.getHooverXAxisCoordinates() != null && value.getHooverXAxisCoordinates() <= value.getRoomLength() &&
                    value.getHooverYAxisCoordinates() != null && value.getHooverYAxisCoordinates() <= value.getRoomWidth()) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public void initialize(PositionValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
