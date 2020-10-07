create table project
(
    id          int auto_increment
        primary key,
    name        varchar(100) not null,
    description text         not null
);

create table task
(
    id           int auto_increment
        primary key,
    title        varchar(100)                                                  not null,
    description  text                                                          null,
    status       enum ('STEADY', 'IN_PROGRESS', 'COMPLETED') default 'STEADY'  not null,
    priority     enum ('CRITICAL', 'URGENT', 'REGULAR')      default 'REGULAR' null,
    started_at   datetime                                                      null,
    end_at       datetime                                                      null,
    story_points int                                                           not null,
    user_id      int                                                           null,
    project_id   int                                                           not null
);

create index task_project_id_fk
    on task (project_id);

create index tasks_users__fk
    on task (user_id);

create table user
(
    id       int auto_increment
        primary key,
    name     varchar(100) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table user_detail
(
    id           int          not null,
    user_id      int          not null,
    phone_number varchar(100) not null,
    country      varchar(100) not null,
    state        varchar(100) not null,
    zip_code     varchar(20)  not null,
    address      varchar(200) not null,
    constraint user_details_id_uindex
        unique (id)
);

create index user_details_users_id_fk
    on user_detail (user_id);

alter table user_detail
    add primary key (id);

create table user_project
(
    user_id    int not null,
    project_id int null
);