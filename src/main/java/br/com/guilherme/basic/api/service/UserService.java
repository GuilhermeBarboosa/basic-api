package br.com.guilherme.basic.api.service;

import br.com.guilherme.basic.exception.SenhaInvalidaException;
import br.com.guilherme.basic.model.entity.Role;
import br.com.guilherme.basic.model.entity.User;
import br.com.guilherme.basic.model.input.UserInput;
import br.com.guilherme.basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public User save(UserInput userInput) {
        userInput.setPassword(passwordEncoder.encode(userInput.getPassword()));
        User user = modelMapper.map(userInput, User.class);
        user.setRole(roleService.findById(userInput.getRole()));
        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User não encontrada"));
    }

    public User updateById(Long id, UserInput userInput) {
        User user = findById(id);
        String password = "";
        if (userInput.getPassword() == null || userInput.getPassword().isEmpty()) {
            password = user.getPassword();
        } else{
            if (userInput.getPassword().equals(user.getPassword())) {
                password = userInput.getPassword();
            } else {
                password = passwordEncoder.encode(userInput.getPassword());
            }
        }


        user.setName(userInput.getName());
        user.setEmail(userInput.getEmail());
        user.setPassword(password);
        user.setRole(roleService.findById(userInput.getRole()));
        user.setBairro(userInput.getBairro());
        user.setCep(userInput.getCep());
        user.setCidade(userInput.getCidade());
        user.setRua(userInput.getRua());
        user.setCpf(userInput.getCpf());
        user.setData_de_nascimento(userInput.getData_de_nascimento());
        user.setTelefone(userInput.getTelefone());
        user.setNumero(userInput.getNumero());
        user.setComplemento(userInput.getComplemento());
        user.setEstado(userInput.getEstado());
        user.setNome_responsavel(userInput.getNome_responsavel());
        user.setEmail_responsavel(userInput.getEmail_responsavel());
        user.setCelular_responsavel(userInput.getCelular_responsavel());
        return userRepository.save(user);
    }

    public User deactivateById(Long id) {
        User user = findById(id);
        user.setActived(false);
        return userRepository.save(user);
    }

    public List<User> listAllUserDesactived() {
        return userRepository.findAllUserDesactived();
    }

    public User findByIdDesactived(Long id) {
        return userRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("User não encontrada"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByExistEmail(email);
    }

    public User ativarById(Long id) {
        User user = findByIdDesactived(id);
        user.setActived(true);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getActived() == false) {
            throw new UsernameNotFoundException("User not found");
        } else {
            Role role = roleService.findById(user.getRole().getId());
            return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).roles(role.getRole()).build();
        }
    }

    public User autenticar(User user) {
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        boolean senha = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
        if (senha) {
            return findByEmail(user.getEmail()).get();
        }
        throw new SenhaInvalidaException();
    }

    public boolean ativarConta(String email) {
        User user = this.userRepository.findByExistEmail(email).get();
        if(user.getActived()){
            return false;
        }
        user.setActived(true);
        userRepository.save(user);
        return true;
    }

    public User mudarSenha(User updated, String senha) {
        updated.setPassword(passwordEncoder.encode(senha));
        return userRepository.save(updated);
    }

    public Optional<User>  findByExistEmail(String email) {
        return this.userRepository.findByExistEmail(email);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }


//    public List<User> findByCpf(String cpf) {
//        return userRepository.findByCpf(cpf);
//    }
}
