create table TB_USERS(
    id int auto_increment primary key,
    name varchar(100),
    password varchar(100),
    created_date timestamp not null,
    last_updated_date timestamp not null
)