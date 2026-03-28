--liquibase formatted sql

--changeset UserN:2 splitStatements:false endDelimiter:@@
--comment first
DO $$
BEGIN
    -- Проверяем существование ограничения в системном каталоге pg_constraint
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'useremail_unique'   -- Имя ограничения
          AND conrelid = 'users'::regclass    -- Имя таблицы
    ) THEN
        -- Если ограничение не найдено, добавляем его
        EXECUTE 'ALTER TABLE users ADD CONSTRAINT useremail_unique UNIQUE (email)';
    END IF;
END;
$$;
@@

