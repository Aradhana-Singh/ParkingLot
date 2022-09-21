package com.demo.parking.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@ToString
public class VehicleInfo {
    @Id
    @GeneratedValue
    private Long vehicleId;
    private VehicleType type;
    private String vehicleNumber;
    private String ownerName;
    private String phoneNo;
    private Boolean paymentStatus = false;
}
