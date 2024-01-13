package com.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "address")
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String houseNo;
    private String houseName;
    private String street;
    private String city;
    
    @OneToOne(mappedBy = "address")
    // below annotation usage: so that there wont be looping-> like in employee I'll have address in address we have employee and so on 
    @JsonBackReference
    private Employee employee;

    public Address(int id, String houseNo, String houseName, String street, String city) {
        this.id = id;
        this.houseNo = houseNo;
        this.houseName = houseName;
        this.street = street;
        this.city = city;
    }
    
}
