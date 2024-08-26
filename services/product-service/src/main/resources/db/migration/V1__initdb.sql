CREATE TABLE IF NOT EXISTS category
(
    id integer not null primary key,
    description varchar(200),
    name varchar(40)
);

CREATE TABLE IF NOT EXISTS product
(
    id integer not null primary key,
    description varchar(200),
    name varchar(40),
    quantity double precision not null,
    price numeric(38,2),
    category_id integer constraint category_fkey references category
);

CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;
