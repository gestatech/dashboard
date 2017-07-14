
create table AUDIT_EVENT (
    AUDIT_EVENT_ID         serial primary key,
    CREATED_AT DATETIME               not null,
    ENTITY     character varying(128) not null,
    ENTITY_ID  bigint (20) not null,
    ACTION     character varying(8)   not null,
    USER_ID    bigint (20) default null,
    USER_NAME  character varying(128) default null
);

create table AUDIT_CHANGE (
    AUDIT_EVENT_ID             serial primary key,
    EVENT_ID       bigint (20) not null,
    FIELD          character varying(128) not null,
    OLD_VALUE      character varying(4096) default null,
    NEW_VALUE      character varying(4096) default null,
    SUB_EVENT_ID   bigint (20) default null,
    SUB_EVENT_NAME character varying(4096) default null
);