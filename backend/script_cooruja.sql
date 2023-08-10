create schema cooruja;

use cooruja;

drop table usuario_login;
drop table usuario;
drop table perfil_acesso;


create Table perfil_acesso (
		id int not null auto_increment,
		nome_perfil varchar(50) not null,
		descricao_perfil varchar(200) not null,
		status TINYINT (1)  not null,
		PRIMARY KEY (id)
);

Create Table usuario (
		id int not null auto_increment,
		nome varchar(50) not null,
		sobrenome varchar(50) not null,
		email varchar(100) not null unique key,
		senha varchar(100) not null,
		termos_aceite TINYINT (1)  not null, 
		id_perfil int not null,
		data_cadastro TIMESTAMP DEFAULT now(),
		data_modificacao TIMESTAMP,
		PRIMARY KEY (id),
		foreign key (id_perfil) references perfil_acesso(id)
);

Create Table usuario_login (
		id_usuario int not null,
		data_login  TIMESTAMP NOT NULL DEFAULT now(),
		PRIMARY KEY (id_usuario, data_login),
		foreign key (id_usuario) references usuario(id)
);

create table arquivo (
	id_arquivo int not null,
	id_usuario int not null,
	tp_armazenamento int null DEFAULT 0, --0 - Local, 1 - AWS S3
	nome_arquivo varchar(50) null,
	path_arquivo varchar(200) null,
	tamanho int null,
	data_cadastro TIMESTAMP DEFAULT now(),
	PRIMARY KEY (id_arquivo),
);

select * from perfil_acesso pa  u 

