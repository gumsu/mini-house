CREATE TABLE POSTS(
                      id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      title varchar(255) not null,
                      content varchar(1000) not null,
                      writer varchar(10) not null,
                      createdDate datetime not null,
                      modifiedDate datetime null
)