-- paltacad
-- TPINT_GRUPO_2_LAB4

CREATE SCHEMA paltacad;

USE paltacad;

CREATE TABLE localidades (
    PRIMARY KEY (localidad_id),
    localidad_id INT NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR(50)
);

CREATE TABLE nacionalidades (
    PRIMARY KEY (nacionalidad_id),
    nacionalidad_id INT NOT NULL AUTO_INCREMENT,
    nombre          VARCHAR(50)
);

CREATE TABLE provincias (
    PRIMARY KEY (provincia_id),
    provincia_id INT NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR(50)
);

CREATE TABLE alumnos (
    PRIMARY KEY (legajo),
    FOREIGN KEY (nacionalidad_id) REFERENCES nacionalidades(nacionalidad_id),
    FOREIGN KEY (provincia_id)    REFERENCES provincias(provincia_id),
    estado          BIT NOT NULL,
    legajo          INT NOT NULL,
    dni             INT UNIQUE,
    nombre          VARCHAR(50),
    nacimiento      DATE,
    domicilio       VARCHAR(50),
    nacionalidad_id INT NOT NULL,
    provincia_id    INT NOT NULL,
    email           VARCHAR(50),
    telefono        VARCHAR(25)
);

CREATE TABLE docentes (
    PRIMARY KEY (legajo),
    FOREIGN KEY (localidad_id)    REFERENCES localidades(localidad_id),
    FOREIGN KEY (nacionalidad_id) REFERENCES nacionalidades(nacionalidad_id),
    estado          BIT NOT NULL,
    legajo          INT NOT NULL,
    dni             INT UNIQUE,
    nombre          VARCHAR(50),
    nacimiento      DATE,
    domicilio       VARCHAR(50),
    localidad_id    INT NOT NULL,
    nacionalidad_id INT NOT NULL,
    email           VARCHAR(50),
    telefono        VARCHAR(25),
    contrasenia     VARCHAR(25)
);

CREATE TABLE materias (
    PRIMARY KEY (materia_id),
    materia_id INT NOT NULL AUTO_INCREMENT,
    nombre     VARCHAR(50)
);

CREATE TABLE cursos (
    PRIMARY KEY (curso_id),
    FOREIGN KEY (docente_legajo) REFERENCES docentes(legajo),
    FOREIGN KEY (materia_id)     REFERENCES materias(materia_id),
    curso_id       INT NOT NULL AUTO_INCREMENT,
    docente_legajo INT NOT NULL,
    materia_id     INT NOT NULL,
    semestre       INT,
    anio           INT
);

CREATE TABLE notas (
    PRIMARY KEY (curso_id, alumno_legajo),
    FOREIGN KEY (curso_id)      REFERENCES cursos(curso_id),
    FOREIGN KEY (alumno_legajo) REFERENCES alumnos(legajo),
    curso_id      INT NOT NULL,
    alumno_legajo INT NOT NULL,
    parcial1      DECIMAL(4,2),
    parcial2      DECIMAL(4,2),
    recu1         DECIMAL(4,2),
    recu2         DECIMAL(4,2),
    estado        BIT
);

-- paltacad insertions

INSERT INTO localidades (nombre)
VALUES ("Carapachay"),
       ("Florida"),
       ("La Lucila"),
       ("Munro"),
       ("Olivos"),
       ("Villa Adelina"),
       ("Boulogne Sur Mer"),
       ("Martínez"),
       ("Acassuso"),
       ("Beccar"),
       ("Virreyes"),
       ("Victoria"),
       ("Don Torcuato"),
       ("General Pacheco"),
       ("El Talar");

INSERT INTO nacionalidades (nombre)
VALUES ("Argentino/a"),
       ("Chileno/a"),
       ("Uruguayo/a"),
       ("Paraguayo/a"),
       ("Boliviano/a"),
       ("Brasileño/a"),
       ("Peruano/a"),
       ("Ecuatoriano/a"),
       ("Colombiano/a"),
       ("Panameño/a"),
       ("Venezolano/a"),
       ("Costarricense"),
       ("Salvadoreño/a"),
       ("Guatemalteco/a"),
       ("Mexicano/a");

INSERT INTO provincias (nombre)
VALUES ("Buenos Aires"),
       ("La Pampa"),
       ("Río Negro"),
       ("Neuquen"),
       ("Mendoza"),
       ("San Luis"),
       ("Cordoba"),
       ("Santa Fe"),
       ("Entre Rios"),
       ("Corrientes"),
       ("San Juan"),
       ("La Rioja"),
       ("Catamarca"),
       ("Tucuman"),
       ("Santiago del Estero");

INSERT INTO alumnos (estado, dni, legajo, nombre, nacimiento, domicilio, nacionalidad_id, provincia_id, email, telefono)
VALUES	(1, 37037257, 68799, "Juan David Llamas", "1993-08-01", "Paredes 18", 1, 8, "ornela33@gmail.com", "(490)15560-2562"),
        (1, 10724653, 91086, "Maximo Menchaca", "1975-08-04", "Ibarra 66667", 6, 7, "jaguilar@espinosa.net", "(5911)1544-1113"),
        (1, 84035507, 46881, "Julia Dominguez", "1950-05-31", "Delfina 3196 6 C", 9, 6, "michelle.sisneros@gmail.com", "(20)6780-9281"),
        (1, 70717995, 39655, "Gabriel Carranza Tercero", "1951-11-30", "Axel 9 Piso 8", 9, 8, "paulina.altamirano@yahoo.com", "(8641)1559-9495"),
        (1, 99414347, 23337, "Vicente Esparza", "1956-01-08", "Quezada 1307 98 8", 8, 8, "oheredia@hotmail.com", "(36)4752-1657"),
        (1, 85319853, 31499, "Valery Frias", "1979-11-28", "Ulibarri 048", 4, 8, "velasquez.valentino@zamudio.com", "(163)15516-4760"),
        (1, 13671084, 80785, "Adriana Madrid", "1962-05-25", "Araña 79", 7, 9, "meza.isidora@gmail.com", "(92)155140-3308"),
        (1, 79414710, 83859, "Luana Moreno Segundo", "1964-02-16", "Sergio 01 Depto. 215", 2, 15, "dsantiago@jaquez.net", "(6939)1544-1015"),
        (1, 67024413, 39071, "Sofia Tapia Hijo", "1995-07-29", "Vicente 3145 5 F", 5, 5, "adrian02@yahoo.com", "(933)502-4020"),
        (1, 33174132, 57311, "Kevin Fonseca", "1968-08-15", "Delafuente 204", 2, 4, "carrillo.ivanna@acevedo.info", "(805)15469-5671"),
        (1, 58910379, 46610, "Emiliano Zambrano Tercero", "1958-04-12", "Sedillo 06 80 F", 6, 8, "rebeca64@hotmail.com", "(802)420-4714"),
        (1, 25754184, 16176, "Miranda Pagan", "1994-08-13", "Soria 1042", 4, 2, "grijalva.luciana@castaneda.net", "(728)15518-4234"),
        (1, 71766000, 71135, "Rafael Robledo Tercero", "1960-05-30", "Orta 2156 Depto. 106", 1, 6, "juanpablo98@yahoo.com", "(305)434-9316"),
        (1, 48072426, 89382, "Antonella Sanabria", "1989-07-17", "Rojas 14539", 3, 9, "alexa.marquez@villareal.com", "(847)15530-4085"),
        (1, 27927758, 72887, "Amelia Figueroa", "1935-12-21", "Elena 7720", 7, 2, "icenteno@yahoo.com", "(610)15476-5755");

INSERT INTO docentes (estado, dni, legajo, nombre, nacimiento, domicilio, localidad_id, nacionalidad_id, email, telefono, contrasenia)
VALUES	(1, 79104654, 85236, "Thiago Archuleta", "1970-07-07", "Robledo 60961 87 B", 8, 7, "kevin.apodaca@esparza.com", "(055)15590-1972", "jgranado"),
        (1, 50147786, 89781, "Ricardo Peres", "1987-10-17", "Delafuente 27", 2, 7, "luciana95@hotmail.com", "(1236)47-6946", "maximiliano.porras"),
        (1, 61209282, 35774, "Juan Pablo Zuñiga", "1980-05-06", "Christian 9 19 F", 1, 9, "mario.bustos@hotmail.com", "(5315)40-0541", "macias.matthew"),
        (1, 12412345, 85158, "Axel Arteaga", "1992-08-27", "Gallardo 3 22 A", 2, 2, "mariaalejandra.cardenas@puente.biz", "(3977)41-0238", "esteban03"),
        (1, 12091167, 19662, "Nahuel Quintana", "1994-09-24", "Rojas 5 Hab. 819", 1, 9, "natalia56@olmos.com", "(7372)41-2713", "catalina99"),
        (1, 78658363, 56793, "Emiliano Paredes", "1998-11-29", "Ian 6644", 4, 4, "andrea18@gmail.com", "(4170)44-0948", "agustin06"),
        (1, 17050486, 57647, "Bianca Arriaga Hijo", "1999-06-23", "Ivanna 86", 8, 6, "duenas.hidalgo@gmail.com", "(26)155251-6016", "amanda54"),
        (1, 67762434, 66423, "Elizabeth Cruz", "1944-04-03", "Mateo 90903 6 F", 3, 6, "bnazario@villarreal.com", "(58)155297-2928", "villegas.jeronimo"),
        (1, 85851574, 58764, "Christian Velasco", "1940-09-13", "Roque 3754 Piso 81", 8, 1, "hipolito.delgado@gmail.com", "(19)154007-0465", "fernando.gomez"),
        (1, 48059638, 24175, "Leonardo Serrato", "1952-06-15", "Abigail 370 30 E", 7, 3, "ytoro@gmail.com", "(3670)1551-3321", "fatima40"),
        (1, 98982327, 86205, "Julia Amaya Segundo", "1956-08-16", "Eduardo 56326 56 D", 5, 6, "serrano.salome@hotmail.com", "(7756)47-1714", "jaramillo.felipe"),
        (1, 75564109, 53875, "Hidalgo Centeno", "1959-09-07", "Castro 01551 PB A", 5, 1, "alan.loera@castellanos.com", "(0778)1547-7902", "pmaya"),
        (1, 66143610, 95733, "Alejandra Luna", "1981-04-29", "Adrian 40967 1 D", 7, 1, "mozuna@ortiz.com", "(876)482-0264", "renata85"),
        (1, 95485725, 26629, "Simon Cervantez", "1942-01-10", "Cortes 248", 4, 8, "rascon.jacobo@barrientos.info", "112-666-9581", "ycorrales"),
        (1, 52566856, 21650, "Irene Tejada", "1993-10-04", "Montserrat 305", 3, 5, "fatima70@collazo.com", "(95)4697-1183", "aguayo.carla");

INSERT INTO materias (nombre)
VALUES ("Arquitectura y Sistemas Operativos"),
       ("Estadistica"),
       ("Laboratorio de Computacion I"),
       ("Laboratorio de Computacion II"),
       ("Laboratorio de Computacion III"),
       ("Laboratorio de Computacion IV"),
       ("Matematica"),
       ("Metodologia de la Investigacion"),
       ("Programacion I"),
       ("Programacion II"),
       ("Programacion III"),
       ("Sistemas de Procesamiento de Datos"),
       ("Diseño y Administracion de Bases de Dato"),
       ("Elementos de Investigacion Operativa"),
       ("Legislacion");

INSERT INTO cursos (docente_legajo, materia_id, semestre, anio)
VALUES (21650, 11, 1, 2018),
       (95733, 1, 1, 2005),
       (66423, 2, 2, 2000),
       (57647, 7, 2, 2008),
       (26629, 3, 2, 2013),
       (57647, 5, 2, 2021),
       (89781, 9, 2, 2016),
       (57647, 15, 1, 2015),
       (95733, 9, 1, 2001),
       (56793, 7, 1, 2004),
       (24175, 12, 2, 2016),
       (56793, 14, 1, 2015),
       (66423, 7, 1, 2001),
       (35774, 7, 2, 2011),
       (57647, 2, 1, 2011);

INSERT INTO notas (curso_id, alumno_legajo, parcial1, parcial2, recu1, recu2, estado)
VALUES (1, 83859, 3, 5, 5, 3, 0),
       (1, 68799, 7, 6, 0, 0, 0),
       (1, 39071, 3, 1, 4, 1, 0),
       (1, 72887, 7, 1, 0, 5, 0),
       (1, 39655, 0, 0, 0, 0, 0),
       (1, 57311, 0, 0, 0, 0, 0),
       (1, 71135, 0, 0, 0, 0, 0),
       (1, 16176, 0, 0, 0, 0, 0),
       (6, 46610, 2, 2, 9, 1, 0),
       (6, 80785, 10, 6, 0, 3, 0),
       (6, 23337, 5, 5, 5, 7, 0),
       (6, 46881, 9, 3, 5, 10, 0),
       (6, 89382, 0, 0, 0, 0, 0),
       (6, 31499, 0, 0, 0, 0, 0),
       (6, 91086, 0, 0, 0, 0, 0);

-- paltacad stored procedures
-- gets

CREATE PROCEDURE get_localidades()
    SELECT *
      FROM localidades;

CREATE PROCEDURE get_localidad_from_id(localidad_id INT)
    SELECT *
      FROM localidades l
     WHERE l.localidad_id = localidad_id;

CREATE PROCEDURE get_nacionalidades()
    SELECT *
      FROM nacionalidades;

CREATE PROCEDURE get_nacionalidad_from_id(nacionalidad_id INT)
    SELECT *
      FROM nacionalidades n
     WHERE n.nacionalidad_id = nacionalidad_id;

CREATE PROCEDURE get_provincias()
    SELECT *
      FROM provincias;

CREATE PROCEDURE get_provincia_from_id(provincia_id INT)
    SELECT *
      FROM provincias p
     WHERE p.provincia_id = provincia_id;

CREATE PROCEDURE get_alumnos()
    SELECT *
      FROM alumnos;

CREATE PROCEDURE get_alumnos_from_curso(id INT)
    SELECT *
      FROM alumnos a
           INNER JOIN notas n
           ON a.legajo = n.alumno_legajo
              AND n.curso_id = id;

CREATE PROCEDURE get_alumno_from_legajo(legajo INT)
    SELECT *
      FROM alumnos a
     WHERE a.legajo = legajo;

CREATE PROCEDURE get_docentes()
    SELECT *
      FROM docentes;

CREATE PROCEDURE get_docente_from_legajo(legajo INT)
    SELECT *
      FROM docentes d
     WHERE d.legajo = legajo;

CREATE PROCEDURE get_docente_login(legajo INT, contrasenia VARCHAR(25))
    SELECT *
      FROM docentes d
     WHERE d.legajo = legajo
       AND d.contrasenia = contrasenia
       AND d.estado = 1;

CREATE PROCEDURE get_materias()
    SELECT *
      FROM materias;

CREATE PROCEDURE get_materia_from_id(id INT)
    SELECT *
      FROM materias m
     WHERE m.materia_id = id;

CREATE PROCEDURE get_cursos()
    SELECT *
      FROM cursos;

CREATE PROCEDURE get_cursos_docente(legajo INT)
    SELECT *
      FROM cursos c
     WHERE c.docente_legajo = legajo;

CREATE PROCEDURE get_curso_from_id(id INT)
    SELECT *
      FROM cursos c
     WHERE c.curso_id = id;

CREATE PROCEDURE get_notas(curso_id INT)
    SELECT *
      FROM notas n
     WHERE n.curso_id = curso_id;

-- crud

CREATE PROCEDURE delete_docente(legajo INT)
    UPDATE docentes d
       SET estado = 0
     WHERE d.legajo = legajo;

CREATE PROCEDURE insert_docente(dni INT, legajo INT, nombre VARCHAR(50),
                                nacimiento DATE, domicilio VARCHAR(50),
                                localidad_id INT, nacionalidad_id INT,
                                email VARCHAR(50), telefono VARCHAR(25),
                                contrasenia VARCHAR(25))
    INSERT INTO docentes(estado, dni, legajo, nombre, nacimiento, domicilio,
                         localidad_id, nacionalidad_id, email, telefono,
                         contrasenia)
    VALUES (1, dni, legajo, nombre, nacimiento, domicilio, localidad_id,
            nacionalidad_id, email, telefono, contrasenia);

CREATE PROCEDURE update_docente(estado BIT, dni INT, nombre VARCHAR(50),
                                nacimiento DATE, domicilio VARCHAR(50),
                                localidad_id INT, nacionalidad_id INT,
                                email VARCHAR(50), telefono VARCHAR(25),
                                contrasenia VARCHAR(25), legajo INT)
    UPDATE docentes
    SET docentes.estado = estado, docentes.dni = dni, docentes.nombre = nombre,
        docentes.nacimiento = nacimiento, docentes.domicilio = domicilio,
        docentes.localidad_id = localidad_id,
        docentes.nacionalidad_id = nacionalidad_id, docentes.email = email,
        docentes.telefono = telefono, docentes.contrasenia = contrasenia
    WHERE docentes.legajo = legajo;

CREATE PROCEDURE delete_alumno(legajo INT)
    UPDATE alumnos
    SET estado = 0
    WHERE alumnos.legajo = legajo;

CREATE PROCEDURE insert_alumno(dni INT, legajo INT, nombre VARCHAR(50),
                               nacimiento DATE, domicilio VARCHAR(50),
                               nacionalidad_id INT, provincia_id INT,
                               email VARCHAR(50), telefono VARCHAR(25))
    INSERT INTO alumnos(estado, dni, legajo, nombre, nacimiento, domicilio,
                         nacionalidad_id, provincia_id, email, telefono)
    VALUES (1, dni, legajo, nombre, nacimiento, domicilio, nacionalidad_id,
            provincia_id, email, telefono);

CREATE PROCEDURE update_alumno(estado BIT, dni INT, nombre VARCHAR(50),
                               nacimiento DATE, domicilio VARCHAR(50),
                               nacionalidad_id INT, provincia_id INT,
                               email VARCHAR(50), telefono VARCHAR(25),
                               legajo INT)
    UPDATE alumnos
    SET alumnos.estado = estado, alumnos.dni = dni, alumnos.nombre = nombre,
        alumnos.nacimiento = nacimiento, alumnos.domicilio = domicilio,
        alumnos.nacionalidad_id = nacionalidad_id,
        alumnos.provincia_id = provincia_id, alumnos.email = email,
        alumnos.telefono = telefono
    WHERE alumnos.legajo = legajo;

CREATE PROCEDURE insert_curso(docente_legajo INT, materia_id INT, semestre INT,
                              anio INT)
    INSERT INTO cursos(docente_legajo, materia_id, semestre, anio)
    VALUES (docente_legajo, materia_id, semestre, anio);

CREATE PROCEDURE insert_notas_alumno(curso_id INT, alumno_legajo INT)
    INSERT INTO notas(curso_id, alumno_legajo, parcial1, parcial2, recu1,
                      recu2, estado)
    VALUES (curso_id, alumno_legajo, 0, 0, 0, 0, 0);

CREATE PROCEDURE update_notas(curso_id INT, alumno_legajo INT,
                              parcial1 DECIMAL(4,2), parcial2 DECIMAL(4,2),
                              recu1 DECIMAL(4,2), recu2 DECIMAL(4,2), estado BIT)
    UPDATE notas n
       SET n.parcial1 = parcial1, n.parcial2 = parcial2, n.recu1 = recu1,
           n.recu2 = recu2, n.estado = estado
     WHERE n.curso_id = curso_id
       AND n.alumno_legajo = alumno_legajo;