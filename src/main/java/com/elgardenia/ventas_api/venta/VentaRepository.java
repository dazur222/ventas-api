package com.elgardenia.ventas_api.venta;

import java.util.Optional;
import java.util.List;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    List<Venta> findAllByOrderByFechaVentaDescIdDesc();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select venta from Venta venta where venta.id = :id")
    Optional<Venta> findByIdForUpdate(@Param("id") Integer id);

    @Modifying(flushAutomatically = true)
    @Query("update Venta venta set venta.estadoCobranza = :estadoCobranza where venta.id = :id")
    int actualizarEstadoCobranza(@Param("id") Integer id, @Param("estadoCobranza") String estadoCobranza);

    @Query(value = """
        SELECT COALESCE(MAX(CAST(SUBSTRING(folio, 3) AS BIGINT)), 0)
        FROM venta
        WHERE folio ~ '^NV[0-9]+$'
        """, nativeQuery = true)
    long findLastFolioNumber();
}
