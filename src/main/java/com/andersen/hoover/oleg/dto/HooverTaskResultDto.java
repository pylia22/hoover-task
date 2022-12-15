package com.andersen.hoover.oleg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HooverTaskResultDto {
    private int cleanCounter;
    private String directionHistory;
}
