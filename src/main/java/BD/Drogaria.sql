CREATE DATABASE Drogaria;
USE Drogaria;

-- ===============================
-- Tabela: drogaria
-- ===============================
CREATE TABLE drogaria (
  CNPJ_Drog VARCHAR(18) PRIMARY KEY,
  Nome_Drog VARCHAR(50) NOT NULL,
  Telefone_Drog VARCHAR(15) NOT NULL,
  Cep_Drog VARCHAR(10) NOT NULL,
  Num_Drog INT NOT NULL,
  Email_Drog VARCHAR(50) UNIQUE NOT NULL
);
-- ===============================
-- Tabela: laboratorio
-- ===============================
CREATE TABLE laboratorio (
  CNPJ_Lab VARCHAR(18) PRIMARY KEY,
  Nome_Lab VARCHAR(50) NOT NULL,
  Telefone_Lab VARCHAR(15) NOT NULL,
  Cep_Lab VARCHAR(10) NOT NULL,
  Num_Lab INT UNIQUE NOT NULL,
  Email_Lab VARCHAR(50) UNIQUE NOT NULL
);
-- ===============================
-- Tabela: laboratorio
-- ===============================
CREATE TABLE funcionario (
  CPF VARCHAR(14) PRIMARY KEY,
  Nome_Fun VARCHAR(50) NOT NULL,
  Telefone_Fun VARCHAR(15) NOT NULL,
  Cep_Fun VARCHAR(10) NOT NULL,
  Num_Fun VARCHAR(15) NOT NULL,
  Email_Fun VARCHAR(50) UNIQUE NOT NULL,
  Senha VARCHAR(255) NOT NULL,
  Cargo VARCHAR(20) NOT NULL
);

-- ===============================
-- Tabela: catalogo_medicamento
-- ===============================
CREATE TABLE catalogo_medicamento (
  Cod_CatMed INT AUTO_INCREMENT PRIMARY KEY,
  Nome_CatMed VARCHAR(50) NOT NULL,
  Desc_CatMed VARCHAR(100) NOT NULL,
  Valor_CatMed DECIMAL(10,2) NOT NULL,
  CNPJ_Lab VARCHAR(18),
  FOREIGN KEY (CNPJ_Lab) REFERENCES laboratorio(CNPJ_Lab)
);

-- ===============================
-- Tabela: medicamento
-- ===============================
CREATE TABLE medicamento (
  Cod_Med INT AUTO_INCREMENT PRIMARY KEY,
  Nome_Med VARCHAR(50) NOT NULL,
  Desc_Med VARCHAR(100) NOT NULL,
  DataVal_Med DATE NOT NULL,
  Qtd_Med INT NOT NULL,
  Valor_Med DECIMAL(10,2) NOT NULL,
  Cod_CatMed INT,
  FOREIGN KEY (Cod_CatMed) REFERENCES catalogo_medicamento(Cod_CatMed)
);

-- ===============================
-- Tabela: compra
-- ===============================
CREATE TABLE compra (
  NotaFiscal_Entrada INT AUTO_INCREMENT PRIMARY KEY,
  Valor_Total DECIMAL(10,2) NOT NULL,
  CPF VARCHAR(14),
  CNPJ_Drog VARCHAR(18),
  FOREIGN KEY (CPF) REFERENCES funcionario(CPF),
  FOREIGN KEY (CNPJ_Drog) REFERENCES drogaria(CNPJ_Drog)
);

-- ===============================
-- Tabela: venda
-- ===============================
CREATE TABLE venda (
  NotaFiscal_Saida INT AUTO_INCREMENT PRIMARY KEY,
  Data_Venda DATE NOT NULL,
  Valor_Venda DECIMAL(10,2) NOT NULL,
  CNPJ_Drog VARCHAR(18),
  CPF VARCHAR(14),
  FOREIGN KEY (CNPJ_Drog) REFERENCES drogaria(CNPJ_Drog),
  FOREIGN KEY (CPF) REFERENCES funcionario(CPF)
);

-- ===============================
-- Tabela: item
-- ===============================
CREATE TABLE item (
  Cod_Item INT AUTO_INCREMENT PRIMARY KEY,
  DataVal_Item DATE NOT NULL,
  Qtd_Item INT NOT NULL,
  Valor_Item DECIMAL(10,2) NOT NULL,
  Data_Venda DATE NOT NULL,
  NotaFiscal_Entrada INT,
  Cod_CatMed INT,
  Cod_Med INT,
  FOREIGN KEY (NotaFiscal_Entrada) REFERENCES compra(NotaFiscal_Entrada),
  FOREIGN KEY (Cod_CatMed) REFERENCES catalogo_medicamento(Cod_CatMed),
  FOREIGN KEY (Cod_Med) REFERENCES medicamento(Cod_Med)
);

-- ===============================
-- Tabela: item_venda
-- ===============================
CREATE TABLE item_venda (
  Cod_ItemVenda INT AUTO_INCREMENT PRIMARY KEY,
  DataVal_ItemVenda DATE NOT NULL,
  Qtd_ItemVenda INT NOT NULL,
  Valor_ItemVenda DECIMAL(10,2) NOT NULL,
  Cod_Med INT,
  NotaFiscal_Saida INT,
  FOREIGN KEY (Cod_Med) REFERENCES medicamento(Cod_Med),
  FOREIGN KEY (NotaFiscal_Saida) REFERENCES venda(NotaFiscal_Saida)
);

-- ===============================
-- INSERTS
-- ===============================

-- 1. Drogaria
INSERT INTO drogaria (CNPJ_Drog, Nome_Drog, Telefone_Drog, Cep_Drog, Num_Drog, Email_Drog) VALUES
('12.345.678/0001-11', 'Drogaria Central', '(11)99888-1111', '01001-000', 101, 'contato@drogariacentral.com'),
('23.456.789/0001-22', 'Drogaria Vida', '(11)97777-2222', '01002-000', 102, 'contato@drogariavida.com'),
('34.567.890/0001-33', 'Drogaria São João', '(21)98888-3333', '20010-000', 103, 'sac@drogariasãojoao.com'),
('45.678.901/0001-44', 'Drogaria Bem Estar', '(31)96666-4444', '30110-000', 104, 'contato@drogariabemestar.com'),
('56.789.012/0001-55', 'Drogaria Popular', '(41)95555-5555', '80010-000', 105, 'vendas@drogariapopular.com'),
('67.890.123/0001-66', 'Drogaria São Paulo', '(51)94444-6666', '90010-000', 106, 'suporte@drogariasaopaulo.com'),
('78.901.234/0001-77', 'Drogaria FarmaMais', '(71)93333-7777', '40010-000', 107, 'atendimento@farmamais.com'),
('89.012.345/0001-88', 'Drogaria Econômica', '(85)92222-8888', '60010-000', 108, 'contato@economica.com'),
('90.123.456/0001-99', 'Drogaria Nacional', '(61)91111-9999', '70010-000', 109, 'sac@drogarianacional.com'),
('11.222.333/0001-00', 'Drogaria Prime', '(19)90000-0000', '13010-000', 110, 'prime@drogariaprime.com');

INSERT INTO funcionario (CPF, Nome_Fun, Telefone_Fun, Cep_Fun, Num_Fun, Email_Fun, Senha, Cargo) VALUES
('111.111.111-11', 'Marcos Silva', '(11)91111-1111', '01001-000', '101A', 'marcos@drogariacentral.com', '12345', 'Gerente'),
('222.222.222-22', 'Ana Paula Costa', '(11)92222-2222', '01002-000', '102B', 'ana@drogariavida.com', 'abc123', 'Gerente'),
('333.333.333-33', 'Lucas Ferreira', '(21)93333-3333', '20010-000', '103C', 'lucas@drogariasãojoao.com', 'senha321', 'Usuario'),
('444.444.444-44', 'Fernanda Oliveira', '(31)94444-4444', '30110-000', '104D', 'fernanda@drogariabemestar.com', 'senha123', 'Usuario'),
('555.555.555-55', 'Bruno Santos', '(41)95555-5555', '80010-000', '105E', 'bruno@drogariapopular.com', 'bruno@1', 'Usuario'),
('666.666.666-66', 'Juliana Rocha', '(51)96666-6666', '90010-000', '106F', 'juliana@drogariasaopaulo.com', 'jul123', 'Usuario'),
('777.777.777-77', 'Rafael Almeida', '(71)97777-7777', '40010-000', '107G', 'rafael@farmamais.com', 'rafael1', 'Usuario'),
('888.888.888-88', 'Paula Mendes', '(85)98888-8888', '60010-000', '108H', 'paula@economica.com', 'paula2024', 'Usuario'),
('999.999.999-99', 'Thiago Moreira', '(61)99999-9999', '70010-000', '109I', 'thiago@nacional.com', 'thg123', 'Usuario'),
('123.456.789-00', 'Beatriz Nunes', '(19)91234-5678', '13010-000', '110J', 'beatriz@prime.com', 'bprime', 'Usuario');

INSERT INTO laboratorio (CNPJ_Lab, Nome_Lab, Telefone_Lab, Cep_Lab, Num_Lab, Email_Lab) VALUES
('01.111.111/0001-01', 'Pfizer', '(11)90000-0001', '01010-000', 1, 'contato@pfizer.com'),
('02.222.222/0001-02', 'EMS', '(19)90000-0002', '13020-000', 2, 'contato@ems.com'),
('03.333.333/0001-03', 'Eurofarma', '(11)90000-0003', '04567-000', 3, 'vendas@eurofarma.com'),
('04.444.444/0001-04', 'Aché', '(11)90000-0004', '04710-000', 4, 'sac@ache.com'),
('05.555.555/0001-05', 'Biolab', '(11)90000-0005', '04610-000', 5, 'atendimento@biolab.com'),
('06.666.666/0001-06', 'Neo Química', '(11)90000-0006', '03510-000', 6, 'info@neoquimica.com'),
('07.777.777/0001-07', 'Sanofi', '(11)90000-0007', '04010-000', 7, 'contato@sanofi.com'),
('08.888.888/0001-08', 'Roche', '(11)90000-0008', '05010-000', 8, 'roche@contato.com'),
('09.999.999/0001-09', 'Bayer', '(11)90000-0009', '06010-000', 9, 'bayer@br.com'),
('10.000.000/0001-10', 'Medley', '(11)90000-0010', '07010-000', 10, 'medley@farmaco.com');

INSERT INTO catalogo_medicamento (Nome_CatMed, Desc_CatMed, Valor_CatMed, CNPJ_Lab) VALUES
('Paracetamol', 'Analgésico e antipirético', 12.50, '01.111.111/0001-01'),
('Ibuprofeno', 'Anti-inflamatório não esteroide', 18.90, '02.222.222/0001-02'),
('Amoxicilina', 'Antibiótico de amplo espectro', 32.00, '03.333.333/0001-03'),
('Loratadina', 'Antialérgico', 15.75, '04.444.444/0001-04'),
('Omeprazol', 'Inibidor da bomba de prótons', 25.00, '05.555.555/0001-05'),
('Dipirona', 'Analgésico e antitérmico', 10.00, '06.666.666/0001-06'),
('Cetoprofeno', 'Anti-inflamatório', 19.50, '07.777.777/0001-07'),
('Sinvastatina', 'Reduz colesterol', 28.40, '08.888.888/0001-08'),
('Azitromicina', 'Antibiótico', 35.00, '09.999.999/0001-09'),
('Losartana', 'Antihipertensivo', 22.90, '10.000.000/0001-10');

INSERT INTO medicamento (Nome_Med, Desc_Med, DataVal_Med, Qtd_Med, Valor_Med, Cod_CatMed) VALUES
('Paracetamol 500mg', 'Comprimidos 500mg', '2026-05-20', 150, 12.50, 1),
('Ibuprofeno 600mg', 'Comprimidos 600mg', '2025-12-10', 200, 18.90, 2),
('Amoxicilina 500mg', 'Cápsulas 500mg', '2026-02-15', 120, 32.00, 3),
('Loratadina 10mg', 'Comprimidos 10mg', '2026-01-10', 180, 15.75, 4),
('Omeprazol 20mg', 'Cápsulas 20mg', '2027-04-20', 250, 25.00, 5),
('Dipirona 1g', 'Comprimidos 1g', '2025-11-10', 300, 10.00, 6),
('Cetoprofeno 100mg', 'Cápsulas 100mg', '2026-03-12', 150, 19.50, 7),
('Sinvastatina 20mg', 'Comprimidos 20mg', '2027-07-30', 100, 28.40, 8),
('Azitromicina 500mg', 'Cápsulas 500mg', '2026-12-01', 90, 35.00, 9),
('Losartana 50mg', 'Comprimidos 50mg', '2027-09-05', 180, 22.90, 10);

INSERT INTO compra (Valor_Total, CPF, CNPJ_Drog) VALUES
(500.00, '111.111.111-11', '12.345.678/0001-11'),
(620.00, '222.222.222-22', '23.456.789/0001-22'),
(750.00, '333.333.333-33', '34.567.890/0001-33'),
(830.00, '444.444.444-44', '45.678.901/0001-44'),
(920.00, '555.555.555-55', '56.789.012/0001-55'),
(1050.00, '666.666.666-66', '67.890.123/0001-66'),
(980.00, '777.777.777-77', '78.901.234/0001-77'),
(1110.00, '888.888.888-88', '89.012.345/0001-88'),
(890.00, '999.999.999-99', '90.123.456/0001-99'),
(770.00, '123.456.789-00', '11.222.333/0001-00');

INSERT INTO venda (Data_Venda, Valor_Venda, CNPJ_Drog, CPF) VALUES
('2025-11-01', 45.00, '12.345.678/0001-11', '111.111.111-11'),
('2025-11-02', 62.50, '23.456.789/0001-22', '222.222.222-22'),
('2025-11-03', 78.90, '34.567.890/0001-33', '333.333.333-33'),
('2025-11-04', 90.00, '45.678.901/0001-44', '444.444.444-44'),
('2025-11-05', 110.50, '56.789.012/0001-55', '555.555.555-55'),
('2025-11-06', 65.00, '67.890.123/0001-66', '666.666.666-66'),
('2025-11-07', 120.00, '78.901.234/0001-77', '777.777.777-77'),
('2025-11-08', 85.00, '89.012.345/0001-88', '888.888.888-88'),
('2025-11-09', 95.30, '90.123.456/0001-99', '999.999.999-99'),
('2025-11-10', 70.20, '11.222.333/0001-00', '123.456.789-00');

INSERT INTO item (DataVal_Item, Qtd_Item, Valor_Item, Data_Venda, NotaFiscal_Entrada, Cod_CatMed, Cod_Med) VALUES
('2026-05-20', 20, 250.00, '2025-11-01', 1, 1, 1),
('2025-12-10', 15, 283.50, '2025-11-02', 2, 2, 2),
('2026-02-15', 10, 320.00, '2025-11-03', 3, 3, 3),
('2026-01-10', 25, 393.75, '2025-11-04', 4, 4, 4),
('2027-04-20', 30, 750.00, '2025-11-05', 5, 5, 5),
('2025-11-10', 40, 400.00, '2025-11-06', 6, 6, 6),
('2026-03-12', 25, 487.50, '2025-11-07', 7, 7, 7),
('2027-07-30', 15, 426.00, '2025-11-08', 8, 8, 8),
('2026-12-01', 10, 350.00, '2025-11-09', 9, 9, 9),
('2027-09-05', 20, 458.00, '2025-11-10', 10, 10, 10);

INSERT INTO item_venda (DataVal_ItemVenda, Qtd_ItemVenda, Valor_ItemVenda, Cod_Med, NotaFiscal_Saida) VALUES
('2026-05-20', 2, 25.00, 1, 1),
('2025-12-10', 3, 56.70, 2, 2),
('2026-02-15', 1, 32.00, 3, 3),
('2026-01-10', 2, 31.50, 4, 4),
('2027-04-20', 3, 75.00, 5, 5),
('2025-11-10', 5, 50.00, 6, 6),
('2026-03-12', 2, 39.00, 7, 7),
('2027-07-30', 1, 28.40, 8, 8),
('2026-12-01', 2, 70.00, 9, 9),
('2027-09-05', 3, 68.70, 10, 10);

CREATE OR REPLACE VIEW vw_relatorio_vendas_detalhadas AS
SELECT
    V.NotaFiscal_Saida,
    V.Data_Venda AS Data_Venda,
    V.Valor_Venda AS Valor_Total_Venda,
    M.Nome_Med AS Medicamento_Vendido,
    M.Valor_Med AS Valor_Base_Medicamento,
    IV.Qtd_ItemVenda AS Quantidade,
    IV.Valor_ItemVenda AS Valor_Unitario_Venda,
    F.Nome_Fun AS Funcionario_Venda,
    D.Nome_Drog AS Drogaria_Venda
FROM
    venda V
JOIN
    item_venda IV ON V.NotaFiscal_Saida = IV.NotaFiscal_Saida
JOIN
    medicamento M ON IV.Cod_Med = M.Cod_Med
JOIN
    funcionario F ON V.CPF = F.CPF
JOIN
    drogaria D ON V.CNPJ_Drog = D.CNPJ_Drog;

DELIMITER $$

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_atualiza_estoque_apos_compra $$
CREATE PROCEDURE sp_atualiza_estoque_apos_compra (
    IN p_Cod_Med INT,
    IN p_Qtd_Comprada INT
)
BEGIN
    DECLARE v_estoque_atual INT;

    -- 1. Busca o estoque atual do medicamento
    SELECT Qtd_Med INTO v_estoque_atual
    FROM medicamento
    WHERE Cod_Med = p_Cod_Med;

    -- 2. Atualiza o estoque com a nova quantidade
    UPDATE medicamento
    SET Qtd_Med = v_estoque_atual + p_Qtd_Comprada
    WHERE Cod_Med = p_Cod_Med;

    -- 3. Retorna uma mensagem de confirmação
    SELECT CONCAT(
        'Estoque atualizado com sucesso! (Cod_Med: ', p_Cod_Med, ') ',
        'Quantidade adicionada: ', p_Qtd_Comprada,
        '. Novo total: ', v_estoque_atual + p_Qtd_Comprada
    ) AS Mensagem_Estoque;
END $$

DELIMITER ;

DELIMITER $$

DROP TRIGGER IF EXISTS trg_baixa_estoque_apos_venda $$
CREATE TRIGGER trg_baixa_estoque_apos_venda
AFTER INSERT ON item_venda
FOR EACH ROW
BEGIN
    DECLARE v_cod_med INT;
    DECLARE v_qtd_vendida INT;

    -- 1️⃣ Pega o código e quantidade vendidos diretamente do novo registro
    SET v_cod_med = NEW.Cod_Med;
    SET v_qtd_vendida = NEW.Qtd_ItemVenda;

    -- 2️⃣ Atualiza o estoque do medicamento
    IF v_cod_med IS NOT NULL THEN
        UPDATE medicamento
        SET Qtd_Med = Qtd_Med - v_qtd_vendida
        WHERE Cod_Med = v_cod_med;
    END IF;
END $$

DELIMITER ;
