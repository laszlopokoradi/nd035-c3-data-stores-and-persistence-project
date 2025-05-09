CREATE TABLE IF NOT EXISTS customers
(
    id           BINARY(16)   NOT NULL,
    name         VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NULL,
    notes        VARCHAR(255) NULL,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employee_days_available
(
    employee_id    BINARY(16) NOT NULL,
    days_available SMALLINT   NULL
);

CREATE TABLE IF NOT EXISTS employee_skills
(
    employee_id BINARY(16)   NOT NULL,
    skills      VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS employees
(
    id   BINARY(16)   NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (id)
);

ALTER TABLE employee_days_available
    ADD CONSTRAINT fk_employee_days_available_on_employee FOREIGN KEY (employee_id) REFERENCES employees (id);

ALTER TABLE employee_skills
    ADD CONSTRAINT fk_employee_skills_on_employee FOREIGN KEY (employee_id) REFERENCES employees (id);

CREATE TABLE IF NOT EXISTS pets
(
    id          BINARY(16)   NOT NULL,
    type        VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    customer_id BINARY(16)   NULL,
    birth_date  date         NULL,
    notes       VARCHAR(255) NULL,
    CONSTRAINT pk_pets PRIMARY KEY (id)
);

ALTER TABLE  pets
    ADD CONSTRAINT FK_PETS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

CREATE TABLE IF NOT EXISTS schedule_activities
(
    schedule_id BIGINT       NOT NULL,
    activities  VARCHAR(255) NULL
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

ALTER TABLE schedule_activities
    ADD CONSTRAINT fk_schedule_activities_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id);

ALTER TABLE schedule_employees
    ADD CONSTRAINT fk_schemp_on_employee FOREIGN KEY (employee_id) REFERENCES employees (id);

ALTER TABLE schedule_employees
    ADD CONSTRAINT fk_schemp_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id);

ALTER TABLE schedule_pets
    ADD CONSTRAINT fk_schpet_on_pet FOREIGN KEY (pet_id) REFERENCES pets (id);

ALTER TABLE schedule_pets
    ADD CONSTRAINT fk_schpet_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id);