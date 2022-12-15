package com.andersen.hoover.oleg.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HooverTaskResult {

    @Id
    @Column(name = "hoover_task_id")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "hoover_task_id")
    private HooverTask hooverTask;

    @Column
    private int cleanCounter;

    @Column
    private String directionHistory;

    public HooverTaskResult(int cleanCounter, String directionHistory) {
        this.cleanCounter = cleanCounter;
        this.directionHistory = directionHistory;
    }
}
