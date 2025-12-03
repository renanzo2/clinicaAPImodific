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
