CREATE TABLE Receita (
	id interge NOT NULL,
	descricao varchar(255),
	valor NUMERIC(17,2),
	'data' DATE,
	CONSTRAINT receita_pkey PRIMARY KEY (id));

CREATE TABLE Despesa (
	id interge NOT NULL,
	descricao varchar(255),
	valor NUMERIC(17,2),
	'data' DATE,
	CONSTRAINT despesa_pkey PRIMARY KEY (id));

INSERT INTO Receita VALUES (1, 'Teste 01', 130.10, DATE());
INSERT INTO Receita VALUES (2, 'Teste 02', 230.10, DATE());
INSERT INTO Receita VALUES (3, 'Teste 03', 360.10, DATE());
INSERT INTO Receita VALUES (4, 'Teste 04', 450.10, DATE());
INSERT INTO Receita VALUES (5, 'Teste 05', 501.10, DATE());


