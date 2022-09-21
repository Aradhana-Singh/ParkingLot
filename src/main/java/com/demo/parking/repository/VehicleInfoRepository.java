package com.demo.parking.repository;

import com.demo.parking.model.entity.VehicleInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInfoRepository extends CrudRepository<VehicleInfo, Long> {
}
