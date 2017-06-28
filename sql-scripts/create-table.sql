
/*******************************************************************/

create table TRANSLATION
(
    TRANS_ID int(11) primary key not null,
    TRANS_CODE varchar(10),
    TRANS_LONG_CODE varchar(30)
);
create unique index TRANS_ID on TRANSLATION (TRANS_ID);

/*******************************************************************/

create table TRANSLATION_LNG
(
    TRANS_REF int(11) not null,
    TRANS_ID int(11) not null,
    TRANS_DESC varchar(40),
    constraint `PRIMARY` primary key (TRANS_REF, TRANS_ID)
);
create unique index TRANS_REF on TRANSLATION_LNG (TRANS_REF);

/*******************************************************************/