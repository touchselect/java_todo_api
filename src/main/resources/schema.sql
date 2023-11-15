create table todos(
    id bigint not null primary key auto_increment,
    title varchar(256) not null,
    status varchar(256) not null,
    details varchar(256) not null
);