package br.com.guilherme.basic.api.controller;

import br.com.guilherme.basic.api.service.JwtService;
import br.com.guilherme.basic.exception.SenhaInvalidaException;
import br.com.guilherme.basic.model.entity.User;
import br.com.guilherme.basic.model.output.Token;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserController userController;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Token login(@RequestBody User loginInput) {
        try {
            User usuario = User.builder()
                    .email(loginInput.getEmail())
                    .password(loginInput.getPassword()).build();

            User user = userController.autenticar(usuario);
            String token = jwtService.gerarToken(user);
            return new Token(usuario.getEmail(), token);

        } catch (SenhaInvalidaException | UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/verifyToken")
    public boolean verifyToken(@RequestBody String token) {
        try {
            return jwtService.tokenValido(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/ativarConta/{email}")
    public ResponseEntity<?> ativarConta(@PathVariable String email) {
        try {
          if(userController.ativarConta(email)){
              return ResponseEntity.ok().body("{\"message\": \"Conta ativada\"}");
          }else{
              return ResponseEntity.badRequest().body("Está conta já está ativada");
          }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/recuperarSenha/{email}")
    public ResponseEntity<?> recuperarSenha(@PathVariable String email) {
        try {
            userController.recuperarSenha(email);
          return ResponseEntity.ok().body("{\"message\": \"Email enviado com sucesso\"}");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


    @PostMapping("/obterClaims")
    public Claims obterClaims(@RequestBody String token) {
        try {
            return jwtService.obterClaims(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
