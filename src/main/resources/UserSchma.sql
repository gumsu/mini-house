CREATE TABLE `users`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(100) NOT NULL,
    `nickname`    VARCHAR(100) NOT NULL,
    `email`       VARCHAR(100) NOT NULL,
    `password`    VARCHAR(255) NOT NULL,
    `phone`       VARCHAR(100) NULL, -- 임시
    `created_at`  DATETIME     NOT NULL,
    `modified_at` DATETIME     NULL,
    `role_id`     INT UNSIGNED NULL -- 임시
);
