CREATE TABLE MEMBERS(
                      id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      name varchar(255) not null,
                      email varchar(1000) not null,
                      password varchar(10) not null,
                      createdAt datetime not null
)