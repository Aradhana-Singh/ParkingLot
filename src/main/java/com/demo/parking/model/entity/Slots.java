package com.demo.parking.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class Slots {
    @Id
    private Long id;
    private int countTwoWheeler;
    private int countFourWheeler;
}
