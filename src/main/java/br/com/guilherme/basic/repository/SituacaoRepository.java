package br.com.guilherme.basic.repository;

import br.com.guilherme.basic.model.entity.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, Long> {

}
