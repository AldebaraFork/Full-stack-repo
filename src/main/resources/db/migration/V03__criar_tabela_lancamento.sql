CREATE TABLE IF NOT EXISTS lancamento (
                                          codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          descricao VARCHAR(255) NOT NULL,
                                          data_vencimento DATE NOT NULL,
                                          data_pagamento DATE,
                                          valor DECIMAL(10,2) NOT NULL,
                                          observacao VARCHAR(500),
                                          tipo VARCHAR(20) NOT NULL,
                                          codigo_categoria BIGINT NOT NULL,
                                          codigo_pessoa BIGINT NOT NULL,
                                          CONSTRAINT fk_lancamento_categoria FOREIGN KEY (codigo_categoria)
                                              REFERENCES categoria(codigo) ON DELETE RESTRICT ON UPDATE CASCADE,
                                          CONSTRAINT fk_lancamento_pessoa FOREIGN KEY (codigo_pessoa)
                                              REFERENCES pessoa(codigo) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;