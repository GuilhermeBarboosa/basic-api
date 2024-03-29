package br.com.guilherme.basic.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "permissao")
public class Permissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_permissao")
    @SequenceGenerator(sequenceName = "id_seq_permissao", allocationSize = 1, name = "id_seq_permissao")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "permissao", nullable = false)
    private String permissao;
}