create table runs (
  runid int(11) default null,
  rdate date default null,
  timeofday varchar(4) default null,
  distance decimal(6,2),
  units varchar(5),
  elapsed time,
  effort varchar(50),
  'comment' varchar(200),
  shoeid int(11)
);

create table unit_conversion (
  from_u varchar(5),
  to_u varchar(5),
  factor double
);

