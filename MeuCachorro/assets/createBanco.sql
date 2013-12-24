
CREATE TABLE EspecieAnimal (
seqEspecie integer PRIMARY KEY,
descrEspecie varchar(200)
);


CREATE TABLE CorAnimal (
seqCor integer PRIMARY KEY,
descrCor varchar(200)
);

CREATE TABLE RacaAnimal (
seqRaca integer PRIMARY KEY,
descrRaca varchar(200),
seqEspecie int,
FOREIGN KEY(seqEspecie) REFERENCES EspecieAnimal (seqEspecie)
);

CREATE TABLE CadastroAnimal (
seqAnimal integer PRIMARY KEY,
seqCor int,
seqRaca int,
nomeAnimal varchar(200),
dtNascAnimal date,
sexo char(1),
registro varchar(40),
observacoes varchar(500),
falecido boolean,
FOREIGN KEY(seqCor) REFERENCES CorAnimal (seqCor),
FOREIGN KEY(seqRaca) REFERENCES RacaAnimal (seqRaca)
);

CREATE TABLE ControlePeso (
seqPeso integer PRIMARY KEY,
seqAnimal int,
peso int,
dataPesagem date,
FOREIGN KEY(seqAnimal) REFERENCES CadastroAnimal (seqAnimal)
);

CREATE TABLE ClinicaVet (
seqClinica integer PRIMARY KEY,
nome varchar(300),
endereco varchar(300),
telefone varchar(30),
email varchar(50)
);

CREATE TABLE Veterinario (
seqVeterinario integer PRIMARY KEY,
seqClinica integer,
nome varchar(300),
registro varchar(40),
email varchar(50),
FOREIGN KEY (seqClinica) REFERENCES ClinicaVet (seqClinica)
);
