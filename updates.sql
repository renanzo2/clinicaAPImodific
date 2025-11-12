use clinica;

ALTER TABLE consulta 
ADD COLUMN data_consulta DATE,
ADD COLUMN hora_consulta TIME;

ALTER TABLE 
consulta ADD COLUMN tipo VARCHAR(50);