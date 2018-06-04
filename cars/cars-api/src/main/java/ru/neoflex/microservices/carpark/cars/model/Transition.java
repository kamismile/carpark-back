package ru.neoflex.microservices.carpark.cars.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transition")
public class Transition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "FROM_STATE")
    private States from;

    @Enumerated(EnumType.STRING)
    @Column(name = "TO_STATE")
    private States to;

    @Enumerated(EnumType.STRING)
    private Events event;
}