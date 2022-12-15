package com.andersen.hoover.oleg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    private int xAxis;
    private int yAxis;

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }
}
