CREATE DATABASE prj_bd;
USE prj_bd;
CREATE TABLE Cliente(
idCliente int primary key,
nome varchar(30) not null,
cognome varchar(30) not null,
nomeProfilo varchar(30) not null unique,
email varchar(30) not null unique,
pwd varchar(30) not null,
numeroAcquisti varchar(30)
);

CREATE TABLE Sviluppatore(
idSviluppatore int primary key,
nome varchar(30) not null,
cognome varchar(30) not null,
nomeProfilo varchar(30) not null unique,
email varchar(30) not null unique,
pwd varchar(30) not null,
casaDiProduzione varchar(30) not null
);

CREATE TABLE Videogioco(
idVideogioco int primary key,
titolo varchar(30) not null ,
genere varchar(30) not null,
requisiti varchar(30) not null,
sviluppatore int not null,
dataPubblicazione date,
FOREIGN KEY(sviluppatore) REFERENCES Sviluppatore(idSviluppatore)
on update cascade
on delete no action
);

CREATE TABLE Recensione(
idRecensione int primary key,
contenuto varchar(30) not null,
valutazione int not null,
idCliente int not null,
videogiocoDescritto int not null,
dataPubblicazioneRecensione date, 
FOREIGN KEY(videogiocoDescritto) REFERENCES Videogioco(idVideogioco)
on update cascade
on delete no action,
FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente)
on update cascade
on delete no action
);

CREATE TABLE Acquisto(
idCliente int not null,
idVideogioco int not null,
dataAcquisto date,
FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente)
on update cascade
on delete no action,
FOREIGN KEY(idVideogioco) REFERENCES Videogioco(idVideogioco)
on update cascade
on delete no action
);