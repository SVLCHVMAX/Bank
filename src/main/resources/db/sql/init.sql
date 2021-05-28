DROP SCHEMA PUBLIC IF EXISTS CASCADE;

CREATE TABLE client
(
    id          UUID PRIMARY KEY    NOT NULL,
    first_name  VARCHAR(30)         NOT NULL,
    middle_name VARCHAR(30)         NOT NULL,
    last_name   VARCHAR(30)         NOT NULL,
    tel_number  BIGINT UNIQUE       NOT NULL,
    email       VARCHAR(255) UNIQUE NOT NULL,
    passport    BIGINT UNIQUE       NOT NULL
);

CREATE TABLE credit
(
    id            UUID PRIMARY KEY    NOT NULL,
    name          VARCHAR(100) UNIQUE NOT NULL,
    credit_limit  BIGINT              NOT NULL,
    interest_rate FLOAT               NOT NULL
);

CREATE TABLE credit_offer
(
    id            UUID PRIMARY KEY NOT NULL,
    client_id     UUID             NOT NULL,
    credit_id     UUID             NOT NULL,
    credit_amount BIGINT           NOT NULL,
    credit_length INTEGER          NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (credit_id) REFERENCES credit (id)
);

CREATE TABLE bank
(
    id              UUID PRIMARY KEY NOT NULL,
    client_id       UUID             NOT NULL,
    credit_id       UUID             NOT NULL,
    credit_offer_id UUID             NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (credit_id) REFERENCES credit (id),
    FOREIGN KEY (credit_offer_id) REFERENCES credit_offer (id)
);

CREATE TABLE payment_schedule
(
    id                        UUID PRIMARY KEY NOT NULL,
    credit_offer_id           UUID             NOT NULL,
    date                      DATE             NOT NULL,
    amount_payment            FLOAT            NOT NULL,
    amount_repayment_body     FLOAT            NOT NULL,
    amount_repayment_interest FLOAT            NOT NULL,
    FOREIGN KEY (credit_offer_id) REFERENCES credit_offer (id)
);

