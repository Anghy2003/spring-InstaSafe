insert into roles(nombre) values ('Admin');
insert into roles(nombre) values ('Guardia');
insert into roles(nombre) values ('Docente');
insert into roles(nombre) values ('Estudiante');

INSERT INTO usuario (cedula,nombre,apellido,correo,foto,genero,fechanacimiento,contrasena,fecharegistro,id_rol) VALUES ('0106944937','Melo','Illescas','melito@gmail.com','https://drive.google.com/file/d/1kOX4LOiNTGTfELh4PxgpbszZJrwFLs4q/view?usp=sharing','Femenino',TO_TIMESTAMP('20010-15-10 19:00:00', 'YYYY-MM-DD HH24:MI:SS'),'abcd1234',TO_TIMESTAMP('2025-07-08 20:42:31', 'YYYY-MM-DD HH24:MI:SS'),1);
