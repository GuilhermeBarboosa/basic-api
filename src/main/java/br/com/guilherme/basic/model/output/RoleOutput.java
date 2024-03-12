package br.com.guilherme.basic.model.output;

import br.com.guilherme.basic.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOutput {
    private Long id;
    private String role;

    public RoleOutput(Role role){
        this.id = role.getId();
        this.role = role.getRole();
    }

}
