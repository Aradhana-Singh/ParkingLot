package com.demo.parking.service;

import com.demo.parking.model.entity.Payment;
import com.demo.parking.model.entity.Slots;
import com.demo.parking.model.entity.VehicleInfo;
import com.demo.parking.model.entity.VehicleType;
import com.demo.parking.model.entity.VehicleVM;
import com.demo.parking.repository.PaymentRepository;
import com.demo.parking.repository.SlotsRepository;
import com.demo.parking.repository.VehicleInfoRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class ParkingService {

    private static final int FOUR_WHEELER_SLOTS = 10;
    private static final int TWO_WHEELER_SLOTS = 20;
    private final SlotsRepository slotsRepository;
    private final VehicleInfoRepository vehicleInfoRepository;
    private final PaymentRepository paymentRepository;

    public ParkingService(SlotsRepository slotsRepository, VehicleInfoRepository vehicleInfoRepository,
                          PaymentRepository paymentRepository) {
        this.slotsRepository = slotsRepository;
        this.vehicleInfoRepository = vehicleInfoRepository;
        this.paymentRepository = paymentRepository;
    }

    public Boolean slotsAvailable(VehicleType vehicleType) throws AccountNotFoundException {

        Slots slots = slotsRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new AccountNotFoundException("Could not connect to db"));
        if (vehicleType.equals(VehicleType.TWO_WHEELER)) {
            return slots.getCountTwoWheeler() < TWO_WHEELER_SLOTS;
        } else {
            return slots.getCountFourWheeler() < FOUR_WHEELER_SLOTS;
        }
    }

    public void addVehicle(VehicleVM vehicleVM) throws AccountNotFoundException {
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setVehicleNumber(vehicleVM.getVehicleNumber());
        vehicleInfo.setOwnerName(vehicleVM.getOwnerName());
        vehicleInfo.setPhoneNo(vehicleVM.getPhoneNo());
        vehicleInfo.setType(vehicleVM.getType());
        vehicleInfoRepository.save(vehicleInfo);

        Slots slots = slotsRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new AccountNotFoundException("Could not connect to db"));
        if (vehicleVM.getType().equals(VehicleType.TWO_WHEELER)) {
            slots.setCountTwoWheeler(slots.getCountTwoWheeler() + 1);
        } else {
            slots.setCountFourWheeler(slots.getCountFourWheeler() + 1);
        }
        slotsRepository.save(slots);
    }

    public void payment(Long vehicleId) throws AccountNotFoundException {
        VehicleInfo vehicleInfo = vehicleInfoRepository.findById(vehicleId)
                .orElseThrow(() -> new AccountNotFoundException("No Vehicle id found"));
        vehicleInfo.setPaymentStatus(true);
        vehicleInfoRepository.save(vehicleInfo);
        Payment payment = new Payment();
        payment.setVehicleInfo(vehicleInfo);
        paymentRepository.save(payment);
    }

    public String exit(Long vehicleId) throws AccountNotFoundException {
        VehicleInfo vehicleInfo = vehicleInfoRepository.findById(vehicleId)
                .orElseThrow(() -> new AccountNotFoundException("No Vehicle id found"));
        if(!vehicleInfo.getPaymentStatus()) {
            return "paise bharo";
        }

        Slots slots = slotsRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new AccountNotFoundException("Could not connect to db"));
        if (vehicleInfo.getType().equals(VehicleType.TWO_WHEELER)) {
            slots.setCountTwoWheeler(slots.getCountTwoWheeler() - 1);
        } else {
            slots.setCountFourWheeler(slots.getCountFourWheeler() - 1);
        }

        slotsRepository.save(slots);
        return "jao bhai, dubara fir ana";
    }
}
