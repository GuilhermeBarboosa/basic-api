package br.com.guilherme.basic.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailOutput {
    @NotBlank String emailUsuario;
    @NotBlank String assunto;
    @NotBlank String mensagem;
}
