package com.andersen.hoover.oleg.entity;


import lombok.*;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Room {

    private int length;
    private int width;
}
