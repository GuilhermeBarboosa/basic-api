package br.com.guilherme.basic.model.output;

import br.com.guilherme.basic.model.entity.RoleTela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleTelaOutput {
    private Long id;
    private Long idTela;
    private Boolean unica;
    private String identificador;
    private String descricao;
    private Long idRole;
    private String role;
    private Long idPermissao;
    private String permissao;

    public RoleTelaOutput(RoleTela roleTela) {
        this.id = roleTela.getId();
        this.idTela = roleTela.getTela().getId();
        this.unica = roleTela.getTela().getUnica();
        this.identificador = roleTela.getTela().getIdentificador();
        this.descricao = roleTela.getTela().getDescricao();
        this.idRole = roleTela.getRole().getId();
        this.role = roleTela.getRole().getRole();
        this.idPermissao = roleTela.getPermissao().getId();
        this.permissao = roleTela.getPermissao().getPermissao();
    }

}
