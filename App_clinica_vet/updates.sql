use clinica;

ALTER TABLE consulta 
ADD COLUMN data_consulta DATE,
ADD COLUMN hora_consulta TIME;

ALTER TABLE 
consulta ADD COLUMN tipo VARCHAR(50);

ALTER TABLE tratamento
DROP FOREIGN KEY FK_id_consulta;

ALTER TABLE tratamento
ADD CONSTRAINT FK_id_consulta
	FOREIGN KEY (id_consulta) REFERENCES consulta(id)
    ON DELETE CASCADE;

ALTER TABLE consulta DROP FOREIGN KEY FK_Animal_Consulta;

ALTER TABLE consulta
ADD CONSTRAINT Fk_Animal_Consulta
FOREIGN KEY (id_animal) REFERENCES Animal(RFID)
ON DELETE CASCADE;


INSERT INTO grupos_usuarios (idgrupo, nome_grupo, descricao) VALUES
(1, 'ADMIN', 'Acesso total ao sistema'),
(2, 'FUNCIONARIO', 'Acessso restrito (leitura e escrita)');

INSERT INTO usuarios (id_usuario, login, senha, nome_completo, id_grupo, ativo) VALUES
(UUID(), 'admin', '****', 'Administrador do Sistema', 1, 1);

INSERT INTO usuarios (id_usuario, login, senha, nome_completo, id_grupo, ativo) VALUES
(UUID(), 'funcionario', '*******', 'Funcionario Padrão', 2, 1);
