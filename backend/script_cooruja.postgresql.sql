create database cooruja;

CREATE TABLE perfil_acesso (
    id SERIAL PRIMARY KEY,
    nome_perfil VARCHAR(50) NOT NULL,
    descricao_perfil VARCHAR(200) NOT NULL,
    status BOOLEAN NOT NULL
);

insert into perfil_acesso values ( 1, 'Comum', 'Usuario comum', true);
insert into perfil_acesso values ( 2, 'Admin', 'Administrador', true);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    termos_aceite BOOLEAN NOT NULL, 
    id_perfil INT NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_modificacao TIMESTAMP,
    FOREIGN KEY (id_perfil) REFERENCES perfil_acesso(id)
);

CREATE TABLE usuario_login (
    id_usuario INT NOT NULL,
    data_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario, data_login),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE arquivo (
    id SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    tipo_armazenamento INT DEFAULT 0,
    nome_arquivo TEXT,
    path_arquivo TEXT,
    tamanho INT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    id_arquivo INT NOT NULL,
    id_usuario INT NOT NULL,
    titulo VARCHAR(30),
    comentario TEXT,
    tags TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_arquivo) REFERENCES arquivo(id)
);

CREATE TABLE comentario (
    id SERIAL PRIMARY KEY,
    id_post INT NOT NULL,
    id_usuario INT NOT NULL,
    comentario TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_post) REFERENCES post(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
