package br.com.guilherme.basic.model.output;

import br.com.guilherme.basic.model.defaults.DefaultEntityDTO;
import br.com.guilherme.basic.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput extends DefaultEntityDTO {
    private Long id;
    private String name;
    private String email;
    private String password;


    private Long idRole;
    private String role;

    //PARTE DE CANDADITOS
    private String cpf;
    private Date data_de_nascimento;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String telefone;
    private String numero;
    private String complemento;
    private String estado;
    private String nome_responsavel;
    private String email_responsavel;
    private String celular_responsavel;

    public UserOutput(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.idRole = user.getRole().getId();
        this.role = user.getRole().getRole();
        this.data_de_nascimento = user.getData_de_nascimento();
        this.rua = user.getRua();
        this.cep = user.getCep();
        this.cidade = user.getCidade();
        this.bairro = user.getBairro();
        this.cpf = user.getCpf();
        this.telefone = user.getTelefone();
        this.numero = user.getNumero();
        this.complemento = user.getComplemento();
        this.estado = user.getEstado();
        this.nome_responsavel = user.getNome_responsavel();
        this.email_responsavel = user.getEmail_responsavel();
        this.celular_responsavel = user.getCelular_responsavel();
        this.setActived(user.getActived());
        this.setCreated(user.getCreated());
        this.setUpdated(user.getUpdated());
    }

}
