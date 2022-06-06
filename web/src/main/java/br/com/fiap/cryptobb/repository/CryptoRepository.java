package br.com.fiap.cryptobb.repository;

import br.com.fiap.cryptobb.entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptoRepository extends JpaRepository<CryptoEntity, Integer> {

    List<CryptoEntity> findAllByNameContaining(String name);

    @Query("from CryptoEntity c where c.name like :name")
    List<CryptoEntity> buscarCryptosPorNome(String name);

}
