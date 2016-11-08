create table runs (
  runid integer default null,
  rdate date default null,
  timeofday varchar(4) default null,
  distance decimal(6,2),
  units varchar(5),
  elapsed time,
  effort varchar(50),
  comments varchar(200),
  shoeid integer
);

create table unit_conversion (
  from_u varchar(5),
  to_u varchar(5),
  factor double precision
);

