CREATE TABLE `tb_user` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `is_send` tinyint DEFAULT '0' COMMENT '0 not send 1  An email has been sent',
                           `is_delete` tinyint DEFAULT '0' COMMENT '0 normal  user  1 deleted user',
                           `password` varchar(255) CHARACTER SET utf32 COLLATE utf32_general_ci DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UNI_NAME` (`name`) USING BTREE,
                           UNIQUE KEY `UNI_EMAIL` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf32;