package com.guilherme.basicapi.model.output;

import com.guilherme.basicapi.model.defaults.DefaultEntityDTO;
import com.guilherme.basicapi.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput extends DefaultEntityDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String password;

    private Long idRole;
    private String role;

    public UserOutput(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.idRole = user.getRole().getId();
        this.role = user.getRole().getRole();
        this.setActived(user.getActived());
        this.setCreated(user.getCreated());
        this.setUpdated(user.getUpdated());
    }

}
