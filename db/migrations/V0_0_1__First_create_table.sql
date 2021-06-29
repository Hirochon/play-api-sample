create table `todo` (
    `id` int(11) NOT NULL AUTO_INCREMENT unique,
    `todo` varchar(256),
    `status` int(11),
    PRIMARY KEY (`id`)
);