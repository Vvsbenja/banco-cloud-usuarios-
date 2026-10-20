# Banco Cloud - avance EP3 RabbitMQ

## Cambios incluidos
- Dependencia Spring AMQP en microservicio `banco-cloud-sucursales`.
- Nombres de cola, exchange y binding definidos con propiedades.
- Beans `Queue`, `DirectExchange` y `Binding` separados de la lógica de negocio.
- Productor que envía `SUCURSAL_CREADA:<id>` tras crear una sucursal.
- Consumidor `@RabbitListener` que registra eventos en logs.
- Docker Compose local para RabbitMQ Management.

## Aplicar en el repositorio original
Copiar los archivos de esta carpeta conservando las rutas y **sobrescribir** los tres archivos editados (`pom.xml`, `SucursalService.java`, `application.properties`). No reemplazar el repositorio completo por este ZIP: contiene solo cambios.

## Levantar broker
1. Copia `.env.rabbitmq.example` a `.env` y cambia la clave por una propia; nunca subas `.env`.
2. `docker compose -f docker-compose.rabbitmq.yml up -d`
3. Panel en `http://localhost:15672`.
4. Ajusta `RABBITMQ_PASSWORD` en el entorno del microservicio para que coincida con la clave de `.env`.
5. Compila con Java 21: `cd banco-cloud-sucursales && ./mvnw clean test` (Windows: `mvnw.cmd clean test`).
6. Con PostgreSQL y RabbitMQ activos, inicia el microservicio, crea una sucursal usando la API y revisa en logs `Evento RabbitMQ recibido: SUCURSAL_CREADA:<id>`.

## Advertencias
- Esta es la **primera integración**, no la EP3 completa. Aún faltan 3 colas funcionales y DLQ, exchange topic, ACK/NACK manual, administrador REST y cluster de dos nodos para EP4.
- Es una publicación posterior a la escritura en BD y **no garantiza atomicidad**. Para producción usar patrón outbox/reintentos.
- Si RabbitMQ está caído, la creación puede devolver error después de persistir la sucursal. No afirmar tolerancia a fallos todavía.
- Archivos proporcionados sin prueba de compilación ni ejecución en el entorno del usuario.
