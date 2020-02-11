create table IF NOT EXISTS OAUTH_ACCESS_TOKEN (
  token_id VARCHAR(256),
  token LONGTEXT,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGTEXT,
  refresh_token VARCHAR(256)
);

create table IF NOT EXISTS OAUTH_REFRESH_TOKEN (
  token_id VARCHAR(256),
  token LONGTEXT,
  authentication LONGTEXT
);