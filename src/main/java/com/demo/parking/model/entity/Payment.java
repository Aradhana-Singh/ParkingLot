package com.demo.parking.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@ToString
public class Payment {
    @Id
    @GeneratedValue
    private int paymentId;

    @OneToOne
    @JoinColumn
    private VehicleInfo vehicleInfo;
}
