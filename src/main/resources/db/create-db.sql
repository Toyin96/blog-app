create database if not exists blogdb;

create user if not exists 'toyin96'@'localhost' ientified by 'm18job,,';
grant all privileges on blogdb.* to 'toyin96'@'localhost';
flush privileges ;