package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// for updating email
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDto {
    private String prevEmail;
    private String newEmail;
}
