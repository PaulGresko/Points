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
create table point
(
    x double not null,
    y double not null,
    z  double,
    ID integer AUTO_INCREMENT,
    primary key (ID),
    record_ID integer,
    foreign key (record_ID) references record (ID)
);
