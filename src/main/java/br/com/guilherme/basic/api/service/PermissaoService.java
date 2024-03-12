package br.com.guilherme.basic.api.service;

import br.com.guilherme.basic.model.entity.Permissao;
import br.com.guilherme.basic.model.input.PermissaoInput;
import br.com.guilherme.basic.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissaoService {

    @Autowired
    private final PermissaoRepository permissaoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Permissao findById(Long id) {
        return permissaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Permissao nao encontrada"));
    }

    public List<Permissao> listAll() {
        return permissaoRepository.findAll();
    }

    public Permissao save(PermissaoInput permissaoInput) {
        Permissao permissao = modelMapper.map(permissaoInput, Permissao.class);
        return permissaoRepository.save(permissao);
    }

    public Permissao updateById(Long id, PermissaoInput permissaoInput) {
        Permissao permissao = findById(id);
        modelMapper.map(permissaoInput, permissao);
        return permissaoRepository.save(permissao);
    }

    public void desactivateById(Long id) {
        Permissao permissao = findById(id);
        permissaoRepository.delete(permissao);
    }
}


