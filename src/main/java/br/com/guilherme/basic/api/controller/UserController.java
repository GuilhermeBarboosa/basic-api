package br.com.guilherme.basic.api.controller;


import br.com.guilherme.basic.api.service.UserService;
import br.com.guilherme.basic.model.entity.User;
import br.com.guilherme.basic.model.input.LoginInput;
import br.com.guilherme.basic.model.input.UserInput;
import br.com.guilherme.basic.model.output.EmailOutput;
import br.com.guilherme.basic.model.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final UserService userService;
    @Autowired
    private EmailController emailController;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserInput userInput) {
        if (userService.findByEmail(userInput.getEmail()).isPresent()) {
            return new ResponseEntity<String>("Email já cadastrado", HttpStatus.BAD_REQUEST);
        } else {
            User createdUser = userService.save(userInput);
            UserOutput userOutput = new UserOutput(createdUser);
            return ResponseEntity.ok(userOutput);
        }
    }

    @PostMapping("/candidato")
    public ResponseEntity<?> saveCandidato(@Valid @RequestBody UserInput userInput) {
        if (userService.findByEmail(userInput.getEmail()).isPresent()) {
            return new ResponseEntity<String>("Email já cadastrado", HttpStatus.BAD_REQUEST);
        } else {
            if (userInput.getRole() == 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possivel cadastrar um candidato como administrador");
            } else {

                User createdUser = userService.save(userInput);
                UserOutput userOutput = new UserOutput(createdUser);

                try {
                    EmailOutput emailOutput = this.emailController.createEmailUser(createdUser);
                    emailController.enviaEmail(emailOutput);
                } catch (Exception e) {
                    this.userService.delete(createdUser.getId());
                    return ResponseEntity.badRequest().body("Erro no sistema");
                }
                return ResponseEntity.ok(userOutput);
            }
        }
    }


    @GetMapping
    public ResponseEntity<List<UserOutput>> listAll() {
        List<User> users = userService.listAll();
        List<UserOutput> responseDTOS = users.stream()
                .map(UserOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<UserOutput>> listAllUser() {
        List<User> users = userService.listAllUserDesactived();
        List<UserOutput> responseDTOS = users.stream()
                .map(UserOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserOutput userOutput = new UserOutput(user);
        return ResponseEntity.ok(userOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<UserOutput> getByIdDesactived(@PathVariable Long id) {
        User user = userService.findByIdDesactived(id);
        UserOutput userOutput = new UserOutput(user);
        return ResponseEntity.ok(userOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid UserInput userInput) {
        User updatedUser = userService.updateById(id, userInput);
        UserOutput userOutput = new UserOutput(updatedUser);
        return ResponseEntity.ok(userOutput);
    }

    @PostMapping("/mudarSenha")
    public ResponseEntity<?> updateById(@RequestBody @Valid LoginInput loginInput) {
        User updatedUser = userService.findByExistEmail(loginInput.getEmail()).get();
        if (!updatedUser.getActived()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário desativado");
        } else {
            updatedUser = userService.mudarSenha(updatedUser, loginInput.getPassword());
            UserOutput userOutput = new UserOutput(updatedUser);
            return ResponseEntity.ok(userOutput);
        }
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ativarById(@PathVariable Long id) {
        User updatedUser = userService.ativarById(id);
        UserOutput userOutput = new UserOutput(updatedUser);
        return ResponseEntity.ok(userOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserOutput> deactivateById(@PathVariable Long id) {
        User deactivatedUser = userService.deactivateById(id);
        UserOutput userOutput = new UserOutput(deactivatedUser);
        return ResponseEntity.ok(userOutput);
    }

    public User autenticar(User usuario) {
        return userService.autenticar(usuario);
    }

    public boolean ativarConta(String email) {
        return userService.ativarConta(email);
    }

    public void recuperarSenha(String email) throws MessagingException {
        Optional<User> user = userService.findByExistEmail(email);
        if (!user.isPresent()) {
            throw new RuntimeException("Email não cadastrado");
        } else {
            EmailOutput emailOutput = this.emailController.createEmailRecuperarSenha(user);
            emailController.enviaEmail(emailOutput);
        }

    }
}
