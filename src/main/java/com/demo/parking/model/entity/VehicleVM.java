package com.demo.parking.model.entity;

import lombok.Data;

@Data
public class VehicleVM {
    private VehicleType type;
    private String vehicleNumber;
    private String ownerName;
    private String phoneNo;
}
