-- Generado por Oracle SQL Developer Data Modeler 24.3.1.347.1153
--   en:        2025-04-24 00:43:37 CST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE tbl_categorias_especialista (
    codigo_categoria_especialista NUMBER NOT NULL,
    categoria_especialista           VARCHAR2(20) NOT NULL
);

ALTER TABLE tbl_categorias_especialista ADD CONSTRAINT tbl_categoria_especialista_pk PRIMARY KEY ( codigo_categoria_especialista)
;

CREATE TABLE tbl_celebraciones (
    codigo_publicacion NUMBER NOT NULL,
    tipo_celebracion   NUMBER NOT NULL,
    multimedia         CLOB
);

ALTER TABLE tbl_celebraciones ADD CONSTRAINT tbl_celebraciones_pk PRIMARY KEY ( codigo_publicacion );

CREATE TABLE tbl_ciudades (
    codigo_ciudad NUMBER NOT NULL,
    nombre_ciudad VARCHAR2(30) NOT NULL,
    ciudad_padre  NUMBER,
    pais          VARCHAR2(5) NOT NULL
);

ALTER TABLE tbl_ciudades ADD CONSTRAINT tbl_ciudad_pk PRIMARY KEY ( codigo_ciudad );

CREATE TABLE tbl_comentarios (
    codigo_comentario NUMBER NOT NULL,
    comentario        CLOB NOT NULL,
    comentario_padre  NUMBER,
    fecha_comentario  DATE NOT NULL
);

ALTER TABLE tbl_comentarios ADD CONSTRAINT tbl_comentarios_pk PRIMARY KEY ( codigo_comentario );

CREATE TABLE tbl_conexiones (
    codigo_conexion  NUMBER NOT NULL,
    fecha_conexion   DATE NOT NULL,
    codigo_estado    NUMBER NOT NULL,
    codigo_usuario_1 NUMBER NOT NULL,
    codigo_usuario_2 NUMBER NOT NULL
);

ALTER TABLE tbl_conexiones ADD CONSTRAINT tbl_conexiones_pk PRIMARY KEY ( codigo_conexion );

CREATE TABLE tbl_documentos (
    codigo_publicacion NUMBER NOT NULL,
    archivo            CLOB NOT NULL,
    titulo_doc         VARCHAR2(58) NOT NULL,
    multimedia         BLOB
);

ALTER TABLE tbl_documentos ADD CONSTRAINT tbl_documento_pk PRIMARY KEY ( codigo_publicacion );

CREATE TABLE tbl_educacion (
    codigo_educacion      NUMBER NOT NULL,
    titulo                VARCHAR2(100),
    disciplina_academica  VARCHAR2(100),
    nota                  VARCHAR2(80),
    actividades_grupos    VARCHAR2(2000),
    descripcion           VARCHAR2(2000),
    codigo_usuario        NUMBER NOT NULL,
    institucion_educativa NUMBER NOT NULL,
    mes_inicio            VARCHAR2(20),
    mes_final             VARCHAR2(20),
    anio_inicio           VARCHAR2(20),
    anio_final            VARCHAR2(40)
);

ALTER TABLE tbl_educacion ADD CONSTRAINT tbl_educacion_pk PRIMARY KEY ( codigo_educacion );

CREATE TABLE tbl_empleos (
    codigo_publicacion NUMBER NOT NULL,
    cargo              VARCHAR2(30) NOT NULL,
    tipo_empleo        NUMBER NOT NULL,
    empresa            NUMBER NOT NULL,
    tipo_lugar_trabajo NUMBER NOT NULL,
    ubicacion_empleo   NUMBER NOT NULL,
    descripcion_empleo VARCHAR2(4000) NOT NULL,
    medio_solicitudes  VARCHAR2(4000) NOT NULL
);

ALTER TABLE tbl_empleos ADD CONSTRAINT tbl_busqueda_personal_pk PRIMARY KEY ( codigo_publicacion );

CREATE TABLE tbl_empresas (
    codigo_empresas NUMBER NOT NULL,
    nombre_empresas VARCHAR2(4000) NOT NULL,
    foto_empresa    CLOB
);

ALTER TABLE tbl_empresas ADD CONSTRAINT tbl_empresas_pk PRIMARY KEY ( codigo_empresas );

CREATE TABLE tbl_encuestas (
    codigo_publicacion NUMBER NOT NULL,
    pregunta           VARCHAR2(140) NOT NULL,
    primera_opcion     VARCHAR2(30) NOT NULL,
    segunda_opcion     VARCHAR2(30) NOT NULL,
    tercer_opcion      VARCHAR2(30),
    cuarta_opcion      VARCHAR2(30)
);

ALTER TABLE tbl_encuestas ADD CONSTRAINT tbl_encuesta_pk PRIMARY KEY ( codigo_publicacion );

CREATE TABLE tbl_estado_conexiones (
    codigo_estado NUMBER NOT NULL,
    estado        VARCHAR2(15) NOT NULL
);

ALTER TABLE tbl_estado_conexiones ADD CONSTRAINT tbl_estado_conexiones_pk PRIMARY KEY ( codigo_estado );

CREATE TABLE tbl_estados_seguimientos (
    codigo_estado NUMBER NOT NULL,
    nombre_estado VARCHAR2(30) NOT NULL,
    descripcion   VARCHAR2(100)
);

ALTER TABLE tbl_estados_seguimientos ADD CONSTRAINT tbl_estados_seguimientos_pk PRIMARY KEY ( codigo_estado );

CREATE TABLE tbl_etiquetas (
    codigo_etiqueta    NUMBER NOT NULL,
    usuario_etiquetado NUMBER NOT NULL,
    codigo_publicacion NUMBER NOT NULL
);

ALTER TABLE tbl_etiquetas ADD CONSTRAINT tbl_etiquetas_pk PRIMARY KEY ( codigo_etiqueta );

CREATE TABLE tbl_eventos (
    codigo_publicacion NUMBER NOT NULL,
    foto_portada       CLOB,
    nombre_evento      VARCHAR2(75) NOT NULL,
    fecha_inicio       DATE NOT NULL,
    hora_inicio        DATE NOT NULL,
    fecha_final        DATE,
    hora_final         DATE,
    descripcion        VARCHAR2(4000),
    enlace_evento      VARCHAR2(500),
    direccion_evento   NUMBER NOT NULL,
    ubicacion_evento   VARCHAR2(1000)
);

ALTER TABLE tbl_eventos ADD CONSTRAINT tbl_evento_pk PRIMARY KEY ( codigo_publicacion );

CREATE TABLE tbl_experiencias (
    codigo_experiencia NUMBER NOT NULL,
    cargo              VARCHAR2(500) NOT NULL,
    fecha_finalizacion DATE,
    descripcion        VARCHAR2(2000),
    codigo_usuario     NUMBER NOT NULL,
    tipo_empleo        NUMBER NOT NULL,
    empresa            NUMBER NOT NULL,
    anio_inicio        VARCHAR2(20) NOT NULL,
    anio_final         VARCHAR2(20),
    mes_inicio         VARCHAR2(20),
    mes_final          VARCHAR2(20),
    tipo_lugar_trabajo NUMBER NOT NULL
);

ALTER TABLE tbl_experiencias ADD CONSTRAINT tbl_experiencias_pk PRIMARY KEY ( codigo_experiencia );

CREATE TABLE tbl_instituciones_educativas (
    codigo_institucion_educativa NUMBER NOT NULL,
    nombre_institucion_educativa VARCHAR2(100) NOT NULL,
    foto_institucion             CLOB
);

ALTER TABLE tbl_instituciones_educativas ADD CONSTRAINT tbl_organizaciones_pk PRIMARY KEY ( codigo_institucion_educativa );

CREATE TABLE tbl_mensajes (
    codigo_mensaje  NUMBER NOT NULL,
    mensaje         CLOB,
    fecha_mensaje   DATE,
    codigo_chat     NUMBER NOT NULL,
    mensaje_padre   NUMBER,
    codigo_usuario  NUMBER NOT NULL,
    codigo_receptor NUMBER NOT NULL,
    codigo_emisor   NUMBER NOT NULL
);

ALTER TABLE tbl_mensajes ADD CONSTRAINT tbl_mensajes_pk PRIMARY KEY ( codigo_mensaje );

CREATE TABLE tbl_multimedias (
    codigo_multimedia  NUMBER NOT NULL,
    codigo_publicacion NUMBER NOT NULL,
    multimedia         BLOB NOT NULL,
    texto_alternativo  VARCHAR2(1000)
);

ALTER TABLE tbl_multimedias ADD CONSTRAINT tbl_multimedia_pk PRIMARY KEY ( codigo_multimedia );

CREATE TABLE tbl_paises (
    codigo_pais VARCHAR2(5) NOT NULL,
    nombre      VARCHAR2(4000) NOT NULL
);

ALTER TABLE tbl_paises ADD CONSTRAINT tbl_regiones_pk PRIMARY KEY ( codigo_pais );

CREATE TABLE tbl_preguntas_empleo (
    codigo_pregunta    NUMBER NOT NULL,
    pregunta           VARCHAR2(4000) NOT NULL,
    codigo_publicacion NUMBER NOT NULL
);

ALTER TABLE tbl_preguntas_empleo ADD CONSTRAINT tbl_preguntas_empleo_pk PRIMARY KEY ( codigo_pregunta );

CREATE TABLE tbl_publicaciones (
    codigo_publicacion NUMBER NOT NULL,
    codigo_usuario     NUMBER NOT NULL,
    texto_descripcion  VARCHAR2(100),
    colaboracion_marca CHAR(1),
    codigo_visibilidad NUMBER NOT NULL,
    fecha_publicacion  DATE
);

ALTER TABLE tbl_publicaciones ADD CONSTRAINT tbl_publicaciones_pk PRIMARY KEY ( codigo_publicacion );

CREATE TABLE tbl_publicaciones_guardadas (
    codigo_usuario     NUMBER NOT NULL,
    codigo_publicacion NUMBER NOT NULL
);

ALTER TABLE tbl_publicaciones_guardadas ADD CONSTRAINT tbl_publicaciones_guardadas_pk PRIMARY KEY ( codigo_usuario,
                                                                                                    codigo_publicacion );

CREATE TABLE tbl_reacciones (
    codigo_reaccion    NUMBER NOT NULL,
    reaccion           VARCHAR2(10),
    codigo_usuario     NUMBER NOT NULL,
    codigo_publicacion NUMBER,
    codigo_comentario  NUMBER
);

ALTER TABLE tbl_reacciones ADD CONSTRAINT tbl_reacciones_pk PRIMARY KEY ( codigo_reaccion );

CREATE TABLE tbl_seguimientos (
    codigo          NUMBER NOT NULL,
    fecha_seguidor  DATE,
    codigo_seguido  NUMBER NOT NULL,
    codigo_seguidor NUMBER NOT NULL,
    estado_seguidor NUMBER NOT NULL
);

ALTER TABLE tbl_seguimientos ADD CONSTRAINT tbl_seguimientos_pk PRIMARY KEY ( codigo );

CREATE TABLE tbl_servicios_especialista (
    codigo_publicacion        NUMBER NOT NULL,
    categoria_especialista NUMBER NOT NULL,
    codigo_servicio           NUMBER NOT NULL,
    ubicacion                 NUMBER NOT NULL
);

ALTER TABLE tbl_servicios_especialista ADD CONSTRAINT tbl_servicio_especialista_pkv2 PRIMARY KEY ( codigo_publicacion );

CREATE TABLE tbl_sitios_web (
    codigo_url     NUMBER NOT NULL,
    codigo_usuario NUMBER NOT NULL,
    url            VARCHAR2(500) NOT NULL,
    texto_enlace   VARCHAR2(1000),
    tipo_sitio_web NUMBER NOT NULL
);

ALTER TABLE tbl_sitios_web ADD CONSTRAINT tbl_sitios_web_pk PRIMARY KEY ( codigo_url );

CREATE TABLE tbl_status_chats (
    codigo_status NUMBER NOT NULL,
    status_chats  VARCHAR2(4000)
);

ALTER TABLE tbl_status_chats ADD CONSTRAINT tbl_status_chats_pk PRIMARY KEY ( codigo_status );

CREATE TABLE tbl_tipo_celebraciones (
    codigo_celebracion NUMBER NOT NULL,
    nombre_celebracion VARCHAR2(30) NOT NULL,
    texto_celebracion  VARCHAR2(1000) NOT NULL
);

ALTER TABLE tbl_tipo_celebraciones ADD CONSTRAINT tbl_tipo_celebraciones_pk PRIMARY KEY ( codigo_celebracion );

CREATE TABLE tbl_tipo_empleos (
    codigo_tipo_empleo NUMBER NOT NULL,
    tipo_empleo        VARCHAR2(50) NOT NULL
);

ALTER TABLE tbl_tipo_empleos ADD CONSTRAINT tbl_tipo_empleo_pk PRIMARY KEY ( codigo_tipo_empleo );

CREATE TABLE tbl_tipos_lugar_trabajo (
    codigo_tipo_lugar_trabajo NUMBER NOT NULL,
    tipo_lugar_trabajo        VARCHAR2(20) NOT NULL
);

ALTER TABLE tbl_tipos_lugar_trabajo ADD CONSTRAINT tbl_tipo_lugar_trabajo_pk PRIMARY KEY ( codigo_tipo_lugar_trabajo );

CREATE TABLE tbl_tipos_web (
    codigo_tipo_web NUMBER NOT NULL,
    tipo_sitio_web  VARCHAR2(20) NOT NULL
);

ALTER TABLE tbl_tipos_web ADD CONSTRAINT tbl_tipo_web_pk PRIMARY KEY ( codigo_tipo_web );

CREATE TABLE tbl_usuarios (
    codigo_usuario   NUMBER NOT NULL,
    nombre           VARCHAR2(100) NOT NULL,
    apellidos        VARCHAR2(100) NOT NULL,
    nombre_adicional VARCHAR2(20),
    titular          CLOB,
    sector           VARCHAR2(100),
    url_perfil       VARCHAR2(100),
    fecha_nacimiento DATE,
    visibilidad      NUMBER,
    pais             VARCHAR2(5) NOT NULL,
    ciudad           NUMBER NOT NULL,
    email            VARCHAR2(200),
    contrasenia      VARCHAR2(100) NOT NULL,
    foto_perfil      CLOB,
    foto_portada     CLOB
);

ALTER TABLE tbl_usuarios ADD CONSTRAINT tbl_usuario_pk PRIMARY KEY ( codigo_usuario );

CREATE TABLE tbl_usuarios_chats (
    codigo_usuario NUMBER NOT NULL,
    codigo_chat    NUMBER NOT NULL,
    status_chat    NUMBER NOT NULL
);

ALTER TABLE tbl_usuarios_chats ADD CONSTRAINT tbl_usuario_chats_pk PRIMARY KEY ( codigo_usuario,
                                                                                 codigo_chat );

CREATE TABLE tbl_visibilidades (
    codigo_visibilidad NUMBER NOT NULL,
    visibilidad        VARCHAR2(10) NOT NULL
);

ALTER TABLE tbl_visibilidades ADD CONSTRAINT tbl_visibilidad_pk PRIMARY KEY ( codigo_visibilidad );

ALTER TABLE tbl_conexiones
    ADD CONSTRAINT conexiones_estado_conexion_fk FOREIGN KEY ( codigo_estado )
        REFERENCES tbl_estado_conexiones ( codigo_estado );

ALTER TABLE tbl_conexiones
    ADD CONSTRAINT conexiones_usuarios_fk2 FOREIGN KEY ( codigo_usuario_2 )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_experiencias
    ADD CONSTRAINT experiencias_lugar_trabajo_fk FOREIGN KEY ( tipo_lugar_trabajo )
        REFERENCES tbl_tipos_lugar_trabajo ( codigo_tipo_lugar_trabajo );

ALTER TABLE tbl_servicios_especialista
    ADD CONSTRAINT fk_categoria_servicios FOREIGN KEY ( categoria_especialista )
        REFERENCES tbl_categorias_especialista ( codigo_categoria_especialista );

ALTER TABLE tbl_eventos
    ADD CONSTRAINT fk_ciudad_evento FOREIGN KEY ( direccion_evento )
        REFERENCES tbl_ciudades ( codigo_ciudad );

ALTER TABLE tbl_ciudades
    ADD CONSTRAINT fk_ciudad_padre FOREIGN KEY ( ciudad_padre )
        REFERENCES tbl_ciudades ( codigo_ciudad );

ALTER TABLE tbl_servicios_especialista
    ADD CONSTRAINT fk_ciudad_servicios FOREIGN KEY ( ubicacion )
        REFERENCES tbl_ciudades ( codigo_ciudad );

ALTER TABLE tbl_eventos
    ADD CONSTRAINT fk_codigo_publicacionv4 FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_usuarios
    ADD CONSTRAINT fk_codigo_visibilidad FOREIGN KEY ( visibilidad )
        REFERENCES tbl_visibilidades ( codigo_visibilidad );

ALTER TABLE tbl_comentarios
    ADD CONSTRAINT fk_comentario_padre FOREIGN KEY ( comentario_padre )
        REFERENCES tbl_comentarios ( codigo_comentario );

ALTER TABLE tbl_reacciones
    ADD CONSTRAINT fk_comentario_reaccion FOREIGN KEY ( codigo_comentario )
        REFERENCES tbl_comentarios ( codigo_comentario );

ALTER TABLE tbl_empleos
    ADD CONSTRAINT fk_empresa_empleo FOREIGN KEY ( empresa )
        REFERENCES tbl_empresas ( codigo_empresas );

ALTER TABLE tbl_experiencias
    ADD CONSTRAINT fk_empresa_experiencia FOREIGN KEY ( empresa )
        REFERENCES tbl_empresas ( codigo_empresas );

ALTER TABLE tbl_educacion
    ADD CONSTRAINT fk_institucion_educativa FOREIGN KEY ( institucion_educativa )
        REFERENCES tbl_instituciones_educativas ( codigo_institucion_educativa );

ALTER TABLE tbl_celebraciones
    ADD CONSTRAINT fk_publicacion_celebracion FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_documentos
    ADD CONSTRAINT fk_publicacion_documento FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_empleos
    ADD CONSTRAINT fk_publicacion_empleo FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_preguntas_empleo
    ADD CONSTRAINT fk_publicacion_empleov2 FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_empleos ( codigo_publicacion );

ALTER TABLE tbl_encuestas
    ADD CONSTRAINT fk_publicacion_encuesta FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_etiquetas
    ADD CONSTRAINT fk_publicacion_etiqueta FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_publicaciones_guardadas
    ADD CONSTRAINT fk_publicacion_guardada FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_multimedias
    ADD CONSTRAINT fk_publicacion_multimedia FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_reacciones
    ADD CONSTRAINT fk_publicacion_reaccion FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_servicios_especialista
    ADD CONSTRAINT fk_publicacion_servicios FOREIGN KEY ( codigo_publicacion )
        REFERENCES tbl_publicaciones ( codigo_publicacion );

ALTER TABLE tbl_usuarios_chats
    ADD CONSTRAINT fk_status_chat FOREIGN KEY ( status_chat )
        REFERENCES tbl_status_chats ( codigo_status );

ALTER TABLE tbl_celebraciones
    ADD CONSTRAINT fk_tipo_celebracion FOREIGN KEY ( tipo_celebracion )
        REFERENCES tbl_tipo_celebraciones ( codigo_celebracion );

ALTER TABLE tbl_empleos
    ADD CONSTRAINT fk_tipo_empleo FOREIGN KEY ( tipo_empleo )
        REFERENCES tbl_tipo_empleos ( codigo_tipo_empleo );

ALTER TABLE tbl_experiencias
    ADD CONSTRAINT fk_tipo_empleo_exp FOREIGN KEY ( tipo_empleo )
        REFERENCES tbl_tipo_empleos ( codigo_tipo_empleo );

ALTER TABLE tbl_empleos
    ADD CONSTRAINT fk_tipo_lugar_empleo FOREIGN KEY ( tipo_lugar_trabajo )
        REFERENCES tbl_tipos_lugar_trabajo ( codigo_tipo_lugar_trabajo );

ALTER TABLE tbl_sitios_web
    ADD CONSTRAINT fk_tipo_web FOREIGN KEY ( tipo_sitio_web )
        REFERENCES tbl_tipos_web ( codigo_tipo_web );

ALTER TABLE tbl_usuarios_chats
    ADD CONSTRAINT fk_usuario_chat FOREIGN KEY ( codigo_usuario )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_educacion
    ADD CONSTRAINT fk_usuario_educacion FOREIGN KEY ( codigo_usuario )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_etiquetas
    ADD CONSTRAINT fk_usuario_etiquetado FOREIGN KEY ( usuario_etiquetado )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_experiencias
    ADD CONSTRAINT fk_usuario_experiencia FOREIGN KEY ( codigo_usuario )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_publicaciones
    ADD CONSTRAINT fk_usuario_publicacion FOREIGN KEY ( codigo_usuario )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_publicaciones_guardadas
    ADD CONSTRAINT fk_usuario_publicacion_guarda FOREIGN KEY ( codigo_usuario )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_reacciones
    ADD CONSTRAINT fk_usuario_reaccion FOREIGN KEY ( codigo_usuario )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_sitios_web
    ADD CONSTRAINT fk_usuario_sitio_web FOREIGN KEY ( codigo_usuario )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_publicaciones
    ADD CONSTRAINT fk_visibilidad_publicacion FOREIGN KEY ( codigo_visibilidad )
        REFERENCES tbl_visibilidades ( codigo_visibilidad );

ALTER TABLE tbl_seguimientos
    ADD CONSTRAINT seguimientos_estados_fk FOREIGN KEY ( estado_seguidor )
        REFERENCES tbl_estados_seguimientos ( codigo_estado );

ALTER TABLE tbl_seguimientos
    ADD CONSTRAINT seguimientos_usuarios_fk FOREIGN KEY ( codigo_seguidor )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_seguimientos
    ADD CONSTRAINT seguimientos_usuarios_fkv1 FOREIGN KEY ( codigo_seguido )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_usuarios
    ADD CONSTRAINT tbl_ciudad_usuario FOREIGN KEY ( ciudad )
        REFERENCES tbl_ciudades ( codigo_ciudad );

ALTER TABLE tbl_ciudades
    ADD CONSTRAINT tbl_ciudades_tbl_paises_fk FOREIGN KEY ( pais )
        REFERENCES tbl_paises ( codigo_pais );

ALTER TABLE tbl_conexiones
    ADD CONSTRAINT tbl_conexiones_tbl_usuarios_fk FOREIGN KEY ( codigo_usuario_1 )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_empleos
    ADD CONSTRAINT tbl_empleos_tbl_ciudad_fk FOREIGN KEY ( ubicacion_empleo )
        REFERENCES tbl_ciudades ( codigo_ciudad );

ALTER TABLE tbl_mensajes
    ADD CONSTRAINT tbl_mensajes_padre FOREIGN KEY ( mensaje_padre )
        REFERENCES tbl_mensajes ( codigo_mensaje );

ALTER TABLE tbl_mensajes
    ADD CONSTRAINT tbl_mensajes_tbl_usuarios_fk FOREIGN KEY ( codigo_receptor )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_mensajes
    ADD CONSTRAINT tbl_mensajes_tbl_usuarios_fkv2 FOREIGN KEY ( codigo_emisor )
        REFERENCES tbl_usuarios ( codigo_usuario );

ALTER TABLE tbl_usuarios
    ADD CONSTRAINT tbl_regiones_fk FOREIGN KEY ( pais )
        REFERENCES tbl_paises ( codigo_pais );

CREATE SEQUENCE codigo_educacion_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_educacion_pk BEFORE
    INSERT ON tbl_educacion
    FOR EACH ROW
    WHEN ( new.codigo_educacion IS NULL )
BEGIN
    :new.codigo_educacion := codigo_educacion_pk.nextval;
END;
/

CREATE SEQUENCE codigo_experiencia_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER  codigo_experiencia_pk BEFORE
    INSERT ON tbl_experiencias
    FOR EACH ROW
    WHEN ( new.codigo_experiencia IS NULL )
BEGIN
    :new.codigo_experiencia :=  codigo_experiencia_pk.nextval;
END;
/

CREATE SEQUENCE codigo_usuario_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_usuario_pk BEFORE
    INSERT ON tbl_usuarios
    FOR EACH ROW
    WHEN ( new.codigo_usuario IS NULL )
BEGIN
    :new.codigo_usuario := codigo_usuario_pk.nextval;
END;

CREATE SEQUENCE codigo_etiqueta_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_etiqueta_pk BEFORE
    INSERT ON tbl_etiquetas
    FOR EACH ROW
    WHEN ( new.codigo_etiqueta IS NULL )
BEGIN
    :new.codigo_etiqueta := codigo_etiqueta_pk.nextval;
END;
/

CREATE SEQUENCE codigo_publicacion_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_publicacion_pk BEFORE
    INSERT ON tbl_eventos
    FOR EACH ROW
    WHEN ( new.codigo_publicacion IS NULL )
BEGIN
    :new.codigo_publicacion := codigo_publicacion_pk.nextval;
END;
/


CREATE SEQUENCE codigo_mensaje_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_mensaje_pk BEFORE
    INSERT ON tbl_mensajes
    FOR EACH ROW
    WHEN ( new.codigo_mensaje IS NULL )
BEGIN
    :new.codigo_mensaje := codigo_mensaje_pk.nextval;
END;
/

CREATE SEQUENCE codigo_reaccion_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_reaccion_pk BEFORE
    INSERT ON tbl_reacciones
    FOR EACH ROW
    WHEN ( new.codigo_reaccion IS NULL )
BEGIN
    :new.codigo_reaccion := codigo_reaccion_pk.nextval;
END;
/

CREATE SEQUENCE codigo_url_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_url_pk BEFORE
    INSERT ON tbl_sitios_web
    FOR EACH ROW
    WHEN ( new.codigo_url IS NULL )
BEGIN
    :new.codigo_url := codigo_url_pk.nextval;
END;
/

CREATE SEQUENCE codigo_conexion_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER codigo_conexion_pk BEFORE
    INSERT ON tbl_conexiones
    FOR EACH ROW
    WHEN ( new.codigo_conexion IS NULL )
BEGIN
    :new.codigo_conexion := codigo_conexion_pk.nextval;
END;
/


CREATE SEQUENCE id_seguimientos_pk START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER id_seguimientos_pk BEFORE
    INSERT ON tbl_seguimientos
    FOR EACH ROW
    WHEN ( new.codigo IS NULL )
BEGIN
    :new.codigo := id_seguimientos_pk.nextval;
END;
/



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            34
-- CREATE INDEX                             0
-- ALTER TABLE                             84
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           9
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          9
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
