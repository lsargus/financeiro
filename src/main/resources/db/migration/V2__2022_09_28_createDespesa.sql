CREATE TABLE Despesa (
	id interge NOT NULL,
	descricao varchar(255),
	valor NUMERIC(17,2),
	'data' DATE,
	CONSTRAINT despesa_pkey PRIMARY KEY (id));

