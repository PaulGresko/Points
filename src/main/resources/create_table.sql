create table calculation
(
    t1     TIME,
    t2     TIME,
    method VARCHAR(50),
    ID     integer AUTO_INCREMENT,
    primary key (ID)
);

create table record
(
    S  DOUBLE,
    ID integer AUTO_INCREMENT,
    primary key (ID),
    calculation_ID integer,
    foreign key (calculation_ID) references calculation (ID)
);
create table pointA
(
    x double not null,
    y double not null,
    z  double,
    record_ID integer,
    primary key (record_ID),
    foreign key (record_ID) references record (ID)
);
create table pointB
(
    x double not null,
    y double not null,
    z  double,
    record_ID integer,
    primary key (record_ID),
    foreign key (record_ID) references record (ID)
);
