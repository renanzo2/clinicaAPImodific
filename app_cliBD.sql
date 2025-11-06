CREATE DATABASE IF NOT EXISTS clinica;
USE clinica;

CREATE TABLE Dono(
    CPF varchar(11) PRIMARY KEY UNIQUE NOT NULL,
    Nome varchar(30),
    Endereco varchar(255),
    data_nasc date
);

CREATE TABLE Animal(
    ID int PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    Especie varchar(20) not null,
    CPF_dono varchar(11) not null,
    Nome varchar(100) not null,
    Idade int not null,
    Porte varchar(40) not null, 
    CONSTRAINT FK_Dono_CPF_Animal
    FOREIGN KEY (CPF_dono) REFERENCES Dono (CPF)
);

CREATE TABLE veterinario(
    CRMV varchar(13) primary key not null,
    nome varchar(100) not null,
    idade int not null,
    data_graduacao date not null
);

CREATE TABLE Especialidade(
	IDespecialidade int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome varchar(100)
);

CREATE TABLE Certificacao(
	NumeroRegistro varchar(30) PRIMARY KEY NOT NULL,
    DataObtencao date,
    InstituicaoCertificadora varchar(50),
    CRMV_certif varchar(13),
    ID_especialidade int,
    FOREIGN KEY (CRMV_certif) REFERENCES veterinario (CRMV),
    FOREIGN KEY (ID_especialidade) REFERENCES Especialidade (IDespecialidade)
);

CREATE TABLE consulta(
    id int primary key auto_increment not null,
    diagnostico varchar(255) not null,
    id_animal int not null,
    CRMV_veterinario varchar(13) not null,
    CONSTRAINT fk_CRMV_VET
    FOREIGN KEY (CRMV_veterinario) REFERENCES veterinario(CRMV),
    CONSTRAINT FK_Animal_Consulta
    FOREIGN KEY (id_animal) REFERENCES Animal(ID)
);

CREATE TABLE tratamento(
    id int primary key auto_increment not null,
    antibiotico boolean not null,
    id_consulta int not null,
    descricao_tratamento varchar(255) not null,
    CONSTRAINT FK_id_consulta
    FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);

CREATE TABLE log_auditoria (
	id_log INT PRIMARY KEY AUTO_INCREMENT, 
    acao_realizada VARCHAR(255) NOT NULL,
    tabela_afetada VARCHAR (100),
    data_hora DATETIME NOT NULL
);



INSERT INTO Dono (CPF, Nome, Endereco, data_nasc) VALUES 
("12345678912", "Renan Watanabe", "Aguas Claras", "2006-06-26"), 
("12345678934", "Gabriel Antônio", "Aguas Claras", "2006-04-07"), 
("12345678901", "Kauan Henrique", "Ceilândia", "2005-07-06");


INSERT INTO Animal(Especie, CPF_dono, Nome, Idade, Porte) VALUES
("Cachorro", "12345678912", "Mack", 11, "Médio"),
("Coelho", "12345678934", "Mike nelson", 4, "Pequeno"),
("Jibóia", "12345678901", "Princesa", 3, "Grande");

INSERT INTO veterinario (CRMV, nome, idade, data_graduacao) VALUES
("CRMV-DF 07439", "Victor Caldas", 19, "2023-09-08");

INSERT INTO veterinario (CRMV, nome, idade, data_graduacao) VALUES
('CRMV-GO 11223', 'Dra. Juliana Martins', 28, '2019-12-15');

INSERT INTO consulta(diagnostico, id_animal, CRMV_veterinario) VALUES 
("Sarna", 1, "CRMV-DF 07439"), 
("Gases", 2, "CRMV-DF 07439"),
("Pneumonia bacteriana", 3, "CRMV-GO 11223");


INSERT INTO tratamento(antibiotico, id_consulta, descricao_tratamento) VALUES
(0, 1, 'Tratar com banhos periódicos usando um shampoo anti sarna de sua preferencia.'),
(0, 2, 'Fazer massagens abdominais periódicas monitorando a melhora do animal ou não. Caso não haja melhora, retorne para mais exames.'),
(1, 3, 'Tomar o antibiótico de 8 em 8 horas, aumentar a temperatura e umidade do recinto, e reduzir o estresse do animal.');

INSERT INTO Especialidade (IDespecialidade, nome) VALUES
(1, 'Clínica Geral de Pequenos Animais'),
(2, 'Cirurgia Veterinária'),
(3, 'Animais Silvestres e Exóticos');

INSERT INTO Certificacao (NumeroRegistro, DataObtencao, InstituicaoCertificadora, CRMV_certif, ID_especialidade) VALUES
('REG-DF-24-001A', '2024-03-10', 'Conselho Federal de Medicina Veterinária', 'CRMV-DF 07439', 1),
('REG-DF-25-009B', '2025-07-22', 'Instituto de Biologia da Conservação', 'CRMV-DF 07439', 2);

INSERT INTO Certificacao (NumeroRegistro, DataObtencao, InstituicaoCertificadora, CRMV_certif, ID_especialidade) VALUES
('REG-GO-20-005C', '2020-08-01', 'Academia Brasileira de Cirurgia Vet', 'CRMV-GO 11223', 3);


CREATE INDEX idx_animal_nome ON Animal(Nome);

CREATE INDEX idx_dono_cpf ON dono(CPF);

DELIMITER $$

CREATE TRIGGER trg_log_delete_consulta
AFTER DELETE ON consulta
FOR EACH ROW
BEGIN 
	INSERT INTO log_auditoria (
		acao_realizada,
        tabela_afetada,
        id_registro_deletado,
        data_hora
	)
    VALUES (
		'DELETE na tabela consulta',
        'consulta',
        OLD.id,
        NOW()
	);
END$$

DELIMITER ;
    

DELIMITER $$

CREATE TRIGGER trg_checar_consultas_before_delete_vet
BEFORE DELETE ON veterinario
FOR EACH ROW
BEGIN 
	DECLARE consulta_count INT;
    
    SELECT COUNT(*)
    INTO consulta_count
    FROM consulta
    WHERE CRMV_veterinario = OLD.CRMV;
    
    IF consulta_count > 0 THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'ERRO:  Este veterinario nao pode ser deletado pois esta associado a consultas no histórico.';
	END IF;
    
END$$

DELIMITER ;


CREATE VIEW v_ConsultasComp AS 
SELECT 
	c.id AS consulta_id,
    c.diagnostico,
    a.Nome AS nome_animal,
    a.Especie,
    d.Nome AS nome_dono,
    d.CPF AS cpf_dono,
    v.nome AS nome_veterinario,
    v.CRMV
FROM 
	consulta c
JOIN 
	Animal a ON c.id_animal = a.ID
JOIN
	Dono d ON a.CPF_dono = d.CPF
JOIN 
	veterinario v ON c.CRMV_veterinario = CRMV;
    
CREATE VIEW v_VetEspecialidades AS 
SELECT 
	v.nome AS nome_veterinario,
    v.CRMV,
    e.nome AS nome_especialidade
FROM
	veterinario v
JOIN
	Certificacao c ON v.CRMV = c.CRMV_certif
JOIN
	Especialidade e ON c.ID_especialidade = e.IDespecialidade;
    
    
DELIMITER $$
CREATE FUNCTION fnc_gerar_roximo_rfid_animal()
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
	DECLARE proximo_numero INT;
	DECLARE proximo_rfid VARCHAR(10);
	SELECT COALESCE(MAX(CAST(SUBSTRING(RFID, 5) AS UNSIGNED)), 0)
	INTO proximo_numero
	FROM Animal
	WHERE RFID LIKE 'PET-%';

	SET proximo_numero = proximo_numero + 1;

	SET proximo_rfid = CONCAT('PET-', LPAD(proximo_numero, 4, '0'));

	RETURN proximo_rfid;
END$$

DELIMITER ;

DROP VIEW IF EXISTS v_ConsultasComp;

ALTER TABLE consulta DROP FOREIGN KEY FK_Animal_Consulta;

ALTER TABLE Animal
	DROP PRIMARY KEY,
    DROP COLUMN ID,
    ADD COLUMN RFID VARCHAR(10) NOT NULL;
    
UPDATE Animal SET RFID = 'PET-0001' WHERE Nome = 'Mack';
UPDATE Animal SET RFID = 'PET-0002' WHERE Nome = 'Mike nelson';
UPDATE Animal SET RFID = 'PET-0003' WHERE Nome = 'Princesa';

ALTER TABLE Animal ADD PRIMARY KEY(RFID);

ALTER TABLE consulta MODIFY COLUMN id_animal VARCHAR(10) NOT NULL;

UPDATE consulta SET id_animal = 'PET-0001' WHERE id_animal = '1';
UPDATE consulta SET id_animal = 'PET-0002' WHERE id_animal = '2';
UPDATE consulta SET id_animal = 'PET-0003' WHERE id_animal = '3';


ALTER TABLE consulta
	ADD CONSTRAINT FK_Animal_Consulta
    FOREIGN KEY (id_animal) REFERENCES Animal(RFID);
    
CREATE VIEW v_ConsultasComp AS
SELECT
	c.id AS consulta_id,
    c.diagnostico,
    a.Nome AS nome_animal,
    a.Especie,
    d.Nome AS nome_dono,
    d.CPF AS cpf_dono,
    v.nome AS nome_veterinario,
    v.CRMV
FROM 
	consulta c
JOIN 
	Animal a ON c.id_animal = a.RFID
JOIN 
	Dono d ON a.CPF_dono = d.CPF
JOIN
	veterinario v ON c.CRMV_veterinario = v.CRMV;
    

	SET FOREIGN_KEY_CHECKS=0;
    
    ALTER TABLE consulta ADD COLUMN uuid_id VARCHAR(36);
    ALTER TABLE tratmento ADD COLUMN uuid_id VARCHAR(36);
    ALTER TABLE tratamento ADD COLUMN uuid_id_consul VARCHAR(36);
    
    UPDATE consulta SET uuid_id = UUID();
    UPDATE tratamento SET uuid_id = UUID();