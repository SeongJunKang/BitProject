create table PROJECTS (
    PNO int primary key auto_increment,
    TITLE   varchar(200) not null,
    SDT     datetime not null,
    EDT     datetime not null,
    DESCT   text,
    STAT    int default 0
);

create table BOARDS (
    BNO      int primary key auto_increment,
    TITLE    varchar(200) not null,
    CONTS    text not null,
    VWCNT    int default 0,
    PWD      varchar(10),
    CDT      datetime
);

create table MEMBERS (
    MNO      int primary key auto_increment,
    MNAME    varchar(50) not null,
    EMAIL    varchar(50) not null,
    PWD      varchar(12) not null,
    TEL      varchar(30)
);

create table TASKS (
    TNO      int primary key auto_increment,
    TITLE    varchar(50) not null,
    CONTS    text not null,
    WKE      varchar(20) not null,
    SDT     datetime not null,
    EDT     datetime not null,
    STAT    int default 0
);

insert into MEMBERS(MNAME,EMAIL,PWD)
values('홍길동','hong@test.com','1111');
insert into MEMBERS(MNAME,EMAIL,PWD)
values('임꺽정','leem@test.com','2222');
insert into MEMBERS(MNAME,EMAIL,PWD)
values('이순신','lee@test.com','3333');

select * from MEMBERS;




