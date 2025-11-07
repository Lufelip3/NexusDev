CREATE DATABASE Drogaria;
USE Drogaria;

-- Tabela Funcionário
CREATE TABLE funcionario (
  CPF VARCHAR(14) UNIQUE PRIMARY KEY,
  Nome_Fun VARCHAR(50) NOT NULL,
  Telefone_Fun VARCHAR(15) NOT NULL,
  Cep_Fun VARCHAR(10) NOT NULL,
  Num_Fun VARCHAR(15) NOT NULL,
  Email_Fun VARCHAR(50) UNIQUE NOT NULL
);

-- Tabela Compra
CREATE TABLE compra (
  NotaFiscal_Entrada INT UNIQUE PRIMARY KEY,
  Data_Venda DATE NOT NULL,
  Valor_Total DECIMAL(10,2) NOT NULL
);

-- Tabela Item
CREATE TABLE item (
  Cod_Item INT AUTO_INCREMENT PRIMARY KEY,
  Nome_Item VARCHAR(50) NOT NULL,
  Desc_Item VARCHAR(100) NOT NULL,
  DataVal_Item DATE NOT NULL,
  Qtd_Item INT NOT NULL,
  Valor_Item DECIMAL(10,2) NOT NULL
);

-- Tabela Medicamento
CREATE TABLE medicamento (
  Cod_Med INT UNIQUE PRIMARY KEY,
  Nome_Med VARCHAR(50) NOT NULL,
  Desc_Med VARCHAR(100) NOT NULL,
  DataVal_Med DATE NOT NULL,
  Qtd_Med INT NOT NULL,
  Valor_Med DECIMAL(10,2) NOT NULL
);

-- Tabela Catálogo de Medicamentos
CREATE TABLE catalogo_medicamento (
  Cod_CatMed INT AUTO_INCREMENT PRIMARY KEY,
  Nome_CatMed VARCHAR(50) NOT NULL,
  Desc_CatMed VARCHAR(100) NOT NULL,
  Valor_CatMed DECIMAL(10,2) NOT NULL,
  Cod_Med INT NOT NULL,
  CONSTRAINT fk_cod_med FOREIGN KEY (Cod_Med) REFERENCES medicamento(Cod_Med)
);

-- Tabela Laboratório
CREATE TABLE laboratorio (
  CNPJ_Lab VARCHAR(18) UNIQUE PRIMARY KEY,
  Nome_Lab VARCHAR(50) NOT NULL,
  Telefone_Lab VARCHAR(15) NOT NULL,
  Cep_Lab VARCHAR(10) NOT NULL,
  Num_Lab INT UNIQUE NOT NULL,
  Email_Lab VARCHAR(50) UNIQUE NOT NULL
);

-- Tabela Item Venda
CREATE TABLE item_venda (
  Cod_ItemVenda INT AUTO_INCREMENT PRIMARY KEY,
  Nome_ItemVenda VARCHAR(50) NOT NULL,
  Desc_ItemVenda VARCHAR(100) NOT NULL,
  DataVal_ItemVenda DATE NOT NULL,
  Qtd_ItemVenda INT NOT NULL,
  Valor_ItemVenda DECIMAL(10,2) NOT NULL
);

-- Tabela Venda
CREATE TABLE venda (
  NotaFiscal_Saida INT AUTO_INCREMENT PRIMARY KEY,
  Data_Venda DATE NOT NULL,
  Valor_Venda DECIMAL(10,2) NOT NULL,
  Cod_Rastreio VARCHAR(30)
);

-- Tabela Drogaria
CREATE TABLE drogaria (
  CNPJ_Drog VARCHAR(18) UNIQUE PRIMARY KEY,
  Nome_Drog VARCHAR(50) NOT NULL,
  Telefone_Drog VARCHAR(15) NOT NULL,
  Cep_Drog VARCHAR(10) NOT NULL,
  Num_Drog INT NOT NULL,
  Email_Drog VARCHAR(50) UNIQUE NOT NULL,
  NotaFiscal_Saida INT NOT NULL,
  CONSTRAINT fk_nota_saida_drog FOREIGN KEY (NotaFiscal_Saida) REFERENCES venda(NotaFiscal_Saida)
);

-- Tabela RealizaCompra
CREATE TABLE realiza_compra (
  CPF VARCHAR(14),
  NotaFiscal_Entrada INT,
  PRIMARY KEY (CPF, NotaFiscal_Entrada),
  CONSTRAINT fk_cpf_compra FOREIGN KEY (CPF) REFERENCES funcionario(CPF),
  CONSTRAINT fk_nf_entrada_compra FOREIGN KEY (NotaFiscal_Entrada) REFERENCES compra(NotaFiscal_Entrada)
);

-- Tabela Possui
CREATE TABLE possuiCompraItem (
  NotaFiscal_Entrada INT,
  Cod_Item INT,
  PRIMARY KEY (NotaFiscal_Entrada, Cod_Item),
  CONSTRAINT fk_nf_entrada_possui FOREIGN KEY (NotaFiscal_Entrada) REFERENCES compra(NotaFiscal_Entrada),
  CONSTRAINT fk_cod_item_possui FOREIGN KEY (Cod_Item) REFERENCES item(Cod_Item)
);

-- Tabela CompõeItem
CREATE TABLE compoe_item (
  Cod_Item INT,
  Cod_CatMed INT,
  PRIMARY KEY (Cod_Item, Cod_CatMed),
  CONSTRAINT fk_cod_item_compoe FOREIGN KEY (Cod_Item) REFERENCES item(Cod_Item),
  CONSTRAINT fk_cod_catmed_compoe FOREIGN KEY (Cod_CatMed) REFERENCES catalogo_medicamento(Cod_CatMed)
);

-- Tabela Fornece
CREATE TABLE forneceCatalogoLab (
  Cod_CatMed INT,
  CNPJ_Lab VARCHAR(18),
  PRIMARY KEY (Cod_CatMed, CNPJ_Lab),
  CONSTRAINT fk_cod_catmed_fornece FOREIGN KEY (Cod_CatMed) REFERENCES catalogo_medicamento(Cod_CatMed),
  CONSTRAINT fk_cnpj_lab_fornece FOREIGN KEY (CNPJ_Lab) REFERENCES laboratorio(CNPJ_Lab)
);

-- Tabela CompoeMedicamento
CREATE TABLE compoe_medicamento (
  Cod_CatMed INT,
  Cod_Med INT,
  PRIMARY KEY (Cod_CatMed, Cod_Med),
  CONSTRAINT fk_cod_catmed_med FOREIGN KEY (Cod_CatMed) REFERENCES catalogo_medicamento(Cod_CatMed),
  CONSTRAINT fk_cod_med_compoe FOREIGN KEY (Cod_Med) REFERENCES medicamento(Cod_Med)
);

-- Tabela CompoeEstoque
CREATE TABLE compoe_estoque (
  Cod_Med INT,
  Cod_ItemVenda INT,
  PRIMARY KEY (Cod_Med, Cod_ItemVenda),
  CONSTRAINT fk_cod_med_estoque FOREIGN KEY (Cod_Med) REFERENCES medicamento(Cod_Med),
  CONSTRAINT fk_cod_itemvenda_estoque FOREIGN KEY (Cod_ItemVenda) REFERENCES item_venda(Cod_ItemVenda)
);

-- Tabela Contém
CREATE TABLE contemVenda (
  Cod_ItemVenda INT,
  NotaFiscal_Saida INT,
  PRIMARY KEY (Cod_ItemVenda, NotaFiscal_Saida),
  CONSTRAINT fk_cod_itemvenda_contem FOREIGN KEY (Cod_ItemVenda) REFERENCES item_venda(Cod_ItemVenda),
  CONSTRAINT fk_nf_saida_contem FOREIGN KEY (NotaFiscal_Saida) REFERENCES venda(NotaFiscal_Saida)
);

-- Tabela RealizaVenda
CREATE TABLE realiza_venda (
  CPF VARCHAR(14),
  NotaFiscal_Saida INT,
  PRIMARY KEY (CPF, NotaFiscal_Saida),
  CONSTRAINT fk_cpf_venda FOREIGN KEY (CPF) REFERENCES funcionario(CPF),
  CONSTRAINT fk_nf_saida_venda FOREIGN KEY (NotaFiscal_Saida) REFERENCES venda(NotaFiscal_Saida)
);

-- 1. TABELAS SEM DEPENDÊNCIAS (podem ser inseridas primeiro)
INSERT INTO funcionario (CPF, Nome_Fun, Telefone_Fun, Cep_Fun, Num_Fun, Email_Fun) VALUES
('12345678901', 'João da Silva', '11912345678', '13500123', '123', 'joao@drogaria.com'),
('23456789012', 'Maria Oliveira', '11923456789', '13500234', '456', 'maria@drogaria.com'),
('34567890123', 'Carlos Souza', '11934567890', '13500345', '789', 'carlos@drogaria.com'),
('45678901234', 'Ana Lima', '11945678901', '13500456', '101', 'ana@drogaria.com'),
('56789012345', 'Pedro Rocha', '11956789012', '13500567', '202', 'pedro@drogaria.com'),
('67890123456', 'Juliana Reis', '11967890123', '13500678', '303', 'juliana@drogaria.com'),
('78901234567', 'Rafael Costa', '11978901234', '13500789', '404', 'rafael@drogaria.com'),
('89012345678', 'Fernanda Melo', '11989012345', '13500890', '505', 'fernanda@drogaria.com'),
('90123456789', 'Lucas Martins', '11990123456', '13500901', '606', 'lucas@drogaria.com'),
('01234567890', 'Patrícia Dias', '11901234567', '13501012', '707', 'patricia@drogaria.com');

INSERT INTO compra (NotaFiscal_Entrada, Data_Venda, Valor_Total) VALUES
(1001, '2025-10-01', 150.00),
(1002, '2025-10-02', 200.50),
(1003, '2025-10-03', 75.90),
(1004, '2025-10-04', 320.00),
(1005, '2025-10-05', 180.75),
(1006, '2025-10-06', 90.00),
(1007, '2025-10-07', 210.30),
(1008, '2025-10-08', 60.00),
(1009, '2025-10-09', 130.40),
(1010, '2025-10-10', 250.00);

INSERT INTO medicamento (Cod_Med, Nome_Med, Desc_Med, DataVal_Med, Qtd_Med, Valor_Med) VALUES
(1, 'Paracetamol', 'Analgésico e antitérmico', '2026-12-31', 100, 5.50),
(2, 'Ibuprofeno', 'Anti-inflamatório', '2026-11-30', 80, 7.20),
(3, 'Dipirona', 'Analgésico potente', '2026-10-15', 120, 4.80),
(4, 'Omeprazol', 'Antiácido', '2027-01-01', 60, 10.00),
(5, 'Amoxicilina', 'Antibiótico', '2026-09-20', 50, 15.00),
(6, 'Loratadina', 'Antialérgico', '2026-08-10', 70, 8.90),
(7, 'Simeticona', 'Antigases', '2026-07-25', 90, 6.30),
(8, 'Cetoconazol', 'Antifúngico', '2026-06-30', 40, 12.50),
(9, 'Metformina', 'Antidiabético', '2026-05-15', 100, 9.80),
(10, 'Losartana', 'Antihipertensivo', '2026-04-01', 110, 11.20);

INSERT INTO laboratorio (CNPJ_Lab, Nome_Lab, Telefone_Lab, Cep_Lab, Num_Lab, Email_Lab) VALUES
('12345678000101', 'LabVida', '1123456789', '13500123', 101, 'contato@labvida.com'),
('23456789000102', 'BioPharma', '1123456790', '13500234', 102, 'suporte@biopharma.com'),
('34567890000103', 'Farmatec', '1123456801', '13500345', 103, 'info@farmatec.com'),
('45678901000104', 'MedLab', '1123456812', '13500456', 104, 'vendas@medlab.com'),
('56789012000105', 'Quimifarma', '1123456823', '13500567', 105, 'quimica@quimifarma.com'),
('67890123000106', 'PharmaPlus', '1123456834', '13500678', 106, 'plus@pharmaplus.com'),
('78901234000107', 'BioGen', '1123456845', '13500789', 107, 'biogen@biogen.com'),
('89012345000108', 'LabClin', '1123456856', '13500890', 108, 'clinico@labclin.com'),
('90123456000109', 'NeoPharma', '1123456867', '13500901', 109, 'neopharma@neopharma.com'),
('01234567000110', 'Farmalab', '1123456878', '13501012', 110, 'farmalab@farmalab.com');

-- 2. VENDA (deve vir ANTES de drogaria, pois drogaria depende dela)
-- Especificando os IDs para garantir que sejam 1-10
INSERT INTO venda (NotaFiscal_Saida, Data_Venda, Valor_Venda, Cod_Rastreio) VALUES
(1, '2025-10-11', 55.00, 'RAST123456'),
(2, '2025-10-12', 72.00, 'RAST234567'),
(3, '2025-10-13', 48.00, 'RAST345678'),
(4, '2025-10-14', 100.00, 'RAST456789'),
(5, '2025-10-15', 150.00, 'RAST567890'),
(6, '2025-10-16', 80.00, 'RAST678901'),
(7, '2025-10-17', 65.00, 'RAST789012'),
(8, '2025-10-18', 90.00, 'RAST890123'),
(9, '2025-10-19', 120.00, 'RAST901234'),
(10, '2025-10-20', 110.00, 'RAST012345');

-- 3. ITEM_VENDA (depende de nada, mas é referenciada por outras)
INSERT INTO item_venda (Nome_ItemVenda, Desc_ItemVenda, DataVal_ItemVenda, Qtd_ItemVenda, Valor_ItemVenda) VALUES
('Paracetamol 500mg', 'Comprimidos para dor e febre', '2026-12-31', 20, 5.50),
('Ibuprofeno 400mg', 'Anti-inflamatório em cápsulas', '2026-11-30', 15, 7.20),
('Dipirona 1g', 'Comprimidos analgésicos', '2026-10-15', 25, 4.80),
('Omeprazol 20mg', 'Cápsulas para refluxo', '2027-01-01', 10, 10.00),
('Amoxicilina 500mg', 'Antibiótico em cápsulas', '2026-09-20', 12, 15.00),
('Loratadina 10mg', 'Antialérgico em comprimidos', '2026-08-10', 18, 8.90),
('Simeticona 125mg', 'Comprimidos para gases', '2026-07-25', 22, 6.30),
('Cetoconazol 200mg', 'Antifúngico oral', '2026-06-30', 8, 12.50),
('Metformina 850mg', 'Comprimidos para diabetes', '2026-05-15', 30, 9.80),
('Losartana 50mg', 'Antihipertensivo em comprimidos', '2026-04-01', 28, 11.20);

-- 4. ITEM (depende de nada, mas é referenciada)
INSERT INTO item (Nome_Item, Desc_Item, DataVal_Item, Qtd_Item, Valor_Item) VALUES
('Caixa de Luvas', 'Luvas descartáveis tamanho M', '2026-12-31', 50, 25.00),
('Álcool Gel', 'Álcool 70% antisséptico', '2026-11-30', 100, 12.50),
('Termômetro Digital', 'Termômetro infravermelho', '2027-01-15', 30, 45.00),
('Máscara Cirúrgica', 'Máscara descartável tripla camada', '2026-10-10', 200, 0.80),
('Seringa 5ml', 'Seringa estéril sem agulha', '2026-09-20', 150, 1.20),
('Algodão', 'Algodão hidrófilo rolo 500g', '2026-08-25', 60, 8.90),
('Esparadrapo', 'Esparadrapo microporoso 10m', '2026-07-30', 40, 6.50),
('Curativo Adesivo', 'Band-aid transparente', '2026-06-15', 300, 0.50),
('Gaze Estéril', 'Pacote com 100 unidades', '2026-05-10', 80, 10.00),
('Oxímetro', 'Medidor de saturação de oxigênio', '2026-04-05', 25, 55.00);

-- 5. CATALOGO_MEDICAMENTO (depende de medicamento)
INSERT INTO catalogo_medicamento (Nome_CatMed, Desc_CatMed, Valor_CatMed, Cod_Med) VALUES
('Paracetamol 500mg', 'Comprimidos para dor e febre', 5.50, 1),
('Ibuprofeno 400mg', 'Anti-inflamatório em cápsulas', 7.20, 2),
('Dipirona 1g', 'Comprimidos analgésicos', 4.80, 3),
('Omeprazol 20mg', 'Cápsulas para refluxo', 10.00, 4),
('Amoxicilina 500mg', 'Antibiótico em cápsulas', 15.00, 5),
('Loratadina 10mg', 'Antialérgico em comprimidos', 8.90, 6),
('Simeticona 125mg', 'Comprimidos para gases', 6.30, 7),
('Cetoconazol 200mg', 'Antifúngico oral', 12.50, 8),
('Metformina 850mg', 'Comprimidos para diabetes', 9.80, 9),
('Losartana 50mg', 'Antihipertensivo em comprimidos', 11.20, 10);

-- 6. DROGARIA (depende de venda - AGORA pode ser inserida)
INSERT INTO drogaria (CNPJ_Drog, Nome_Drog, Telefone_Drog, Cep_Drog, Num_Drog, Email_Drog, NotaFiscal_Saida) VALUES
('11111111000101', 'Drogaria Central', '11911111111', '13500100', 101, 'central@drogaria.com', 1),
('22222222000102', 'Drogaria Popular', '11922222222', '13500200', 102, 'popular@drogaria.com', 2),
('33333333000103', 'Drogaria Saúde', '11933333333', '13500300', 103, 'saude@drogaria.com', 3),
('44444444000104', 'Drogaria BemEstar', '11944444444', '13500400', 104, 'bemestar@drogaria.com', 4),
('55555555000105', 'Drogaria Vida', '11955555555', '13500500', 105, 'vida@drogaria.com', 5),
('66666666000106', 'Drogaria Econômica', '11966666666', '13500600', 106, 'economica@drogaria.com', 6),
('77777777000107', 'Drogaria Mais', '11977777777', '13500700', 107, 'mais@drogaria.com', 7),
('88888888000108', 'Drogaria Farma', '11988888888', '13500800', 108, 'farma@drogaria.com', 8),
('99999999000109', 'Drogaria São João', '11999999999', '13500900', 109, 'saojoao@drogaria.com', 9),
('00000000000110', 'Drogaria Paulista', '11900000000', '13501000', 110, 'paulista@drogaria.com', 10);

-- 7. TABELAS DE RELACIONAMENTO (sempre por último)
INSERT INTO realiza_compra (CPF, NotaFiscal_Entrada) VALUES
('12345678901', 1001),
('23456789012', 1002),
('34567890123', 1003),
('45678901234', 1004),
('56789012345', 1005),
('67890123456', 1006),
('78901234567', 1007),
('89012345678', 1008),
('90123456789', 1009),
('01234567890', 1010);

INSERT INTO possuiCompraItem (NotaFiscal_Entrada, Cod_Item) VALUES
(1001, 1),
(1002, 2),
(1003, 3),
(1004, 4),
(1005, 5),
(1006, 6),
(1007, 7),
(1008, 8),
(1009, 9),
(1010, 10);

INSERT INTO compoe_item (Cod_Item, Cod_CatMed) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

INSERT INTO forneceCatalogoLab (Cod_CatMed, CNPJ_Lab) VALUES
(1, '12345678000101'),
(2, '23456789000102'),
(3, '34567890000103'),
(4, '45678901000104'),
(5, '56789012000105'),
(6, '67890123000106'),
(7, '78901234000107'),
(8, '89012345000108'),
(9, '90123456000109'),
(10, '01234567000110');

INSERT INTO compoe_medicamento (Cod_CatMed, Cod_Med) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

INSERT INTO compoe_estoque (Cod_Med, Cod_ItemVenda) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

INSERT INTO contemVenda (Cod_ItemVenda, NotaFiscal_Saida) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

INSERT INTO realiza_venda (CPF, NotaFiscal_Saida) VALUES
('12345678901', 1),
('23456789012', 2),
('34567890123', 3),
('45678901234', 4),
('56789012345', 5),
('67890123456', 6),
('78901234567', 7),
('89012345678', 8),
('90123456789', 9),
('01234567890', 10);

ALTER TABLE funcionario
ADD Senha VARCHAR(30) NOT NULL;

UPDATE funcionario SET Senha = 'senha123' WHERE CPF = '12345678901';
UPDATE funcionario SET Senha = 'senha234' WHERE CPF = '23456789012';
UPDATE funcionario SET Senha = 'senha345' WHERE CPF = '34567890123';
UPDATE funcionario SET Senha = 'senha456' WHERE CPF = '45678901234';
UPDATE funcionario SET Senha = 'senha567' WHERE CPF = '56789012345';
UPDATE funcionario SET Senha = 'senha678' WHERE CPF = '67890123456';
UPDATE funcionario SET Senha = 'senha789' WHERE CPF = '78901234567';
UPDATE funcionario SET Senha = 'senha890' WHERE CPF = '89012345678';
UPDATE funcionario SET Senha = 'senha901' WHERE CPF = '90123456789';
UPDATE funcionario SET Senha = 'senha012' WHERE CPF = '01234567890';

CREATE OR REPLACE VIEW vw_relatorio_vendas_detalhadas AS
SELECT
    V.NotaFiscal_Saida,
    V.Data_Venda AS Data_Venda,
    V.Valor_Venda AS Valor_Total_Venda,
    IV.Nome_ItemVenda AS Item_Vendido,
    IV.Qtd_ItemVenda AS Quantidade,      -- CORRIGIDO: Agora usa IV (item_venda)
    IV.Valor_ItemVenda AS Valor_Unitario, -- CORRIGIDO: Agora usa IV (item_venda)
    F.Nome_Fun AS Funcionario_Venda
FROM
    venda V
JOIN
    contemVenda CV ON V.NotaFiscal_Saida = CV.NotaFiscal_Saida
JOIN
    item_venda IV ON CV.Cod_ItemVenda = IV.Cod_ItemVenda
JOIN
    realiza_venda RV ON V.NotaFiscal_Saida = RV.NotaFiscal_Saida
JOIN
    funcionario F ON RV.CPF = F.CPF;
 
DELIMITER $$
CREATE PROCEDURE sp_atualiza_estoque_apos_compra (
    p_Cod_Med INT,
    p_Qtd_Comprada INT
)
BEGIN
    DECLARE v_estoque_atual INT;

    -- 1. Verifica o estoque atual
    SELECT Qtd_Med INTO v_estoque_atual
    FROM medicamento
    WHERE Cod_Med = p_Cod_Med;

    -- 2. Atualiza a quantidade do medicamento
    UPDATE medicamento
    SET Qtd_Med = v_estoque_atual + p_Qtd_Comprada
    WHERE Cod_Med = p_Cod_Med;

    -- 3. Retorna a confirmação e novo estoque
    SELECT CONCAT(
        'Estoque do medicamento (Cod: ', p_Cod_Med, ') atualizado. ',
        'Quantidade adicionada: ', p_Qtd_Comprada,
        '. Novo Estoque Total: ', v_estoque_atual + p_Qtd_Comprada
    ) AS Mensagem_Estoque;

END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_baixa_estoque_apos_venda
AFTER INSERT ON contemVenda
FOR EACH ROW
BEGIN
    DECLARE v_cod_med INT;
    DECLARE v_qtd_vendida INT;

    -- 1. Pega o Cod_Med associado ao Item_Venda
    SELECT Cod_Med INTO v_cod_med
    FROM compoe_estoque
    WHERE Cod_ItemVenda = NEW.Cod_ItemVenda;

    -- 2. Pega a quantidade vendida da tabela item_venda
    SELECT Qtd_ItemVenda INTO v_qtd_vendida
    FROM item_venda
    WHERE Cod_ItemVenda = NEW.Cod_ItemVenda;

    -- 3. Diminui a quantidade no estoque de medicamento
    IF v_cod_med IS NOT NULL THEN
        UPDATE medicamento
        SET Qtd_Med = Qtd_Med - v_qtd_vendida
        WHERE Cod_Med = v_cod_med;
    END IF;
END $$
DELIMITER ;