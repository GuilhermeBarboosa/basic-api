package com.guilherme.basicapi.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {

    private String name;

    private String cpf;

    private String email;

    private String password;

    private Long role;
}
