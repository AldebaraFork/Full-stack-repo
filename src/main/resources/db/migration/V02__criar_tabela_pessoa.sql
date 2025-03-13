CREATE TABLE pessoa
(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome  VARCHAR(70) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cep VARCHAR(10),
    cidade VARCHAR(100),
    estado VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Eduardo', true, 'Estrada do Jacarandá', '123', 'Apto 101', 'Centro', '12345-678', 'São Paulo', 'SP');

INSERT INTO pessoa (nome, ativo) VALUES ('Joyce', true);

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Gabigol', true, 'Rua azul', '456', 'Casa 2', 'Bairro Novo', '87654-321', 'Rio de Janeiro', 'RJ');

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('D4rk', false, 'Cuiaba', '789', 'Sobrado', 'Centro', '54321-876', 'Cuiabá', 'MT');