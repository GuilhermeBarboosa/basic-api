package br.com.guilherme.basic.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "role_tela")
public class RoleTela {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_role_tela")
    @SequenceGenerator(sequenceName = "id_seq_role_tela", allocationSize = 1, name = "id_seq_role_tela")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tela", nullable = false)
    private Tela tela;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role", nullable = false)
    private Role role;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permissao", nullable = false)
    private Permissao permissao;
}