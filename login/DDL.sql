-- Apaga o schema e o usuário se já existirem
drop schema if exists spring;
drop user if exists 'user'@'localhost';

-- Cria um novo schema chamado spring
create schema spring;

-- Cria um novo usuário e define uma senha
create user 'user'@'localhost' identified by 'pass123';

-- Concede permissões para o usuário no schema spring
grant select, insert, delete, update on spring.* to 'user'@'localhost';

-- Seleciona o schema spring para uso
use spring;

-- Cria a tabela para a entidade User com todas as colunas necessárias
create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_name varchar(255) not null, -- Coluna para o nome
  usr_surname varchar(255), -- Coluna para o sobrenome
  usr_password varchar(150) not null, -- Coluna para a senha
  usr_email varchar(255) unique, -- Coluna para o email com restrição unique
  usr_function varchar(255), -- Coluna para a função
  primary key (usr_id),
  unique key uni_user_name (usr_name) -- Chave única baseada no nome do usuário
);