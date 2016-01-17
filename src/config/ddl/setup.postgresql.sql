create table rate (
  transaction_id bigint not null,
  currency_pair varchar(7) not null,
  acquisition_time timestamp not null,
  high_rate decimal not null,
  low_rate  decimal not null,
  ask_rate  decimal not null,
  bid_rate  decimal not null,
  open_rate decimal not null,
  primary key (transaction_id)
);
