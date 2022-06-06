create table TB_CRYPTOS(
  id int auto_increment primary key,
  name varchar(200),
  acronym varchar(30),
  usd_value double,
  created_date timestamp not null,
  last_updated_date timestamp
);
