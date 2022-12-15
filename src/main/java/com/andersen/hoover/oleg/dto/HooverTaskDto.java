package com.andersen.hoover.oleg.dto;

import com.andersen.hoover.oleg.annotations.PositionValidation;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@PositionValidation
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HooverTaskDto {

    @NotNull(message = "Room length must not be null")
    @Min(value = 1, message = "Room length must be greater than 0")
    private Integer roomLength;

    @NotNull(message = "Room width must not be null")
    @Min(value = 1, message = "Room width must be greater than 0")
    private Integer roomWidth;

    @NotNull(message = "Hoover X Axis coordinates must not be null")
    @Min(value = 1, message = "Hoover X Axis coordinates must be greater than 0")
    private Integer hooverXAxisCoordinates;

    @NotNull(message = "Hoover Y Axis coordinates must not be null")
    @Min(value = 1, message = "Hoover Y Axis coordinates must be greater than 0")
    private Integer hooverYAxisCoordinates;

}
