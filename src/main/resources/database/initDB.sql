CREATE TABLE IF NOT EXISTS contact_attribute
(
    id           UUID,
    created_time TIMESTAMP with time zone,
    name         VARCHAR(50),
    type         VARCHAR(255),
    value        VARCHAR(450),
    contact      UUID,
    packageId    VARCHAR(50),
    PRIMARY KEY (id)
);





