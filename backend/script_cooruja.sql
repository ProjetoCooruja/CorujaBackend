create schema cooruja;

use cooruja;

drop table usuario;
drop table usuario_login;
drop table perfil_acesso;

create Table perfil_acesso (
		id int not null auto_increment,
		nome_perfil varchar(50) not null,
		descricao_perfil varchar(200) not null,
		status TINYINT (1)  not null,
		PRIMARY KEY (id)
);

insert into perfil_acesso values (1, "Comum", "Comum", 1);
insert into perfil_acesso values (2, "Admin", "Administrador", 1);

Create Table usuario (
		id int not null auto_increment,
		nome varchar(50) not null,
		sobrenome varchar(50) not null,
		email varchar(100) not null unique key,
		senha varchar(100) not null,
		termos_aceite TINYINT (1)  not null, 
		id_perfil int not null,
		data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		data_modificacao TIMESTAMP null,
		PRIMARY KEY (id),
		foreign key (id_perfil) references perfil_acesso(id)
);

Create Table usuario_login (
		id_usuario int not null,
		data_login  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		PRIMARY KEY (id_usuario, data_login),
		foreign key (id_usuario) references usuario(id)
);

create table arquivo (
	id int not null auto_increment,
	id_usuario int not null,
	tipo_armazenamento int null DEFAULT 0,
	nome_arquivo text null,
	path_arquivo text null,
	tamanho int null,
	data_cadastro TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

create table post (
	id int not null auto_increment,
	id_arquivo int not null,
	id_usuario int not null,
	titulo varchar(30) null,
	comentario text null,
	tags text null,
	data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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