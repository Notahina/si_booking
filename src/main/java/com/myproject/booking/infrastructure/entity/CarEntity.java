package com.myproject.booking.infrastructure.entity;

import com.myproject.booking.domain.resource.FuelType;
import com.myproject.booking.domain.resource.Transmission;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class CarEntity {
    @Id
    private Integer idCar;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "resource_id")
    private ResourcesEntity resources;

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

    @Column(name = "daily_price")
    private Double dailyPrice;

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
}
