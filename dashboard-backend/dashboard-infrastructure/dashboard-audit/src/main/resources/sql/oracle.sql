
create table AUDIT_EVENT (
    AUDIT_EVENT_ID         number(19)    not null AUTO_INCREMENT,
    CREATED_AT date          not null,
    ENTITY     varchar2(128) not null,
    ENTITY_ID  number(19)    not null,
    ACTION     varchar2(8)   not null,
    USER_ID    number(19)    default null,
    USER_NAME  varchar2(128) default null
);

alter table AUDIT_EVENT
    add (
    constraint AUDIT_EVENT_PK primary key (AUDIT_EVENT_ID)
    );

create sequence AUDIT_EVENT_SEQ start with 1;

create or replace trigger AUDIT_EVENT_BIR
before insert on AUDIT_EVENT
for each row

    begin
        select AUDIT_EVENT_SEQ.NEXTVAL
        into :NEW.ID
        from DUAL;
    end;
/

create table AUDIT_CHANGE (
    AUDIT_CHANGE_ID             number(19)    not null AUTO_INCREMENT,
    EVENT_ID       number(19)    not null,
    FIELD          varchar2(128) not null,
    OLD_VALUE      varchar2(4096) default null,
    NEW_VALUE      varchar2(4096) default null,
    SUB_EVENT_ID   number(19)     default null,
    SUB_EVENT_NAME varchar2(4096) default null,
    constraint AUDIT_CHANGE_PK primary key (AUDIT_CHANGE_ID)
);

alter table AUDIT_CHANGE
    add (
    constraint AUDIT_CHANGE_PK primary key (AUDIT_CHANGE_ID)
    );

create sequence AUDIT_CHANGE_SEQ start with 1;

create or replace trigger AUDIT_CHANGE_BIR
before insert on AUDIT_CHANGE
for each row

    begin
        select AUDIT_CHANGE_SEQ.NEXTVAL
        into :NEW.ID
        from DUAL;
    end;
/
