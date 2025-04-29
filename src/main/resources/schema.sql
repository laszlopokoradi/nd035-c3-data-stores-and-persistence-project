CREATE TABLE IF NOT EXISTS customers
(
    id           binary(16)   NOT NULL,
    name         varchar(255) NOT NULL,
    notes        varchar(255),
    phone_number varchar(255),
    primary key (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS employees
(
    id             binary(16)   NOT NULL,
    name           varchar(255) NOT NULL,
    days_available varbinary(255),
    skills         varbinary(255),
    primary key (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS pets
(
    birth_date  date,
    customer_id binary(16),
    id          binary(16)                                                NOT NULL,
    name        varchar(255)                                              NOT NULL,
    notes       varchar(255),
    type        enum ('BIRD','CAT','DOG','FISH','LIZARD','OTHER','SNAKE') NOT NULL,
    primary key (id)
) engine = InnoDB;