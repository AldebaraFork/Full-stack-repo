CREATE TABLE pessoas
(
    nome  VARCHAR(70) NOT NULL,
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    ativo BOOLEAN NOT NULL,
    endereco VARCHAR (100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoas (nome, ativo, endereco) VALUES ('Eduardo', true, 'Estrada do Jacarandá');
INSERT INTO pessoas (nome, ativo) VALUES ('Joyce', true);
INSERT INTO pessoas (nome, ativo, endereco) VALUES ('Gabigol', true, 'Rua azul');
INSERT INTO pessoas (nome, ativo, endereco) VALUES ('D4rk', false, 'Cuiaba');