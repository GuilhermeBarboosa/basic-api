package br.com.guilherme.basic.model.output;

import br.com.guilherme.basic.model.entity.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoOutput {
    private Long id;
    private String situacao;

    public SituacaoOutput(Situacao situacao){
        this.id = situacao.getId();
        this.situacao = situacao.getSituacao();
    }

}
