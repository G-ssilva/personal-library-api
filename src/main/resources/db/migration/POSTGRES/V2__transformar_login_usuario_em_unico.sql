ALTER TABLE IF EXISTS usuario
ADD CONSTRAINT uk_login UNIQUE (login);
