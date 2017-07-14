
create table AUDIT_EVENT (
    AUDIT_EVENT_ID         serial,
    CREATED_AT datetime     not null,
    ENTITY     varchar(128) not null,
    ENTITY_ID  bigint(20)   not null,
    ACTION     varchar(8)   not null,
    USER_ID    bigint(20)   default null,
    USER_NAME  varchar(128) default null,
    primary key (AUDIT_EVENT_ID)
);

create table AUDIT_CHANGE (
    AUDIT_CHANGE_ID             serial,
    EVENT_ID       bigint(20)   not null,
    FIELD          varchar(128) not null,
    OLD_VALUE      varchar(4096) default null,
    NEW_VALUE      varchar(4096) default null,
    SUB_EVENT_ID   bigint(20)    default null,
    SUB_EVENT_NAME varchar(4096) default null,
    primary key (AUDIT_CHANGE_ID)
);