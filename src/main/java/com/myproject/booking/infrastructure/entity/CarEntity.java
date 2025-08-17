package com.myproject.booking.infrastructure.entity;

import com.myproject.booking.domain.resource.FuelType;
import com.myproject.booking.domain.resource.Transmission;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @Column(name = "id")
    private String idCar;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "marque")
    private String marque;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "annee")
    private String annee;

    @Column(name = "numero_matriculation",unique = true)
    private String licencePlate;

    @Column(name = "nbre_place")
    private int nbrePlace;

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(name = "fuelType")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
}
