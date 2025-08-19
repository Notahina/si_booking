package com.myproject.booking.infrastructure.entity;

import com.myproject.booking.domain.resource.RoomStatus;
import com.myproject.booking.domain.resource.RoomType;
import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    private Integer idRoom;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "resource_id")
    private ResourcesEntity resources;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "numero_chambre")
    private String roomNumber;

    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "etage")
    private int etage;

    @Column(name = "area")
    private double area;

    @Column(name = "price_night")
    private double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

}
