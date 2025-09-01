-- Garante 1 ticket ABERTO por veículo usando índice único baseado em função
-- Em Oracle, valores NULL não conflitam em índices únicos. Assim, apenas status='ABERTO' participa.
CREATE UNIQUE INDEX uq_ticket_veiculo_aberto
  ON ticket (CASE WHEN status = 'ABERTO' THEN veiculo_id END);
