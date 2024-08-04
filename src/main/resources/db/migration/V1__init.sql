CREATE TABLE `recruit`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `position`     VARCHAR(50)  NOT NULL,
    `stack`        VARCHAR(50)  NOT NULL,
    `content`      VARCHAR(255) NOT NULL,
    `compensation` BIGINT       NOT NULL,
    `company_id`   BIGINT UNSIGNED NOT NULL,
    `created_at`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `apply_history`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `member_id`    BIGINT    NOT NULL,
    `recruit_id` BIGINT    NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `company`
(
    `id`     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50) NOT NULL,
    `nation` VARCHAR(50) NOT NULL,
    `region` VARCHAR(50) NOT NULL
);

CREATE TABLE `member`
(
    `id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL
);

ALTER TABLE
    `apply_history`
    ADD CONSTRAINT `apply_history_member_id_foreign` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);
ALTER TABLE
    `apply_history`
    ADD CONSTRAINT `apply_history_recruit_id_foreign` FOREIGN KEY (`recruit_id`) REFERENCES `recruit` (`id`);
ALTER TABLE
    `recruit`
    ADD CONSTRAINT `recruit_company_id_foreign` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);