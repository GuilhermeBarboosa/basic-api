package br.com.guilherme.basic.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleTelaInput {
    private Long tela;
    private Long role;
    private Long permissao;
}
