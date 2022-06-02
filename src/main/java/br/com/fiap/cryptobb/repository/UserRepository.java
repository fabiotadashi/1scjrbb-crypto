package br.com.fiap.cryptobb.repository;

import br.com.fiap.cryptobb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findFirstByNameEquals(String name);

}
