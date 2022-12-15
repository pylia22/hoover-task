package com.andersen.hoover.oleg.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class HooverTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "hoover_task_generator")
    private int id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "xAxis", column = @Column(name = "hover_coordinates_xAsis", nullable = false)),
            @AttributeOverride(name = "yAxis", column = @Column(name = "hover_coordinates_yAsis", nullable = false))
    })
    private Coordinates hooverCoordinates;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "length", column = @Column(name = "room_length", nullable = false)),
            @AttributeOverride(name = "width", column = @Column(name = "room_width", nullable = false))
    })
    private Room room;


    @ElementCollection
    @CollectionTable(name="dirty_coordinates")
    @AttributeOverrides({
            @AttributeOverride(name = "xAxis", column = @Column(name = "dirty_x_axis", nullable = false)),
            @AttributeOverride(name = "yAxis", column = @Column(name = "dirty_y_axis", nullable = false))
    })
    @Column(nullable = false)
    private List<Coordinates> dirtyCoordinates;

    @Column(nullable = false)
    private String instructions;


}
