package br.com.guilherme.basic.model.output;

import br.com.guilherme.basic.model.entity.Tela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelaOutput {
    private Long id;
    private String identificador;
    private String descricao;
    private Boolean unica;

    public TelaOutput(Tela tela){
        this.id = tela.getId();
        this.identificador = tela.getIdentificador();
        this.descricao = tela.getDescricao();
        this.unica = tela.getUnica();
    }

}
