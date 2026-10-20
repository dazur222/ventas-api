package com.elgardenia.ventas_api.formapago;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagoRepository extends JpaRepository<FormaPago, Long> {

    List<FormaPago> findAllByActivoTrueOrderByNombreAsc();

    Optional<FormaPago> findByIdAndActivoTrue(Long id);
}
