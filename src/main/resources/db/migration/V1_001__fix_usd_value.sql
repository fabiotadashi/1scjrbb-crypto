alter table TB_CRYPTOS drop column usd_value;
alter table TB_CRYPTOS add column usd_value decimal(19,2);