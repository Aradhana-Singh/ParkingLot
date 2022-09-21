package com.demo.parking.controller;

import com.demo.parking.model.entity.VehicleType;
import com.demo.parking.model.entity.VehicleVM;
import com.demo.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/slot-availability")
    public Boolean getSlotAvailability(@RequestParam VehicleType vehicleType) throws AccountNotFoundException {
        return parkingService.slotsAvailable(vehicleType);
    }

    @PostMapping("/add-vehicle")
    public void addVehicle(@RequestBody VehicleVM vehicleVM) throws AccountNotFoundException {
        parkingService.addVehicle(vehicleVM);
    }

    @PostMapping("/add-payment")
    public void addPayment(@RequestParam Long vehicleId) throws AccountNotFoundException {
        parkingService.payment(vehicleId);
    }

    @GetMapping("/exit-status")
    public String exitVehicleStatus(@RequestParam Long vehicleId) throws AccountNotFoundException {
        return parkingService.exit(vehicleId);
    }
}
