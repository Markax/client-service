package com.practicaWipay.client_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends MainTable {
    private String name;
    private String surname;
    private String cifNifNie;
    private String phone;
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email format")
    private String email;
}
