# ventas-api

## Configuración de CORS

Configura `CORS_ALLOWED_ORIGINS` en el entorno del servicio para controlar los
orígenes permitidos. Se aceptan varios orígenes separados por comas; `*` permite
cualquier origen temporalmente y es el valor predeterminado actual. Después de
cambiar esta variable en Render, redepliega el backend para aplicar la
configuración.

## Migración de municipio y estado

La relación de clientes ahora se guarda en `cliente.municipio_id`, con `municipio.estado_id` como relación hacia `estado`. Para conservar los datos de una instalación anterior, ejecuta una vez `src/main/resources/db/manual/20261019_normalize_customer_location.sql` contra la base de datos antes de desplegar la nueva versión. La migración transforma las filas de `ubicacion`, reasigna los clientes y después elimina la columna y tabla antiguas. En una base nueva, las entidades JPA crean las tablas y el catálogo debe cargarse con estados y municipios válidos.

La API expone los catálogos de lectura en `GET /estado` y `GET /municipio`; cada municipio incluye `estadoId` y `estadoNombre`. Las solicitudes y respuestas de clientes usan `municipioId`.

## Notas de venta

`GET /venta` lista las notas registradas y `POST /venta` crea una nota junto con sus partidas dentro de una transacción. El folio se genera como `NV0001`, `NV0002`, etc.; el endpoint serializa la asignación del folio con un lock transaccional de PostgreSQL para evitar duplicados concurrentes. Hasta que exista el catálogo de vendedores, `vendedor_id` se guarda como `NULL`.

Cada partida recibe `productoId`, `cantidad` y `precioUnitario`; el precio enviado se persiste en la partida y se usa para los cálculos. Los grados de alcohol se guardan como porcentaje entre 0.1 y 100 y pueden incluir decimales (por ejemplo, `5.5` representa 5.5° G.L.); se comparan directamente contra los umbrales `14` y `20`. El IEPS es 26.5% para cerveza de hasta 14° G.L., 30% para más de 14° y hasta 20° G.L., y 53% para más de 20° G.L. (tasas persistidas como `0.2650`, `0.3000` y `0.5300`). El IVA es 16% y se aplica sobre el importe base más el IEPS. Los montos se redondean a dos decimales por partida y se suman para generar los totales de la venta.

Para registrar cobros, `GET /forma-pago` devuelve las formas activas, `GET /venta/{id}/pagos` consulta el historial y `POST /venta/{id}/pagos` registra un pago confirmado (`CONFIRMADO`). Las respuestas de venta incluyen `totalPagado` y `saldoPendiente`; la cobranza cambia a `PARCIAL` con un abono y a `PAGADA` al liquidar el saldo, estados permitidos por `ck_estado_cobranza`.
