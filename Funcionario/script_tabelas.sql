SET FOREIGN_KEY_CHECKS=0;




CREATE TABLE Cargo
(
	id BIGINT NOT NULL,
	nome VARCHAR(50) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Cargo_id(id),
	UNIQUE UQ_Cargo_nome(nome)

) 
;


CREATE TABLE Funcionario
(
	Nome VARCHAR(100) NOT NULL,
	id BIGINT NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	id_cargo BIGINT NOT NULL,
	PRIMARY KEY (id, id_cargo),
	UNIQUE UQ_Funcionario_email(email),
	UNIQUE UQ_Funcionario_id(id),
	KEY (id_cargo)

) 
;



SET FOREIGN_KEY_CHECKS=1;


ALTER TABLE Funcionario ADD CONSTRAINT FK_Funcionario_Cargo 
	FOREIGN KEY (id_cargo) REFERENCES Cargo (id)
;
