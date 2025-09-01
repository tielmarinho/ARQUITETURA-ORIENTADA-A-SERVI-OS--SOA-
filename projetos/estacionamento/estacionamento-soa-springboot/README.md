# API de Estacionamento — SOA (Spring Boot + Oracle + Swagger)
Professor: Salatiel Luz Marinho • Data: 2025-09-01

Este projeto é um **exemplo funcional** para o desafio da disciplina de **SOA**:
uma API REST de **Estacionamento** com CRUD de veículos e fluxo **check-in / check-out**.

## Objetivo do Desafio
- CRUD de **Veículos** (placa única).
- **Check-in** cria um ticket aberto para uma vaga.
- **Check-out** encerra o ticket e calcula o valor (tarifa/hora).
- Documentação via **Swagger UI**.

## Como rodar
1. Execute no Oracle os scripts em `db/oracle` na ordem:
   - `01_schema.sql`
   - `02_sample_data.sql` (opcional)
2. Configure variáveis de ambiente - git bash:
   ```bash
   set ORACLE_URL=
   set ORACLE_USER=
   set ORACLE_PASSWORD=
   set PARKING_HOURLY_RATE="8.00"
   ```
3. Execute:
   ```bash
   mvn clean package
   mvn spring-boot:run -Dspring-boot.run.profiles=sid,debug -Dspring-boot.run.fork=false
   ```
4. Acesse: `http://localhost:8080/swagger-ui.html`

## Endpoints principais
- **Veículos**: `GET/POST/PUT/DELETE /api/veiculos`
- **Tickets**:
  - `POST /api/tickets/checkin`
  - `POST /api/tickets/<built-in function id>/checkout`
  - `GET /api/tickets/abertos`
  - `GET /api/tickets/<built-in function id>`

> Cálculo: arredondamento **para cima** por hora (mínimo 1h) × `PARKING_HOURLY_RATE`.

## Estrutura
```
src/main/java/br/edu/fiap/soa/estacionamento
  domain/ (Veiculo, Ticket, TicketStatus)
  repository/ (VeiculoRepository, TicketRepository)
  service/ (VeiculoService, TicketService)
  web/ (controllers, DTOs, handler)
db/oracle (scripts)
```

## Evolução de Aula - 01/09/2025

- Propor refactor no código;
- Aplicar o desenvolvimento dos requisitos: Reservas de vaga, lotação do pátio e relatórios.
- Aplicar regras de preço diferenciadas (diária, frações).