package br.com.guilherme.basic.api.controller;

import br.com.guilherme.basic.api.service.RoleService;
import br.com.guilherme.basic.api.service.RoleTelaService;
import br.com.guilherme.basic.model.entity.Role;
import br.com.guilherme.basic.model.input.RoleInput;
import br.com.guilherme.basic.model.output.RoleOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final RoleService roleService;
    @Autowired
    private final RoleTelaService roleTelaService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody RoleInput roleInput) {
        Role createdRole = roleService.save(roleInput);
        RoleOutput roleOutput = new RoleOutput(createdRole);
        return ResponseEntity.ok(roleOutput);
    }

    @GetMapping
    public ResponseEntity<List<RoleOutput>> listAll() {
        List<Role> roles = roleService.listAllRole();
        List<RoleOutput> responseDTOS = roles.stream()
                .map(RoleOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid RoleInput roleInput) {
        Role updatedRole = roleService.updateById(id, roleInput);
        RoleOutput roleOutput = new RoleOutput(updatedRole);
        return ResponseEntity.ok(roleOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateById(@PathVariable Long id) {
        roleTelaService.deleteByRole(id);
        roleService.desactivateById(id);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Role desativada com sucesso\"}");
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleOutput> getById(@PathVariable Long id) {
        Role role = roleService.findById(id);
        RoleOutput roleOutput = new RoleOutput(role);
        return ResponseEntity.ok(roleOutput);
    }

}
