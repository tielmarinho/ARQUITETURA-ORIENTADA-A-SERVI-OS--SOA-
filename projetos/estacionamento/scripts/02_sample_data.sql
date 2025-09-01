INSERT INTO veiculo (placa, modelo, cor) VALUES ('ABC-1D23', 'Fiat Argo 1.3', 'Preto');
INSERT INTO veiculo (placa, modelo, cor) VALUES ('XYZ-9Z99', 'Honda HR-V', 'Branco');
INSERT INTO ticket (veiculo_id, vaga, entrada, status) SELECT id, 'A12', SYSTIMESTAMP, 'ABERTO' FROM veiculo WHERE placa='ABC-1D23';
