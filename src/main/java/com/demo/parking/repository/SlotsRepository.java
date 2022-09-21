package com.demo.parking.repository;

import com.demo.parking.model.entity.Slots;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotsRepository extends CrudRepository<Slots, Long> {
}
