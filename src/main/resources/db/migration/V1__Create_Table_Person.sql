CREATE TABLE `person`
(
   `id` bigint (20) NOT NULL AUTO_INCREMENT,
   `first_name` varchar (80) NOT NULL,
   `last_name` varchar (80) NOT NULL,
   `address` varchar (90) NOT NULL,
   `gender` varchar (10) NOT NULL,
   PRIMARY KEY (`id`)
);