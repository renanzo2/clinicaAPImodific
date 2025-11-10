use clinica;

ALTER TABLE consulta 
ADD COLUMN data_consulta DATE,
ADD COLUMN hora_consulta TIME;