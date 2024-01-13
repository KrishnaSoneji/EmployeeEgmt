package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{
    Address findAddressByStreetAndCity(String street,String city);
}
