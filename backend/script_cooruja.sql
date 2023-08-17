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
	id int not null auto_increment,
	id_usuario int not null,
	tp_armazenamento int null DEFAULT 0, --0 - Local, 1 - AWS S3
	nome_arquivo text null,
	path_arquivo text null,
	tamanho int null,
	data_cadastro TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id_arquivo),
);

create table post (
	id int not null auto_increment,
	id_arquivo int not null,
	id_usuario int not null,
	titulo varchar(30) null,
	comentario text null,
	tags text null,
	data_cadastro TIMESTAMP DEFAULT now(),
	PRIMARY KEY (id)
);

create table comentario (
	id int not null auto_increment,
	id_post int not null,
	id_usuario int not null,
	comentario text null,
	data_cadastro TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

select * from perfil_acesso pa  u 


select * 
from post as p
join arquivo as a on p.id_arquivo = a.id
join usuario as u on p.id_usuario = u.id

