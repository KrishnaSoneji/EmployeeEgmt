package com.project.dto;

import com.project.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInfoDto {
    private String name;
    private String email;
    private Address address;
}
