package com.guilherme.basicapi.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInput {
    
    private String email;

    private String senha;

}
