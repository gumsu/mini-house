CREATE TABLE `posts`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title`       VARCHAR(100)  NOT NULL,
    `content`     VARCHAR(1000) NOT NULL,
    `created_at`  DATETIME      NOT NULL,
    `modified_at` DATETIME NULL,
    `views`       BIGINT UNSIGNED NULL,
    `likes`        INT UNSIGNED NULL,
    `user_id`     BIGINT UNSIGNED NULL -- 임시
        FOREIGN KEY (user_id)
        REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
);
