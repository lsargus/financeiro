CREATE TABLE Receita (
	id interge NOT NULL,
	descricao varchar(255),
	valor NUMERIC(17,2),
	'data' DATE,
	CONSTRAINT receita_pkey PRIMARY KEY (id));


