CREATE TABLE FI001 (
	FI001_id INTEGER,
	FI001_ds_receita varchar(255),
	FI001_vl_receita NUMERIC(17,2),
	FI001_nr_ano NUMERIC(4),
	FI001_nr_mes NUMERIC(2),
	FI001_dt_receita datetime,
	PRIMARY KEY("FI001_id" AUTOINCREMENT));

CREATE TABLE FI002 (
	FI002_id INTEGER,
	FI002_ds_despesa varchar(255),
	FI002_vl_despesa NUMERIC(17,2),
	FI002_nr_ano NUMERIC(4),
	FI002_nr_mes NUMERIC(2),
	FI002_dt_despesa datetime,
	PRIMARY KEY("FI002_id" AUTOINCREMENT));

INSERT INTO FI001 VALUES (1, 'Teste 01', 130.10, 2022, 10, datetime());
INSERT INTO FI001 VALUES (2, 'Teste 02', 230.10, 2022, 10, datetime());
INSERT INTO FI001 VALUES (3, 'Teste 03', 360.10, 2022, 10, datetime());

INSERT INTO FI002 VALUES (1, 'Teste 01', 130.10, 2022, 10, datetime());
INSERT INTO FI002 VALUES (2, 'Teste 02', 230.10, 2022, 10, datetime());
INSERT INTO FI002 VALUES (3, 'Teste 03', 360.10, 2022, 10, datetime());


