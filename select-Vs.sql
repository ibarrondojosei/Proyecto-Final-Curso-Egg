INSERT INTO `localidad` (`id`,`provincia_id`,`nombre`,`codigo_postal`) VALUES
(1,13,'CABEZA DE CHANCHO','3061'),
(2,13,'CAMPO GARAY','3066'),
(3,13,'CAMPO SAN JOSE','3060'),
(4,13,'COLONIA INDEPENDENCIA','3066'),
(5,13,'COLONIA MONTEFIORE','2341'),
(6,13,'EL AMARGO','3060'),
(7,13,'EL MARIANO','3060'),
(8,13,'EL NOCHERO','3061'),
(9,13,'ESTANCIA LA CIGUE','3060'),
(10,13,'ESTEBAN RAMS','3066'),
(11,13,'FORTIN ALERTA','3066'),
(12,13,'FORTIN ARGENTINA','3060'),
(13,13,'FORTIN ATAHUALPA','3061'),
(14,13,'FORTIN CACIQUE','3060'),
(15,13,'FORTIN SEIS DE CABALLERIA','3061'),
(16,13,'FORTIN TOSTADO','3060'),
(17,13,'GATO COLORADO','3541');

INSERT INTO `provincia`(`id`,`nombre`,`codigo`) VALUES
(1,'Ciudad Autónoma de Buenos Aires (CABA)','AR-C'),
(2,'Buenos Aires','AR-B'),
(3,'Catamarca','AR-K'),
(4,'Córdoba','AR-X'),
(5,'Corrientes','AR-W'),
(6,'Entre Ríos','AR-E'),
(7,'Jujuy','AR-Y'),
(8,'Mendoza','AR-M'),
(9,'La Rioja','AR-F'),
(10,'Salta','AR-A'),
(11,'San Juan','AR-J'),
(12,'San Luis','AR-D'),
(13,'Santa Fe','AR-S'),
(14,'Santiago del Estero','AR-G'),
(15,'Tucumán','AR-T'),
(16,'Chaco','AR-H'),
(17,'Chubut','AR-U'),
(18,'Formosa','AR-P'),
(19,'Misiones','AR-N'),
(20,'Neuquén','AR-Q'),
(21,'La Pampa','AR-L'),
(22,'Río Negro','AR-R'),
(23,'Santa Cruz','AR-Z'),
(24,'Tierra del Fuego','AR-V');

/*
INSERT INTO `planes_entrenamiento`.`alumno` (`id`, `alta`, `apellido`, `direccion`, `dni`, `email`, `fecha_nacimiento`, `nombre`, `password`, `password2`, `telefono`, `anamnesis_id`, `localidad_id`, `provincia_id`) VALUES ('1', b'1', 'molllll', 'alto 2139', '22562528', 'ale@gmail.com', '1970-05-06', 'ale', '123', '123', '4515151515', '1', '8', '13');
INSERT INTO `planes_entrenamiento`.`alumno` (`id`, `alta`, `apellido`, `direccion`, `dni`, `email`, `fecha_nacimiento`, `nombre`, `password`, `password2`, `telefono`, `anamnesis_id`, `localidad_id`, `provincia_id`) VALUES ('2', b'1', 'Jimenez', 'colon 1254', '32323233', 'jimenezjola@gmail.com', '1999-12-26', 'Carlos', '1234', '1234', '5656262', '1', '8', '13');
*/

INSERT INTO `planes_entrenamiento`.`profesor` (`id`, `alta`, `apellido`, `direccion`, `dni`, `email`, `fecha_nacimiento`, `nombre`, `password`, `password2`, `profesion`, `telefono`, `localidad_id`, `provincia_id`, `foto_perfil`,  `descripcion_actividad`) VALUES ('1', b'1', 'moll', 'jhjh 122', '22525252', 'alexmoll@hotmail.com', '1998-10-10', 'ale', '123', '123', 'PROFESOR_EN_EDUCACION_FISICA', '51555151', '8', '13', '23930ae3-e12d-4acf-8a1c-65b86179524e-file','me especializo en el alto rendimeinto, soy personal trainer internacional');
INSERT INTO `planes_entrenamiento`.`profesor` (`id`, `alta`, `apellido`, `direccion`, `dni`, `email`, `fecha_nacimiento`, `nombre`, `password`, `password2`, `profesion`, `telefono`, `localidad_id`, `provincia_id`, `foto_perfil`,  `descripcion_actividad`) VALUES ('2', b'0', 'lopez', 'kljlj 5454', '6565656', 'dhhd@gami', '1970-08-12', 'fer', '123', '123', 'TECNICO_EN_ACTIVIDADES_DE_MONTAÑA', '545454545', '8', '13', '64647cd7-e50f-4211-8345-1a73ee8f0844-file','soy unico en Sudamerica en mi especialidad, mi experiencia es de 20 años');
INSERT INTO `planes_entrenamiento`.`profesor` (`id`, `alta`, `apellido`, `direccion`, `dni`, `email`, `fecha_nacimiento`, `nombre`, `password`, `password2`, `profesion`, `telefono`, `localidad_id`, `provincia_id`, `foto_perfil`,  `descripcion_actividad`) VALUES ('3', b'1', 'perez', 'kjljk 566', '6677578', 'uiyiuyi@jajha', '2000-10-10', 'jose', '123', '123', 'TECNICO_EN_PREPARACION_FISICA', '54644664', '8', '13', 'a5de58c6-af97-4ebf-9fcb-638629ad8642-file','soy especialista en rascarme el pomo, cobro planes y duermo todo el dia');
INSERT INTO `planes_entrenamiento`.`profesor` (`id`, `alta`, `apellido`, `direccion`, `dni`, `email`, `fecha_nacimiento`, `nombre`, `password`, `password2`, `profesion`, `telefono`, `localidad_id`, `provincia_id`, `foto_perfil`,  `descripcion_actividad`) VALUES ('4', b'0', 'Castano', 'aaaa 566', '622222278', 'ximenacastano821@gmail.com', '2000-07-13', 'Ximena', '12345', '12345', 'TECNICO_EN_PREPARACION_FISICA', '54644664', '4', '13', 'b8ab49ae-50bc-4e61-b0fa-c1b7ae8f5f0f-file', 'me encargo del baile y la zumba en argentina desde 1999');
INSERT INTO `planes_entrenamiento`.`profesor` (`id`, `alta`, `apellido`, `direccion`, `dni`, `email`, `fecha_nacimiento`, `nombre`, `password`, `password2`, `profesion`, `telefono`, `localidad_id`, `provincia_id`, `foto_perfil`,  `descripcion_actividad`) VALUES ('5', b'0', 'Perez', 'bbbb 566', '611111', 'uiyiuyi@jajha', '1995-02-27', 'Juan Cruz', '12345', '12345', 'TECNICO_EN_PREPARACION_FISICA', '54644664', '4', '13', 'b8ab49ae-50bc-4e61-b0fa-c1b7ae8f5f0f-file','soy cordobes, me gusta el vino y la coca, y lo tomo sin soda, porque asi pega mas');

INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('1', 'YOGA');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('2', 'BAILE');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('3', 'SPINNING');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('4', 'ATLETISMO');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('5', 'SOBRECARGA');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('6', 'PILATES');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('7', 'MOUNTAIN_BIKE');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('8', 'CROSSFIT');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('9', 'BAILE');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('10', 'ENTRENAMIENTO_FUNCIONAL');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('11', 'RUNNING');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('12', 'YOGA');
INSERT INTO `planes_entrenamiento`.`actividad_profesor` (`id`, `nombre`) VALUES ('13', 'ATLETISMO');

INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('1', '62d5b634-ae41-40d1-bedd-155ead4ec2ab-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('2', '895fe713-b252-4e51-8992-74788a9077de-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('3', '3594fd34-fabe-49a9-be4f-70e6c39b8dbf-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('4', '23930ae3-e12d-4acf-8a1c-65b86179524e-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('5', '64647cd7-e50f-4211-8345-1a73ee8f0844-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('6', '64647cd7-e50f-4211-8345-1a73ee8f0844-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('7', 'fa8d72ca-73f8-437d-854f-d044d8c2418d-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('8', '23930ae3-e12d-4acf-8a1c-65b86179524e-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('9', 'e6ce0549-c6d2-4301-964d-5f8ed2fd7db7-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('10', 'a06c634a-de71-4b1e-a3ee-ece7554e00a5-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('11', '23930ae3-e12d-4acf-8a1c-65b86179524e-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('12', '23930ae3-e12d-4acf-8a1c-65b86179524e-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('13', 'e6ce0549-c6d2-4301-964d-5f8ed2fd7db7-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('14', '64647cd7-e50f-4211-8345-1a73ee8f0844-file');
INSERT INTO `planes_entrenamiento`.`fotos_profesor` (`id`, `fotos`) VALUES ('15', '23930ae3-e12d-4acf-8a1c-65b86179524e-file');

INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('1', '1');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('1', '2');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('1', '3');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('1', '4');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('2', '5');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('2', '6');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('2', '7');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('3', '8');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('3', '9');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('4', '11');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('4', '12');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('5', '13');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('5', '14');
INSERT INTO `planes_entrenamiento`.`profesor_fotos` (`profesor_foto_id`, `fotos_id`) VALUES ('5', '15');


INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (1,2);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (1,4);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (1,12);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (2,6);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (2,1);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (2,3);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (3,1);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (3,5);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (3,4);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (1,7);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (4,4);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (5,6);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (4,7);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (2,2);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (3,7);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (4,2);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (5,9);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (3,11);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (2,8);
INSERT INTO planes_entrenamiento.profesor_actividades(profesor_id, actividades_id) values (1,11);





