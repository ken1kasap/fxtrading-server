create table rate {
  transaction_id bigint not null,
  currency_pair varchar(7) not null,
  acquisition_time timestamp not null,
  high decimal not null,
  low  decimal not null,
  ask  decimal not null,
  bid  decimal not null,
  open decimal not null,
  primary key (transaction_id)
}

