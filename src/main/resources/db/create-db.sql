create database if not exists blogdb;

create user if not exists 'magna'@'localhost' ientified by 'm18job,,';
grant all privileges on blogdb.* to 'magna'@'localhost';
flush privileges ;