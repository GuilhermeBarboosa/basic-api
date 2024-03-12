package br.com.guilherme.basic.model.output;

import br.com.guilherme.basic.model.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoOutput {
    private Long id;
    private String permissao;

    public PermissaoOutput(Permissao permissao){
        this.id = permissao.getId();
        this.permissao = permissao.getPermissao();
    }

}
