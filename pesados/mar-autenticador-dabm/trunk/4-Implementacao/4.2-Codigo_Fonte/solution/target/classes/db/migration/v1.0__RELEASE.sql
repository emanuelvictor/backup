CREATE SCHEMA IF NOT EXISTS "public";
CREATE SCHEMA IF NOT EXISTS "auditoria";
CREATE EXTENSION IF NOT EXISTS UNACCENT SCHEMA PG_CATALOG;


CREATE TABLE usuario
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  data_expiracao_senha timestamp without time zone NOT NULL,
  email character varying(100),
  login character varying(50) NOT NULL,
  nome_completo character varying(144),
  senha character varying(255) NOT NULL,
  pessoa bytea,
  CONSTRAINT usuario_pkey PRIMARY KEY (id),
  CONSTRAINT uk_usuario_email UNIQUE (email),
  CONSTRAINT uk_usuario_login UNIQUE (login)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE aplicativo
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  ativo boolean NOT NULL,
  identificador character varying(255),
  descricao character varying(144),
  endereco character varying(255),
  nome character varying(144) NOT NULL,
  refresh_token  character varying(144),
  perfis_dinamicos boolean NOT NULL,
  versao character varying(144),
  versao_estavel_id bigint,
  mensagem_desativacao text,
  CONSTRAINT aplicativo_pkey PRIMARY KEY (id),
  CONSTRAINT fk_aplicativo_versao_estavel_id FOREIGN KEY (versao_estavel_id)
      REFERENCES aplicativo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_aplicativo_identificador UNIQUE (identificador)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE perfil_acesso
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  descricao character varying(144),
  dias_expiracao_senha integer,
  editavel boolean NOT NULL,
  nome character varying(144) NOT NULL,
  removivel boolean NOT NULL,
  aplicativo_id bigint NOT NULL,
  CONSTRAINT perfil_acesso_pkey PRIMARY KEY (id),
  CONSTRAINT fk_perfil_acesso_aplicativo_id FOREIGN KEY (aplicativo_id)
      REFERENCES aplicativo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_perfil_acesso_aplicativo_id_id_nome UNIQUE (nome, aplicativo_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE perfil_usuario_aplicativo
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  perfil_acesso_id bigint NOT NULL,
  usuario_id bigint NOT NULL,
  CONSTRAINT perfil_usuario_aplicativo_pkey PRIMARY KEY (id),
  CONSTRAINT fk_perfil_usuario_aplicativo_perfil_acesso_id FOREIGN KEY (perfil_acesso_id)
      REFERENCES perfil_acesso (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_perfil_usuario_aplicativo_usuario_id FOREIGN KEY (usuario_id)
      REFERENCES usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_perfil_usuario_aplicativo_perfil_acesso_id_usuario_id UNIQUE (perfil_acesso_id, usuario_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE configuracao
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  dias_expiracao_senha integer NOT NULL,
  CONSTRAINT configuracao_pkey PRIMARY KEY (id),
  CONSTRAINT configuracao_dias_expiracao_senha_check CHECK (dias_expiracao_senha >= 0)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE permissao
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  identificador character varying(144) NOT NULL,
  aplicativo_id bigint NOT NULL,
  permissao_superior_id bigint,
  CONSTRAINT permissao_pkey PRIMARY KEY (id),
  CONSTRAINT fk_permissao_aplicativo_id FOREIGN KEY (aplicativo_id)
      REFERENCES aplicativo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_permissao_permissao_superior_id FOREIGN KEY (permissao_superior_id)
      REFERENCES permissao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_permissao_aplicativo_id_id_identificador_permissao_superior UNIQUE (id, identificador, aplicativo_id, permissao_superior_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE perfil_acesso_permissao
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  perfil_acesso_id bigint NOT NULL,
  permissao_id bigint NOT NULL,
  CONSTRAINT perfil_acesso_permissao_pkey PRIMARY KEY (id),
  CONSTRAINT fk_perfil_acesso_permissao_perfil_acesso_id FOREIGN KEY (perfil_acesso_id)
      REFERENCES perfil_acesso (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_perfil_acesso_permissao_permissao_id FOREIGN KEY (permissao_id)
      REFERENCES permissao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_perfil_acesso_permissao_id_perfil_acesso_id_permissao_id UNIQUE (id, perfil_acesso_id, permissao_id)
)
WITH (
  OIDS=FALSE
);


--Usuário login DABM
INSERT INTO usuario(id, created, data_expiracao_senha, email, login, nome_completo, senha)
	 VALUES (100, NOW(),NOW(), 'administrador@mailinator.com', 'administrador', 'Usuário Administrador Autenticador', 'a31a310a741322d0a88aa40c13dfe4a83ed58024');
	 
--Usuário login DABM
INSERT INTO usuario(id, created, data_expiracao_senha, email, login, nome_completo, senha)
	 VALUES (101, NOW(), NOW(), 'usuario@mailinator.com', 'usuario', 'Usuário Normal Autenticador', 'a31a310a741322d0a88aa40c13dfe4a83ed58024');	 

--Usuário test login DABM
INSERT INTO usuario(id, created, data_expiracao_senha, email, login, nome_completo, senha)
	 VALUES (106, NOW(), '2016-06-12 00:41:06.389338', 'test@mailinator.com', 'test', 'Usuario Test Autenticador', '41e9297364264b3cab844b6be06b9e286fecf379'); 
	 
--Usuário GOS	 
INSERT INTO usuario(id, created, data_expiracao_senha, email, login, nome_completo, senha)
	 VALUES (102, NOW(), NOW(), 'administrador_gos@mailinator.com', 'administrador_gos', 'Usuário Administrador GOS', 'a31a310a741322d0a88aa40c13dfe4a83ed58024');
	 
--Usuário GOS	 
INSERT INTO usuario(id, created, data_expiracao_senha, email, login, nome_completo, senha)
	 VALUES (103, NOW(), NOW(), 'usuario_gos@mailinator.com', 'usuario_gos', 'Usuário Normal GOS', 'a31a310a741322d0a88aa40c13dfe4a83ed58024');
	 
--Usuário PDTI
INSERT INTO usuario(id, created, data_expiracao_senha, email, login, nome_completo, senha)
	 VALUES (104, NOW(), NOW(), 'administrador_pdti@mailinator.com', 'administrador_pdti', 'Usuário Administrador PDTI', 'a31a310a741322d0a88aa40c13dfe4a83ed58024');
	 
--Usuário PDTI
INSERT INTO usuario(id, created, data_expiracao_senha, email, login, nome_completo, senha)
	 VALUES (105, NOW(), NOW(),'usuario_pdti@mailinator.com', 'usuario_pdti', 'Usuario Normal PDTI', 'a31a310a741322d0a88aa40c13dfe4a83ed58024');	 
	 
--Inserindo autenticador DABM	 
INSERT INTO aplicativo(id, created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
     VALUES (200, NOW(), TRUE, 'Autenticador', 'b77c1321-6549-43dc-9697-a9018c65a227', 'http://autenticador.prognus.com.br', 'Autenticador DAbM', FALSE, '1.0.4-RELEASE');

--Perfis do autenticador DABM
INSERT INTO perfil_acesso(id, created,  editavel, removivel, aplicativo_id, nome)
                    VALUES (400, NOW(),  false, false, 200, 'ADMINISTRADOR');

--Perfis do autenticador DABM
INSERT INTO perfil_acesso(id, created,  editavel, removivel, aplicativo_id, nome)
                    VALUES (401, NOW(),  false, false, 200, 'USUÁRIO');

                    --       Usuários com perfis do login DABM
INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (500, NOW(), 400, 100);

INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (502, NOW(), 401, 101);

INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (511, NOW(), 400, 106);

--Permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (1, NOW(),  NOW(), 'usuarioService', 200, NULL);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (25, NOW(),  NOW(), 'getUserLogged', 200, 1);
                    
--Permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (2, NOW(),  NOW(), 'findUsuarioById', 200, 1);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (3, NOW(),  NOW(), 'listUsuariosByFilters', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (4, NOW(),  NOW(), 'listUsuariosByFiltersAndBloqueados', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (5, NOW(),  NOW(), 'listUsuariosByFiltersAndExcluidos', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (6, NOW(),  NOW(), 'listAplicativoByUsuarioId', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (7, NOW(),  NOW(), 'listPerfilUsuarioAplicativoByUsuarioId', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (8, NOW(),  NOW(), 'insertPerfilUsuarioAplicativo', 200, 2);

                    --    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (9, NOW(),  NOW(), 'removePerfilUsuarioAplicativo', 200, 2);

                    --    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (10, NOW(),  NOW(), 'replicarPerfisAcesso', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (11, NOW(),  NOW(), 'listPerfilUsuarioAplicativoHistoricoByUsuarioId', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (12, NOW(),  NOW(), 'findFotoUsuarioById', 200, 25);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (13, NOW(),  NOW(), 'uploadFotoUsuario', 200, 25);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (14, NOW(),  NOW(), 'removeFotoUsuario', 200, 25);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (15, NOW(),  NOW(), 'updateUsuario', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (16, NOW(),  NOW(), 'changeSenhaUsuario', 200, 25);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (17, NOW(),  NOW(), 'resetSenhaUsuario', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (18, NOW(),  NOW(), 'enviarEmail', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (19, NOW(),  NOW(), 'bloquearUsuarios', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (20, NOW(),  NOW(), 'desbloquearUsuarios', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (21, NOW(),  NOW(), 'excluirUsuario', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (22, NOW(),  NOW(), 'excluirUsuarios', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (23, NOW(),  NOW(), 'restaurarUsuarios', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (24, NOW(),  NOW(), 'insertUsuario', 200, 2);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (26, NOW(),  NOW(), 'listAplicativosByUserLogged', 200, 25);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (27, NOW(),  NOW(), 'checkEmail', 200, 1);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (28, NOW(),  NOW(), 'checkLogin', 200, 1);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (30, NOW(),  NOW(), 'aplicativoService', 200, NULL);

--    permissão do AUTENTICADOR
INSERT INTO permissao(id, created, updated, identificador, aplicativo_id, permissao_superior_id)
                    VALUES (31, NOW(),  NOW(), 'configuracaoService', 200, NULL);

--                    Perfis acesso permissão AUTENTICADOR
--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (1, NOW(),  null, 400, 1);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (2, NOW(),  null, 400, 2);



--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (13, NOW(),  null, 400, 22);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (14, NOW(),  null, 400, 23);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (15, NOW(),  null, 400, 24);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (16, NOW(),  null, 400, 20);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (17, NOW(),  null, 400, 15);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (18, NOW(),  null, 400, 14);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (19, NOW(),  null, 400, 13);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (20, NOW(),  null, 400, 12);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (21, NOW(),  null, 400, 19);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (22, NOW(),  null, 400, 18);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (23, NOW(),  null, 400, 17);

                      --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (24, NOW(),  null, 400, 16);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (25, NOW(),  null, 400, 21);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (26, NOW(),  null, 400, 3);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (27, NOW(),  null, 400, 4);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (28, NOW(),  null, 400, 5);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (29, NOW(),  null, 400, 6);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (30, NOW(),  null, 400, 7);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (31, NOW(),  null, 400, 10);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (32, NOW(),  null, 400, 11);

                    --    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (33, NOW(),  null, 400, 8);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (34, NOW(),  null, 400, 9);




--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (3, NOW(),  null, 400, 25);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (4, NOW(),  null, 400, 26);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (5, NOW(),  null, 400, 27);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (6, NOW(),  null, 400, 28);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (7, NOW(),  null, 400, 30);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (8, NOW(),  null, 400, 31);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (9, NOW(),  null, 401, 25);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (10, NOW(),  null, 401, 26);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (11, NOW(),  null, 401, 27);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (12, NOW(),  null, 401, 28);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (35, NOW(),  null, 401, 30);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (36, NOW(),  null, 401, 12);
                    
--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (37, NOW(),  null, 401, 14);
                    
--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (38, NOW(),  null, 401, 16);

--    perfil acesso permissão
INSERT INTO perfil_acesso_permissao(id, created, updated, perfil_acesso_id, permissao_id)
                    VALUES (39, NOW(),  null, 401, 13);
                    
 --Inserindo GOS
INSERT INTO aplicativo(id, created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
     VALUES (201, NOW(), TRUE, 'GOS', 'b77c1257-6549-43dc-9696-a9018c92a227', 'http://gos.prognus.com.br', 'GOS', FALSE, '1.0.0-SNAPSHOT');

-- perfis do GOS
INSERT INTO perfil_acesso(id, created,  editavel, removivel, aplicativo_id, nome)
                    VALUES (402, NOW(),  false, false, 201, 'ADMINISTRADOR');

--perfis do GOS
INSERT INTO perfil_acesso(id, created,  editavel, removivel, aplicativo_id, nome)
                    VALUES (403, NOW(),  false, false, 201, 'ATENDENTE');

                    --       perfis do gos
INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (503, NOW(), 400, 102);

INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (504, NOW(), 401, 103);

INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (505, NOW(), 403, 102);

INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (506, NOW(), 402, 103);

--Inserindo PDTI
INSERT INTO aplicativo(id, created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
VALUES (202, NOW(), TRUE, 'PDTI', 'a77c1257-6549-43dc-9676-a9018c92a227', 'http://pdti.prognus.com.br', 'PDTI', FALSE, '1.0.0-SNAPSHOT');
                    
--    perfis do PDTI
INSERT INTO perfil_acesso(id, created,  editavel, removivel, aplicativo_id, nome)
                    VALUES (404, NOW(),  false, false, 202, 'ADMINISTRADOR');
                    
--    perfis do PDTI
INSERT INTO perfil_acesso(id, created,  editavel, removivel, aplicativo_id, nome)
                    VALUES (405, NOW(),  false, false, 202, 'FISCAL');       
                      
--       perfis do pdti
INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (507, NOW(), 400, 104);
                    
INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (508, NOW(), 401, 105);                    
                    
INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (509, NOW(), 405, 104);
                    
INSERT INTO perfil_usuario_aplicativo(id, created, perfil_acesso_id, usuario_id)
                    VALUES (510, NOW(), 404, 105);
     

--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '222', '2222', 'http://localhost:8081/client2', 'App Test2', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '333', '3333', 'http://localhost:8081/client3', 'App Test3', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '444', '4444', 'http://localhost:8081/client4', 'App Test4', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '555', '5555', 'http://localhost:8081/client5', 'App Test5', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '666', '6666', 'http://localhost:8081/client6', 'App Test6', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '777', '7777', 'http://localhost:8081/client7', 'App Test7', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '888', '8888', 'http://localhost:8081/client8', 'App Test8', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '999', '9999', 'http://localhost:8081/client9', 'App Test9', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '101010', '10101010', 'http://localhost:8081/client10', 'App Test10', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '11111111', '1111111111', 'http://localhost:8081/client11', 'App Test11', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '121212', '12121212', 'http://localhost:8081/client12', 'App Test12', FALSE, '1.0.0-SNAPSHOT');
--INSERT INTO aplicativo(created, ativo, identificador, refresh_token, endereco, nome, perfis_dinamicos, versao)
--     VALUES (NOW(), TRUE, '13131313', '1313131313', 'http://localhost:8081/client13', 'App Test13', FALSE, '1.0.0-SNAPSHOT');

--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (200, 0); 
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (200, 1); 
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (200, 2);
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (200, 3);
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (201, 0); 
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (201, 1); 
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (201, 2);
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (201, 3);
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (202, 0); 
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (202, 1); 
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (202, 2);
--INSERT INTO TIPO_ACESSO_APLICATIVO(APLICATIVO_ID, TIPOS_ACESSO_APLICATIVO) VALUES (202, 3);


INSERT INTO configuracao(id , created , dias_expiracao_senha)
	VALUES(1 , NOW() , 90);
