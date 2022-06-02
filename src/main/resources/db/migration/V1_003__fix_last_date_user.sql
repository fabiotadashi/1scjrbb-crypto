alter table TB_USERS drop column last_updated_date;
alter table TB_USERS add column last_modified_date timestamp not null;