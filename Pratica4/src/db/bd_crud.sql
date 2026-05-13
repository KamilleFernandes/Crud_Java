create database bd_crud;
use bd_crud;

CREATE TABLE cliente (
id INT AUTO_INCREMENT PRIMARY KEY not null,
nome VARCHAR(100) not null,
cpf VARCHAR(11) unique not null,
data_nascimento DATE,
telefone VARCHAR(20),
endereco VARCHAR(150),
bairro VARCHAR(100),
cidade VARCHAR(100),
estado VARCHAR(2),
cep VARCHAR(10),
status enum ("ativo", "inativo") not null
);

CREATE TABLE raca (
id_raca int auto_increment primary key not null,
nome_raca varchar(100) not null,
tipo_animal varchar(10) not null,
status enum ("ativo", "inativo")
);

create table animal (
id_animal int auto_increment primary key not null,
nome varchar(100) not null,
data_nascimento date, 
sexo char(1),
cor varchar(10),
observacoes varchar(155),
status enum ("ativo", "inativo"),
id_cliente int not null,
id_raca int not null,
foreign key (id_cliente) 
references cliente (id),
foreign key (id_raca) 
references raca (id_raca)
);
