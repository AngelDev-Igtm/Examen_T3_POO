/* ============================================================================
   INDIGO LOFT - SCRIPT COMPLETO DE BASE DE DATOS PARA PRUEBAS
   Motor: Microsoft SQL Server
   Incluye:
     1) Creación de una base de datos de prueba independiente.
     2) Las 14 tablas del modelo relacional aprobado.
     3) Claves primarias, claves foráneas, UNIQUE, CHECK y DEFAULT.
     4) Datos demostrativos para probar las relaciones.
     5) Consultas finales de verificación.

   IMPORTANTE:
   - Este script crea IndigoLoftDB_Prueba para NO sobrescribir la BD real.
   - Ejecutar completo en SSMS con permisos para crear bases de datos.
   - Si la BD de prueba ya contiene PERSONA, el script se detiene para no
     duplicar ni borrar objetos existentes.
   - No incluye usuarios/roles de acceso: el proyecto acordó un único acceso
     compartido fuera del esquema de estas 14 tablas.
   ============================================================================ */

/* ========================= 0. CREAR BASE DE DATOS ========================= */
USE master;
GO

IF DB_ID(N'IndigoLoftDB_Prueba') IS NULL
BEGIN
    CREATE DATABASE [IndigoLoftDB_Prueba];
    PRINT N'Base de datos IndigoLoftDB_Prueba creada.';
END
ELSE
BEGIN
    PRINT N'La base de datos IndigoLoftDB_Prueba ya existe; se utilizará.';
END;
GO

USE [IndigoLoftDB_Prueba];
GO

IF OBJECT_ID(N'dbo.PERSONA', N'U') IS NOT NULL
BEGIN
    ;THROW 51000, 'La base de datos ya contiene el esquema Indigo Loft. Usa una BD vacía o cambia el nombre de la BD de prueba al inicio del script.', 1;
END;
GO

SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
GO

/* ============================== 1. PERSONA ================================ */
CREATE TABLE dbo.PERSONA
(
    nro_doc       VARCHAR(10)    NOT NULL,
    tipo_doc      VARCHAR(3)     NOT NULL,
    nombre        NVARCHAR(80)   NOT NULL,
    apellido_pat  NVARCHAR(80)   NOT NULL,
    apellido_mat  NVARCHAR(80)   NOT NULL,
    telefono      VARCHAR(16)    NOT NULL,
    correo        VARCHAR(150)   NOT NULL,

    CONSTRAINT PK_PERSONA PRIMARY KEY (nro_doc),
    CONSTRAINT CK_PERSONA_TIPO_DOC
        CHECK (tipo_doc IN ('DNI', 'CE', 'PAS')),
    CONSTRAINT CK_PERSONA_NUMERO_DOC
        CHECK
        (
            nro_doc NOT LIKE '%[^0-9]%'
            AND
            (
                (tipo_doc = 'DNI' AND LEN(nro_doc) = 8)
                OR (tipo_doc = 'CE' AND LEN(nro_doc) = 10)
                OR (tipo_doc = 'PAS' AND LEN(nro_doc) = 9)
            )
        ),
    CONSTRAINT CK_PERSONA_NOMBRES
        CHECK
        (
            LEN(LTRIM(RTRIM(nombre))) > 0
            AND LEN(LTRIM(RTRIM(apellido_pat))) > 0
            AND LEN(LTRIM(RTRIM(apellido_mat))) > 0
        ),
    CONSTRAINT CK_PERSONA_TELEFONO
        CHECK
        (
            LEN(REPLACE(telefono, '+', '')) BETWEEN 7 AND 15
            AND REPLACE(telefono, '+', '') NOT LIKE '%[^0-9]%'
            AND
            (
                CHARINDEX('+', telefono) = 0
                OR (LEFT(telefono, 1) = '+' AND CHARINDEX('+', telefono, 2) = 0)
            )
        ),
    CONSTRAINT CK_PERSONA_CORREO
        CHECK (correo LIKE '%_@_%._%')
);
GO

/* ============================ 2. DEPARTAMENTO ============================= */
CREATE TABLE dbo.DEPARTAMENTO
(
    id_departamento     VARCHAR(7)    NOT NULL,
    nombre_departamento NVARCHAR(80)  COLLATE Latin1_General_100_CI_AI NOT NULL,
    presupuesto_mensual DECIMAL(12,2) NOT NULL,
    nro_doc_gerente     VARCHAR(10)   NULL,

    CONSTRAINT PK_DEPARTAMENTO PRIMARY KEY (id_departamento),
    CONSTRAINT UQ_DEPARTAMENTO_NOMBRE UNIQUE (nombre_departamento),
    CONSTRAINT CK_DEPARTAMENTO_ID
        CHECK (id_departamento LIKE 'DEP-[A-Z0-9][A-Z0-9][A-Z0-9]'),
    CONSTRAINT CK_DEPARTAMENTO_PRESUPUESTO
        CHECK (presupuesto_mensual > 0),
    CONSTRAINT CK_DEPARTAMENTO_NOMBRE
        CHECK (LEN(LTRIM(RTRIM(nombre_departamento))) > 0)
);
GO

/* ============================== 3. EMPLEADO ================================ */
CREATE TABLE dbo.EMPLEADO
(
    nro_doc         VARCHAR(10)   NOT NULL,
    sueldo          DECIMAL(10,2) NOT NULL,
    nro_doc_jefe    VARCHAR(10)   NULL,
    id_departamento VARCHAR(7)    NULL,

    CONSTRAINT PK_EMPLEADO PRIMARY KEY (nro_doc),
    CONSTRAINT CK_EMPLEADO_SUELDO CHECK (sueldo > 0),
    CONSTRAINT CK_EMPLEADO_NO_ES_SU_PROPIO_JEFE
        CHECK (nro_doc_jefe IS NULL OR nro_doc_jefe <> nro_doc)
);
GO

/* =============================== 4. HUESPED ================================ */
CREATE TABLE dbo.HUESPED
(
    nro_doc       VARCHAR(10)   NOT NULL,
    nacionalidad  NVARCHAR(60)  NOT NULL,
    preferencias  NVARCHAR(250) NULL,
    esFrecuente   BIT           NOT NULL
        CONSTRAINT DF_HUESPED_ES_FRECUENTE DEFAULT (0),

    CONSTRAINT PK_HUESPED PRIMARY KEY (nro_doc),
    CONSTRAINT CK_HUESPED_NACIONALIDAD
        CHECK (LEN(LTRIM(RTRIM(nacionalidad))) > 0)
);
GO

/* ================================ 5. GERENTE ============================== */
CREATE TABLE dbo.GERENTE
(
    nro_doc       VARCHAR(10)   NOT NULL,
    bono_gestion  DECIMAL(10,2) NOT NULL,

    CONSTRAINT PK_GERENTE PRIMARY KEY (nro_doc),
    CONSTRAINT CK_GERENTE_BONO CHECK (bono_gestion >= 0)
);
GO

/* ============================ 6. ADMINISTRATIVO ============================ */
CREATE TABLE dbo.ADMINISTRATIVO
(
    nro_doc    VARCHAR(10) NOT NULL,
    nro_anexo  VARCHAR(6)  NOT NULL,

    CONSTRAINT PK_ADMINISTRATIVO PRIMARY KEY (nro_doc),
    CONSTRAINT CK_ADMINISTRATIVO_ANEXO
        CHECK (LEN(nro_anexo) = 6 AND nro_anexo NOT LIKE '%[^0-9]%')
);
GO

/* =============================== 7. CONSERJE ============================== */
CREATE TABLE dbo.CONSERJE
(
    nro_doc      VARCHAR(10)   NOT NULL,
    especialidad NVARCHAR(100) NOT NULL,

    CONSTRAINT PK_CONSERJE PRIMARY KEY (nro_doc),
    CONSTRAINT CK_CONSERJE_ESPECIALIDAD
        CHECK (LEN(LTRIM(RTRIM(especialidad))) > 0)
);
GO

/* ============================ 8. RECEPCIONISTA ============================ */
CREATE TABLE dbo.RECEPCIONISTA
(
    nro_doc VARCHAR(10)  NOT NULL,
    turno   NVARCHAR(15) COLLATE Latin1_General_100_CI_AS NOT NULL,

    CONSTRAINT PK_RECEPCIONISTA PRIMARY KEY (nro_doc),
    CONSTRAINT CK_RECEPCIONISTA_TURNO
        CHECK (UPPER(turno) IN (N'MAÑANA', N'TARDE', N'NOCHE', N'COMPLETO'))
);
GO

/* ============================== 9. HABITACION ============================== */
CREATE TABLE dbo.HABITACION
(
    nro_habitacion INT           NOT NULL,
    estado         NVARCHAR(20)  NOT NULL
        CONSTRAINT DF_HABITACION_ESTADO DEFAULT (N'Disponible'),
    precio_noche   DECIMAL(10,2)  NOT NULL,

    CONSTRAINT PK_HABITACION PRIMARY KEY (nro_habitacion),
    CONSTRAINT CK_HABITACION_NUMERO
        CHECK (nro_habitacion BETWEEN 100 AND 2000),
    CONSTRAINT CK_HABITACION_ESTADO
        CHECK (UPPER(estado) IN (N'DISPONIBLE', N'OCUPADA', N'MANTENIMIENTO', N'LIMPIEZA')),
    CONSTRAINT CK_HABITACION_PRECIO CHECK (precio_noche > 0)
);
GO

/* ========================== 10. HABITACION_SIMPLE ========================== */
CREATE TABLE dbo.HABITACION_SIMPLE
(
    nro_habitacion INT NOT NULL,
    tieneVistaCalle BIT NOT NULL,

    CONSTRAINT PK_HABITACION_SIMPLE PRIMARY KEY (nro_habitacion)
);
GO

/* ================================= 11. SUITE =============================== */
CREATE TABLE dbo.SUITE
(
    nro_habitacion INT NOT NULL,
    tieneJacuzzi   BIT NOT NULL,

    CONSTRAINT PK_SUITE PRIMARY KEY (nro_habitacion)
);
GO

/* ================================ 12. RESERVA ============================== */
CREATE TABLE dbo.RESERVA
(
    cod_reserva          VARCHAR(10)  NOT NULL,
    fecha_inicio         DATE         NOT NULL,
    fecha_fin            DATE         NOT NULL,
    fecha_salida_real    DATE         NULL,
    estado_reserva       NVARCHAR(20) NOT NULL
        CONSTRAINT DF_RESERVA_ESTADO DEFAULT (N'Confirmada'),
    nro_doc_huesped      VARCHAR(10)  NOT NULL,
    nro_doc_recepcionista VARCHAR(10) NOT NULL,

    CONSTRAINT PK_RESERVA PRIMARY KEY (cod_reserva),
    CONSTRAINT CK_RESERVA_CODIGO
        CHECK (cod_reserva LIKE 'HTL-[A-F0-9][A-F0-9][A-F0-9][A-F0-9][A-F0-9][A-F0-9]'),
    CONSTRAINT CK_RESERVA_FECHAS
        CHECK
        (
            fecha_fin > fecha_inicio
            AND (fecha_salida_real IS NULL OR fecha_salida_real >= fecha_inicio)
        ),
    CONSTRAINT CK_RESERVA_ESTADO
        CHECK (UPPER(estado_reserva) IN (N'CONFIRMADA', N'EN CURSO', N'FINALIZADA', N'CANCELADA'))
);
GO

/* ========================= 13. RESERVA_HABITACION =========================== */
CREATE TABLE dbo.RESERVA_HABITACION
(
    cod_reserva    VARCHAR(10) NOT NULL,
    nro_habitacion INT         NOT NULL,

    CONSTRAINT PK_RESERVA_HABITACION
        PRIMARY KEY (cod_reserva, nro_habitacion)
);
GO

/* ================================ 14. FACTURA ============================== */
CREATE TABLE dbo.FACTURA
(
    cod_factura   VARCHAR(12) NOT NULL,
    cod_reserva   VARCHAR(10) NOT NULL,
    fecha_emision DATE        NOT NULL
        CONSTRAINT DF_FACTURA_FECHA_EMISION DEFAULT (CONVERT(DATE, GETDATE())),

    CONSTRAINT PK_FACTURA PRIMARY KEY (cod_factura),
    CONSTRAINT UQ_FACTURA_COD_RESERVA UNIQUE (cod_reserva),
    CONSTRAINT CK_FACTURA_CODIGO
        CHECK (cod_factura LIKE 'FAC-[A-F0-9][A-F0-9][A-F0-9][A-F0-9][A-F0-9][A-F0-9][A-F0-9][A-F0-9]')
);
GO

/* ============================== CLAVES FORANEAS ============================ */
/* 1. EMPLEADO.nro_doc -> PERSONA.nro_doc */
ALTER TABLE dbo.EMPLEADO WITH CHECK
ADD CONSTRAINT FK_EMPLEADO_PERSONA
FOREIGN KEY (nro_doc) REFERENCES dbo.PERSONA (nro_doc);
GO

/* 2. EMPLEADO.nro_doc_jefe -> EMPLEADO.nro_doc (relacion recursiva) */
ALTER TABLE dbo.EMPLEADO WITH CHECK
ADD CONSTRAINT FK_EMPLEADO_JEFE
FOREIGN KEY (nro_doc_jefe) REFERENCES dbo.EMPLEADO (nro_doc);
GO

/* 3. EMPLEADO.id_departamento -> DEPARTAMENTO.id_departamento */
ALTER TABLE dbo.EMPLEADO WITH CHECK
ADD CONSTRAINT FK_EMPLEADO_DEPARTAMENTO
FOREIGN KEY (id_departamento) REFERENCES dbo.DEPARTAMENTO (id_departamento);
GO

/* 4. HUESPED.nro_doc -> PERSONA.nro_doc */
ALTER TABLE dbo.HUESPED WITH CHECK
ADD CONSTRAINT FK_HUESPED_PERSONA
FOREIGN KEY (nro_doc) REFERENCES dbo.PERSONA (nro_doc);
GO

/* 5. GERENTE.nro_doc -> EMPLEADO.nro_doc */
ALTER TABLE dbo.GERENTE WITH CHECK
ADD CONSTRAINT FK_GERENTE_EMPLEADO
FOREIGN KEY (nro_doc) REFERENCES dbo.EMPLEADO (nro_doc);
GO

/* 6. ADMINISTRATIVO.nro_doc -> EMPLEADO.nro_doc */
ALTER TABLE dbo.ADMINISTRATIVO WITH CHECK
ADD CONSTRAINT FK_ADMINISTRATIVO_EMPLEADO
FOREIGN KEY (nro_doc) REFERENCES dbo.EMPLEADO (nro_doc);
GO

/* 7. CONSERJE.nro_doc -> EMPLEADO.nro_doc */
ALTER TABLE dbo.CONSERJE WITH CHECK
ADD CONSTRAINT FK_CONSERJE_EMPLEADO
FOREIGN KEY (nro_doc) REFERENCES dbo.EMPLEADO (nro_doc);
GO

/* 8. RECEPCIONISTA.nro_doc -> EMPLEADO.nro_doc */
ALTER TABLE dbo.RECEPCIONISTA WITH CHECK
ADD CONSTRAINT FK_RECEPCIONISTA_EMPLEADO
FOREIGN KEY (nro_doc) REFERENCES dbo.EMPLEADO (nro_doc);
GO

/* 9. DEPARTAMENTO.nro_doc_gerente -> GERENTE.nro_doc */
ALTER TABLE dbo.DEPARTAMENTO WITH CHECK
ADD CONSTRAINT FK_DEPARTAMENTO_GERENTE
FOREIGN KEY (nro_doc_gerente) REFERENCES dbo.GERENTE (nro_doc);
GO

/* 10. HABITACION_SIMPLE.nro_habitacion -> HABITACION.nro_habitacion */
ALTER TABLE dbo.HABITACION_SIMPLE WITH CHECK
ADD CONSTRAINT FK_HABITACION_SIMPLE_HABITACION
FOREIGN KEY (nro_habitacion) REFERENCES dbo.HABITACION (nro_habitacion);
GO

/* 11. SUITE.nro_habitacion -> HABITACION.nro_habitacion */
ALTER TABLE dbo.SUITE WITH CHECK
ADD CONSTRAINT FK_SUITE_HABITACION
FOREIGN KEY (nro_habitacion) REFERENCES dbo.HABITACION (nro_habitacion);
GO

/* 12. RESERVA.nro_doc_huesped -> HUESPED.nro_doc */
ALTER TABLE dbo.RESERVA WITH CHECK
ADD CONSTRAINT FK_RESERVA_HUESPED
FOREIGN KEY (nro_doc_huesped) REFERENCES dbo.HUESPED (nro_doc);
GO

/* 13. RESERVA.nro_doc_recepcionista -> RECEPCIONISTA.nro_doc */
ALTER TABLE dbo.RESERVA WITH CHECK
ADD CONSTRAINT FK_RESERVA_RECEPCIONISTA
FOREIGN KEY (nro_doc_recepcionista) REFERENCES dbo.RECEPCIONISTA (nro_doc);
GO

/* 14. RESERVA_HABITACION.cod_reserva -> RESERVA.cod_reserva */
ALTER TABLE dbo.RESERVA_HABITACION WITH CHECK
ADD CONSTRAINT FK_RESERVA_HABITACION_RESERVA
FOREIGN KEY (cod_reserva) REFERENCES dbo.RESERVA (cod_reserva);
GO

/* 15. RESERVA_HABITACION.nro_habitacion -> HABITACION.nro_habitacion */
ALTER TABLE dbo.RESERVA_HABITACION WITH CHECK
ADD CONSTRAINT FK_RESERVA_HABITACION_HABITACION
FOREIGN KEY (nro_habitacion) REFERENCES dbo.HABITACION (nro_habitacion);
GO

/* 16. FACTURA.cod_reserva -> RESERVA.cod_reserva */
ALTER TABLE dbo.FACTURA WITH CHECK
ADD CONSTRAINT FK_FACTURA_RESERVA
FOREIGN KEY (cod_reserva) REFERENCES dbo.RESERVA (cod_reserva);
GO

/* ================================ INDICES ================================= */
/* Un gerente puede dirigir como maximo un departamento.
   Se permiten varios departamentos sin gerente (NULL). */
CREATE UNIQUE INDEX UX_DEPARTAMENTO_GERENTE
ON dbo.DEPARTAMENTO (nro_doc_gerente)
WHERE nro_doc_gerente IS NOT NULL;
GO

CREATE INDEX IX_EMPLEADO_DEPARTAMENTO ON dbo.EMPLEADO (id_departamento);
CREATE INDEX IX_EMPLEADO_JEFE ON dbo.EMPLEADO (nro_doc_jefe);
CREATE INDEX IX_RESERVA_HUESPED ON dbo.RESERVA (nro_doc_huesped);
CREATE INDEX IX_RESERVA_RECEPCIONISTA ON dbo.RESERVA (nro_doc_recepcionista);
CREATE INDEX IX_RESERVA_HABITACION_HABITACION ON dbo.RESERVA_HABITACION (nro_habitacion);
GO

/* ============================ DATOS DE PRUEBA ============================== */
/* Estos registros son demostrativos. Puedes eliminarlos si solo deseas
   instalar la estructura vacia. Los codigos respetan los formatos generados
   por las clases Java del proyecto. */
SET XACT_ABORT ON;
BEGIN TRANSACTION;

/* PERSONAS: 4 empleados y 1 huesped. */
INSERT INTO dbo.PERSONA
    (nro_doc, tipo_doc, nombre, apellido_pat, apellido_mat, telefono, correo)
VALUES
    ('12345678',   'DNI', N'Carlos',  N'Rojas',    N'Mendoza', '987654321', 'carlos.rojas@indigoloft.com'),
    ('23456789',   'DNI', N'Lucia',   N'Vargas',   N'Flores',  '986543210', 'lucia.vargas@indigoloft.com'),
    ('34567890',   'DNI', N'Pedro',   N'Sanchez',  N'Ruiz',    '985432109', 'pedro.sanchez@indigoloft.com'),
    ('45678901',   'DNI', N'Maria',   N'Torres',   N'Castro',  '984321098', 'maria.torres@indigoloft.com'),
    ('1234567890', 'CE',  N'Andres',  N'Gomez',    N'Lopez',   '983210987', 'andres.gomez@example.com');

/* DEPARTAMENTO primero con gerente NULL para resolver la dependencia circular. */
INSERT INTO dbo.DEPARTAMENTO
    (id_departamento, nombre_departamento, presupuesto_mensual, nro_doc_gerente)
VALUES
    ('DEP-001', N'Administracion', 25000.00, NULL);

/* Empleados: el gerente no tiene jefe; los otros tres reportan al gerente. */
INSERT INTO dbo.EMPLEADO
    (nro_doc, sueldo, nro_doc_jefe, id_departamento)
VALUES
    ('12345678', 5000.00, NULL,       'DEP-001'),
    ('23456789', 2800.00, '12345678', 'DEP-001'),
    ('34567890', 2200.00, '12345678', 'DEP-001'),
    ('45678901', 2600.00, '12345678', 'DEP-001');

/* Subtipos de empleado. */
INSERT INTO dbo.GERENTE (nro_doc, bono_gestion)
VALUES ('12345678', 800.00);

INSERT INTO dbo.ADMINISTRATIVO (nro_doc, nro_anexo)
VALUES ('23456789', '123456');

INSERT INTO dbo.CONSERJE (nro_doc, especialidad)
VALUES ('34567890', N'Mantenimiento general');

INSERT INTO dbo.RECEPCIONISTA (nro_doc, turno)
VALUES ('45678901', N'Mañana');

/* Ahora se asigna el gerente responsable del departamento. */
UPDATE dbo.DEPARTAMENTO
SET nro_doc_gerente = '12345678'
WHERE id_departamento = 'DEP-001';

/* Huesped. */
INSERT INTO dbo.HUESPED (nro_doc, nacionalidad, preferencias, esFrecuente)
VALUES ('1234567890', N'Peruana', N'Habitacion tranquila', 0);

/* Habitaciones y sus subtipos. */
INSERT INTO dbo.HABITACION (nro_habitacion, estado, precio_noche)
VALUES
    (101, N'Disponible', 90.00),
    (201, N'Disponible', 310.00);

INSERT INTO dbo.HABITACION_SIMPLE (nro_habitacion, tieneVistaCalle)
VALUES (101, 1);

INSERT INTO dbo.SUITE (nro_habitacion, tieneJacuzzi)
VALUES (201, 1);

/* Reserva futura de prueba con dos habitaciones. */
DECLARE @InicioFuturo DATE = DATEADD(DAY, 7, CONVERT(DATE, GETDATE()));
DECLARE @FinFuturo    DATE = DATEADD(DAY, 10, CONVERT(DATE, GETDATE()));

INSERT INTO dbo.RESERVA
    (cod_reserva, fecha_inicio, fecha_fin, fecha_salida_real,
     estado_reserva, nro_doc_huesped, nro_doc_recepcionista)
VALUES
    ('HTL-000001', @InicioFuturo, @FinFuturo, NULL,
     N'Confirmada', '1234567890', '45678901');

INSERT INTO dbo.RESERVA_HABITACION (cod_reserva, nro_habitacion)
VALUES
    ('HTL-000001', 101),
    ('HTL-000001', 201);

INSERT INTO dbo.FACTURA (cod_factura, cod_reserva, fecha_emision)
VALUES ('FAC-00000001', 'HTL-000001', CONVERT(DATE, GETDATE()));

/* Reserva historica finalizada: la salida real fue antes de la fecha prevista. */
DECLARE @InicioHistorico DATE = DATEADD(DAY, -10, CONVERT(DATE, GETDATE()));
DECLARE @FinHistorico    DATE = DATEADD(DAY, -5, CONVERT(DATE, GETDATE()));
DECLARE @SalidaReal      DATE = DATEADD(DAY, -7, CONVERT(DATE, GETDATE()));

INSERT INTO dbo.RESERVA
    (cod_reserva, fecha_inicio, fecha_fin, fecha_salida_real,
     estado_reserva, nro_doc_huesped, nro_doc_recepcionista)
VALUES
    ('HTL-000002', @InicioHistorico, @FinHistorico, @SalidaReal,
     N'Finalizada', '1234567890', '45678901');

INSERT INTO dbo.RESERVA_HABITACION (cod_reserva, nro_habitacion)
VALUES ('HTL-000002', 201);

INSERT INTO dbo.FACTURA (cod_factura, cod_reserva, fecha_emision)
VALUES ('FAC-00000002', 'HTL-000002', CONVERT(DATE, GETDATE()));

COMMIT TRANSACTION;
GO

/* ============================ VERIFICACION FINAL =========================== */
PRINT N'Instalacion terminada. Conteo de registros por tabla:';

SELECT N'PERSONA' AS Tabla, COUNT(*) AS Registros FROM dbo.PERSONA
UNION ALL SELECT N'DEPARTAMENTO', COUNT(*) FROM dbo.DEPARTAMENTO
UNION ALL SELECT N'EMPLEADO', COUNT(*) FROM dbo.EMPLEADO
UNION ALL SELECT N'HUESPED', COUNT(*) FROM dbo.HUESPED
UNION ALL SELECT N'GERENTE', COUNT(*) FROM dbo.GERENTE
UNION ALL SELECT N'ADMINISTRATIVO', COUNT(*) FROM dbo.ADMINISTRATIVO
UNION ALL SELECT N'CONSERJE', COUNT(*) FROM dbo.CONSERJE
UNION ALL SELECT N'RECEPCIONISTA', COUNT(*) FROM dbo.RECEPCIONISTA
UNION ALL SELECT N'HABITACION', COUNT(*) FROM dbo.HABITACION
UNION ALL SELECT N'HABITACION_SIMPLE', COUNT(*) FROM dbo.HABITACION_SIMPLE
UNION ALL SELECT N'SUITE', COUNT(*) FROM dbo.SUITE
UNION ALL SELECT N'RESERVA', COUNT(*) FROM dbo.RESERVA
UNION ALL SELECT N'RESERVA_HABITACION', COUNT(*) FROM dbo.RESERVA_HABITACION
UNION ALL SELECT N'FACTURA', COUNT(*) FROM dbo.FACTURA
ORDER BY Tabla;
GO

/* Consulta de prueba: reservas, huespedes, habitaciones y facturas. */
SELECT
    r.cod_reserva,
    r.estado_reserva,
    r.fecha_inicio,
    r.fecha_fin,
    r.fecha_salida_real,
    CONCAT(p.nombre, N' ', p.apellido_pat, N' ', p.apellido_mat) AS Huesped,
    h.nro_habitacion,
    h.estado AS EstadoHabitacion,
    h.precio_noche,
    f.cod_factura,
    f.fecha_emision
FROM dbo.RESERVA AS r
INNER JOIN dbo.HUESPED AS hu
    ON hu.nro_doc = r.nro_doc_huesped
INNER JOIN dbo.PERSONA AS p
    ON p.nro_doc = hu.nro_doc
INNER JOIN dbo.RESERVA_HABITACION AS rh
    ON rh.cod_reserva = r.cod_reserva
INNER JOIN dbo.HABITACION AS h
    ON h.nro_habitacion = rh.nro_habitacion
LEFT JOIN dbo.FACTURA AS f
    ON f.cod_reserva = r.cod_reserva
ORDER BY r.fecha_inicio, r.cod_reserva, h.nro_habitacion;
GO

/* Resumen de claves foraneas creadas. */
SELECT
    fk.name AS NombreClaveForanea,
    OBJECT_NAME(fk.parent_object_id) AS TablaHija,
    OBJECT_NAME(fk.referenced_object_id) AS TablaPadre
FROM sys.foreign_keys AS fk
ORDER BY TablaHija, NombreClaveForanea;
GO

PRINT N'Fin del script de IndigoLoftDB_Prueba.';
GO
