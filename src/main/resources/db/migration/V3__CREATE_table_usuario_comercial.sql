-- Tabla de UsuarioComercial
CREATE TABLE usuario_comercial (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,  -- Correo electrónico único
    contraseña VARCHAR(300) NOT NULL ,
    perfil_id BIGINT,  -- Relación con la tabla perfiles
    FOREIGN KEY (perfil_id) REFERENCES perfiles(id) ON DELETE SET NULL  -- Relación con perfiles
);
