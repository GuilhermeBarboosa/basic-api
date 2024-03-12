package br.com.guilherme.basic.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuantidadeAllOutput {
    @JsonProperty("qtdUser")
    private Integer qtd_usuarios;

    public QuantidadeAllOutput() {}
}
