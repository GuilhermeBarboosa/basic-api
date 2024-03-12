package br.com.guilherme.basic.model.entity;

import br.com.guilherme.basic.model.defaults.DefaultEntityUser;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User  extends DefaultEntityUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_user")
    @SequenceGenerator(sequenceName = "id_seq_user", allocationSize = 1, name = "id_seq_user")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 40)
    @NotNull
    @Column(name = "email", nullable = false, length = 40)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role", nullable = false)
    private Role role;


    //PARTE DE CANDADITOS
    @Size(max = 255)
    @Column(name = "cpf", nullable = false, length = 255)
    private String cpf;

    @Column(name = "data_de_nascimento", nullable = false, length = 255)
    private Date data_de_nascimento;

    @Size(max = 255)
    @Column(name = "rua", nullable = false, length = 255)
    private String rua;

    @Size(max = 255)
    @Column(name = "cep", nullable = false, length = 255)
    private String cep;

    @Size(max = 255)
    @Column(name = "bairro", nullable = false, length = 255)
    private String bairro;

    @Size(max = 255)
    @Column(name = "cidade", nullable = false, length = 255)
    private String cidade;

    @Size(max = 255)
    @Column(name = "telefone", nullable = false, length = 255)
    private String telefone;

    @Size(max = 255)
    @Column(name = "numero", nullable = false, length = 255)
    private String numero;

    @Size(max = 255)
    @Column(name = "complemento", nullable = false, length = 255)
    private String complemento;

    @Size(max = 255)
    @Column(name = "estado", nullable = false, length = 255)
    private String estado;

    @Size(max = 255)
    @Column(name = "nome_responsavel", nullable = false, length = 255)
    private String nome_responsavel;

    @Size(max = 255)
    @Column(name = "email_responsavel", nullable = false, length = 255)
    private String email_responsavel;

    @Size(max = 255)
    @Column(name = "celular_responsavel", nullable = false, length = 255)
    private String celular_responsavel;

    public boolean isAdmin(){
        if(this.role.getRole().equals("ADMIN")){
            return true;
        }else{
            return false;
        }
    }

}