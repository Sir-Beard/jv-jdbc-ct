#INSERT INTO `dbcarservice`.`manufacturers` (`name`, `country`, `is_deleted`) VALUES ('test', 'testC', '0')
#INSERT INTO `dbcarservice`.`manufacturers` (`name`, `country`, `is_deleted`) VALUES ('test', 'testC', '0')
#DELETE FROM manufacturers WHERE id=4
#INSERT INTO dbcarservice.manufacturers (name, country, is_deleted) VALUES ('test', 'testCountry', 0);


#comment ()
-- comment(double dash + whitespace)
/*comment(could be multiple-line)*/
/*
 examples of queries:
    INSERT INTO users (name, age, email) VALUES ('Steve', 55, 'steve@mail.com');
    SELECT * FROM users;
    SELECT * FROM users WHERE name = 'Steve';
    UPDATE users SET name='Steve', age=55 WHERE id=1;
    DELETE FROM users WHERE id=4;
 */


/*
ТУТ, В ЦЬОМУ ФАЙЛІ init_db.sql, СТВОРЮЄТЬСЯ ТАБЛИЧКА ДЛЯ БД
                            ▼▼▼
*/

create table manufacturers
(
    id         bigint auto_increment
        primary key,
    name       varchar(45) not null,
    country    varchar(45) not null,
    is_deleted tinyint     NOT NULL DEFAULT 0
)
    comment 'List of car makers.';