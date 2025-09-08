package com.fiap.aeroporto.repository;

import com.fiap.aeroporto.model.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {
    Optional<Aeroporto> findByCodigo(String codigo);
}
