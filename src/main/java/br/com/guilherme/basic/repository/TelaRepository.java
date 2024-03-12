package br.com.guilherme.basic.repository;

import br.com.guilherme.basic.model.entity.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelaRepository extends JpaRepository<Tela, Long> {

}
