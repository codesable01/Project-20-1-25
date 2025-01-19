-- Tabla de Cursos
CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombreCurso  VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO'
);