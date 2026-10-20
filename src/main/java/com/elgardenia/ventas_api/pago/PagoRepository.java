package com.elgardenia.ventas_api.pago;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    List<Pago> findAllByVenta_IdOrderByFechaPagoDescIdDesc(Integer ventaId);

    @Query("""
        select coalesce(sum(pago.monto), 0)
        from Pago pago
        where pago.venta.id = :ventaId
          and pago.estadoPago = 'CONFIRMADO'
        """)
    BigDecimal sumarPagosConfirmados(@Param("ventaId") Integer ventaId);

    @Query("""
        select pago.venta.id as ventaId, sum(pago.monto) as totalPagado
        from Pago pago
        where pago.venta.id in :ventaIds
          and pago.estadoPago = 'CONFIRMADO'
        group by pago.venta.id
        """)
    List<PagoTotalVenta> findTotalesConfirmadosPorVentaIds(@Param("ventaIds") List<Integer> ventaIds);
}
