create database game_store;

use game_store;

create table users(
	id          	int auto_increment primary key not null,
    name            varchar(150),
    email           varchar(60) unique not null,
    password        varchar(150),
    enabled 		tinyint,
    updated_at      timestamp,
    created_at      timestamp
);

create table authorities(
	id  int auto_increment primary key not null,
	user_id int,
	authority varchar(45) not null,
	created_at      timestamp,
	foreign key (user_id) references users(id) on delete cascade
);

create table games(
	id    		int auto_increment primary key not null,
    title 		varchar(60),
    type        varchar(60),
    image 		text,
    price 		float,
    updated_at	timestamp,
    created_at 	timestamp
);