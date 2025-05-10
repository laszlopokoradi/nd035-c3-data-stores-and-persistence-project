CREATE TABLE IF NOT EXISTS activities
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_activities PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customers
(
    id           BINARY(16)   NOT NULL,
    name         VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NULL,
    notes        VARCHAR(255) NULL,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employee_activities
(
    activity_id BIGINT     NOT NULL,
    employee_id BINARY(16) NOT NULL,
    CONSTRAINT pk_employee_activities PRIMARY KEY (activity_id, employee_id)
);

CREATE TABLE IF NOT EXISTS employee_days_available
(
    employee_id    BINARY(16) NOT NULL,
    days_available SMALLINT   NULL
);

CREATE TABLE IF NOT EXISTS employees
(
    id   BINARY(16)   NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS schedule_activities
(
    activity_id BIGINT     NOT NULL,
    schedule_id BINARY(16) NOT NULL,
    CONSTRAINT pk_schedule_activities PRIMARY KEY (activity_id, schedule_id)
);

CREATE TABLE IF NOT EXISTS pets
(
    id          BINARY(16)   NOT NULL,
    pet_type_id BIGINT       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    customer_id BINARY(16)   NULL,
    birth_date  date         NULL,
    notes       VARCHAR(255) NULL,
    CONSTRAINT pk_pets PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pet_type_activities
(
    activity_id BIGINT NOT NULL,
    pet_type_id BIGINT NOT NULL,
    CONSTRAINT pk_pet_type_activities PRIMARY KEY (activity_id, pet_type_id)
);

CREATE TABLE IF NOT EXISTS pet_types
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_pet_types PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS schedule_employees
(
    employee_id BINARY(16) NOT NULL,
    schedule_id BIGINT     NOT NULL
);

CREATE TABLE IF NOT EXISTS schedule_pets
(
    pet_id      BINARY(16) NOT NULL,
    schedule_id BIGINT     NOT NULL
);

CREATE TABLE IF NOT EXISTS schedules
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    date date                  NULL,
    CONSTRAINT pk_schedules PRIMARY KEY (id)
);

ALTER TABLE activities
    ADD CONSTRAINT uc_activities_name UNIQUE (name);

ALTER TABLE employee_activities
    ADD CONSTRAINT fk_empact_on_activity FOREIGN KEY (activity_id) REFERENCES activities (id);

ALTER TABLE employee_activities
    ADD CONSTRAINT fk_empact_on_employee FOREIGN KEY (employee_id) REFERENCES employees (id);

ALTER TABLE employee_days_available
    ADD CONSTRAINT fk_employee_days_available_on_employee FOREIGN KEY (employee_id) REFERENCES employees (id);

ALTER TABLE schedule_activities
    ADD CONSTRAINT fk_schact_on_activity FOREIGN KEY (activity_id) REFERENCES activities (id);

ALTER TABLE schedule_activities
    ADD CONSTRAINT fk_schact_on_employee FOREIGN KEY (schedule_id) REFERENCES employees (id);

ALTER TABLE pets
    ADD CONSTRAINT FK_PETS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE pets
    ADD CONSTRAINT FK_PETS_ON_PET_TYPE FOREIGN KEY (pet_type_id) REFERENCES pet_types (id);

ALTER TABLE pet_types
    ADD CONSTRAINT uc_pet_types_name UNIQUE (name);

ALTER TABLE pet_type_activities
    ADD CONSTRAINT fk_pettypact_on_activity FOREIGN KEY (activity_id) REFERENCES activities (id);

ALTER TABLE pet_type_activities
    ADD CONSTRAINT fk_pettypact_on_pet_type FOREIGN KEY (pet_type_id) REFERENCES pet_types (id);

ALTER TABLE schedule_employees
    ADD CONSTRAINT fk_schemp_on_employee FOREIGN KEY (employee_id) REFERENCES employees (id);

ALTER TABLE schedule_employees
    ADD CONSTRAINT fk_schemp_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id);

ALTER TABLE schedule_pets
    ADD CONSTRAINT fk_schpet_on_pet FOREIGN KEY (pet_id) REFERENCES pets (id);

ALTER TABLE schedule_pets
    ADD CONSTRAINT fk_schpet_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id);
