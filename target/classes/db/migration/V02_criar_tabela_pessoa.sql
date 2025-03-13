CREATE TABLE pessoa
(
    nome  VARCHAR(70) NOT NULL,
    ativo BOOLEAN NOT NULL,
    endereco VARCHAR (100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, endereco) VALUES ('Eduardo', true, 'Estrada do Jacarandá');
INSERT INTO pessoa (nome, ativo) VALUES ('Joyce', true);
INSERT INTO pessoa (nome, ativo, endereco) VALUES ('Gabigol', true, 'Rua azul');
INSERT INTO pessoa (nome, ativo, endereco) VALUES ('D4rk', true, 'Cuiaba');