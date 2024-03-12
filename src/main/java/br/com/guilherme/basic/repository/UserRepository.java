package br.com.guilherme.basic.repository;

import br.com.guilherme.basic.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);

    @Query("select u from User u where u.actived = false")
    List<User> findAllUserDesactived();

    @Query("select u from User u where u.id = :id and u.actived = false")
    Optional<User> findByIdDesactived(Long id);

    @Query("SELECT u FROM User u WHERE u.email LIKE :email")
    Optional<User> findByExistEmail(@Param("email") String email);

    Optional<User> findByEmail(String email);
}
