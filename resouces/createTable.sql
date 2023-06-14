-- Link figma -- Diagrama entidade-relacionamento
-- https://www.figma.com/file/ecSNooXLKXGCKre6Xb04ye/Untitled?type=whiteboard&node-id=0%3A1&t=PHSHKMndjNvNFST3-1

-- DROP DAS TABELAS
DROP TABLE IF EXISTS itemVenda; 
DROP TABLE IF EXISTS venda;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS formaPagamento;
DROP TABLE IF EXISTS vendedor;
DROP TABLE IF EXISTS fabricante;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS anime;
DROP TABLE IF EXISTS cidade;
DROP TABLE IF EXISTS timeTorcedor;

-- CRIAÇÃO DAS TABELAS

CREATE TABLE anime (
idAnime INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE cidade (
idCidade INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE timeTorcedor (
idTimeTorcedor INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE cliente ( 
idCliente INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
telefone INT(20),
idAnime INT,
idCidade INT,
idTimeTorcedor INT,
possuiDesconto BOOL DEFAULT FALSE,
FOREIGN KEY (idAnime) REFERENCES anime(idAnime),
FOREIGN KEY (idCidade) REFERENCES cidade(idCidade),
FOREIGN KEY (idTimeTorcedor) REFERENCES timeTorcedor(idTimeTorcedor)
);

CREATE TABLE categoria (
idCategoria INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE fabricante (
idFabricante INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
endereco VARCHAR(100)
);

CREATE TABLE vendedor (
idVendedor INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE formaPagamento (
idFormaPagamento INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100)
);

CREATE TABLE produto ( -- Modificar no back
idProduto INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
preco DECIMAL(10,2),
idCategoria INT,
idFabricante INT,
qtd INT,
FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria),
FOREIGN KEY (idFabricante) REFERENCES fabricante(idFabricante)
);

CREATE TABLE venda (
idVenda INT PRIMARY KEY AUTO_INCREMENT,
idCliente INT,
idVendedor INT,
idFormaPagamento INT,
dataVenda DATE,
valorFinal DECIMAL(10, 2),
porcDesconto DECIMAL(10, 2),
statusPago BOOLEAN,
FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
FOREIGN KEY (idVendedor) REFERENCES vendedor(idVendedor),
FOREIGN KEY (idFormaPagamento) REFERENCES formaPagamento(idFormaPagamento)
); 

CREATE TABLE itemVenda ( 
idItemVenda INT PRIMARY KEY AUTO_INCREMENT,
idVenda INT,
idProduto INT,
quantidade INT,
preco DECIMAL(10, 2),
FOREIGN KEY (idVenda) REFERENCES venda(idVenda),
FOREIGN KEY (idProduto) REFERENCES produto(idProduto)
);

-- INSERTS --

-- INSERT -- ANIME
INSERT INTO anime (nome) VALUES ('N/A'); -- Requisito
INSERT INTO anime (nome) VALUES ('One Piece'); -- Requisito
INSERT INTO anime (nome) VALUES ('Naruto');
INSERT INTO anime (nome) VALUES ('Attack on Titan');
INSERT INTO anime (nome) VALUES ('Dragon Ball');
INSERT INTO anime (nome) VALUES ('Death Note');
INSERT INTO anime (nome) VALUES ('Your Name');
INSERT INTO anime (nome) VALUES ('Hunter X Hunter');

-- INSERT -- CIDADE
INSERT INTO cidade (nome) VALUES ('Sousa'); -- Requisito
INSERT INTO cidade (nome) VALUES ('João Pessoa');
INSERT INTO cidade (nome) VALUES ('São Paulo');
INSERT INTO cidade (nome) VALUES ('Rio de Janeiro');
INSERT INTO cidade (nome) VALUES ('Curitiba');

-- INSERT -- TIME TORCEDOR
INSERT INTO timeTorcedor (nome) VALUES ('N/A'); -- Requisito
INSERT INTO timeTorcedor (nome) VALUES ('Flamengo'); -- Requisito
INSERT INTO timeTorcedor (nome) VALUES ('São Paulo FC');
INSERT INTO timeTorcedor (nome) VALUES ('Palmeiras');
INSERT INTO timeTorcedor (nome) VALUES ('Vasco');
INSERT INTO timeTorcedor (nome) VALUES ('Botafogo');
INSERT INTO timeTorcedor (nome) VALUES ('Sousa');

-- INSERT -- FORMA DE PAGAMENTO
INSERT INTO formaPagamento (nome) VALUES ('cartão'); -- Requisito
INSERT INTO formaPagamento (nome) VALUES ('boleto'); -- Requisito
INSERT INTO formaPagamento (nome) VALUES ('pix'); -- Requisito
INSERT INTO formaPagamento (nome) VALUES ('berries'); -- Requisito
INSERT INTO formaPagamento (nome) VALUES ('dinheiro');

-- INSERT -- CLIENTE
INSERT INTO cliente (nome, telefone, idAnime, idCidade, idTimeTorcedor) VALUES ('João', 123456789, 1, 1, 1);
INSERT INTO cliente (nome, telefone, idAnime, idCidade, idTimeTorcedor) VALUES ('Maria', 987654321, 2, 2, 2);
INSERT INTO cliente (nome, telefone, idAnime, idCidade, idTimeTorcedor) VALUES ('Pedro', 555555555, 3, 3, 3);
INSERT INTO cliente (nome, telefone, idAnime, idCidade, idTimeTorcedor) VALUES ('Ana', 111111111, 1, 2, 3);
INSERT INTO cliente (nome, telefone, idAnime, idCidade, idTimeTorcedor) VALUES ('Lucas', 999999999, 2, 3, 1);

-- INSERT -- CATEGORIA
INSERT INTO categoria (nome) VALUES ('Eletrônicos');
INSERT INTO categoria (nome) VALUES ('Roupas');
INSERT INTO categoria (nome) VALUES ('Alimentos');

-- INSERT -- FABRICANTE
INSERT INTO fabricante (nome, endereco) VALUES ('Fabricante A', 'Mari'); -- Requisito
INSERT INTO fabricante (nome, endereco) VALUES ('Fabricante B', 'Botafogo');
INSERT INTO fabricante (nome, endereco) VALUES ('Fabricante C', 'Pedras de fogo');

-- INSERT -- PRODUTO
INSERT INTO produto (nome, preco, idCategoria, idFabricante, qtd) VALUES ('Celular', 999.99, 1, 1, 67);
INSERT INTO produto (nome, preco, idCategoria, idFabricante, qtd) VALUES ('Camiseta', 29.99, 2, 2, 89);
INSERT INTO produto (nome, preco, idCategoria, idFabricante, qtd) VALUES ('Arroz', 4.99, 3, 3, 44);

-- INSERT -- VENDEDOR
INSERT INTO vendedor (nome) VALUES ('Guilherme Kakaroto');
INSERT INTO vendedor (nome) VALUES ('João Pé De Feijão');
INSERT INTO vendedor (nome) VALUES ('Gabriel El El El');

-- VIEWS --

-- DROP DE VIEWS
DROP VIEW IF EXISTS produtoView;
DROP VIEW IF EXISTS clienteView;

-- CRIAÇÃO DE VIEWS
CREATE VIEW produtoView AS
SELECT p.idProduto AS idProduto, 
		p.nome, 
        p.preco, 
        c.nome AS nomeCategoria, 
        f.nome AS nomeFabricante,
        f.endereco AS enderecoFabricante, -- Adicionar no ProdutoView e ProdutoViewDAO
        p.qtd AS quantidade -- Refazer no ProdutoView e ProdutoViewDAO
FROM produto p
JOIN categoria c ON p.idCategoria = c.idCategoria
JOIN fabricante f ON p.idFabricante = f.idFabricante;

CREATE VIEW clienteView AS
SELECT c.idCliente, 
		c.nome, 
		c.telefone, 
        a.nome AS nomeAnime, 
        cid.nome AS nomeCidade,
        tf.nome AS nomeTimeTorcedor
FROM cliente c
JOIN anime a ON c.idAnime = a.idAnime
JOIN cidade cid ON c.idCidade = cid.idCidade
JOIN timeTorcedor tf ON c.idTimeTorcedor = tf.idTimeTorcedor;

DROP PROCEDURE IF EXISTS GenerateSalesReport

DELIMITER //

CREATE PROCEDURE GenerateSalesReport(IN p_month INT, IN p_vendorID INT)
BEGIN
    SELECT c.nome AS nome_cliente,
           COUNT(DISTINCT v.idVenda) AS total_vendas,
           SUM(iv.quantidade) AS total_produtos,
           SUM(iv.quantidade * iv.preco) AS total_pago
    FROM cliente c
    INNER JOIN venda v ON c.idCliente = v.idCliente
    INNER JOIN itemVenda iv ON v.idVenda = iv.idVenda
    WHERE MONTH(v.dataVenda) = p_month AND v.idVendedor = p_vendorID
    GROUP BY c.idCliente;
END //

DELIMITER ;

