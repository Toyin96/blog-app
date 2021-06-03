SET FOREIGN_KEY_CHECKS = 0;

truncate table blog_post;
truncate  table author;
truncate table comment;
truncate table author_posts;

INSERT INTO blog_post(id, title, content, date_created)
VALUES(41, 'Title post1', 'Post content 1', '2021-06-03 12:31:18.000000'),
    (42, 'Title post2', 'Post content 2', '2021-06-03 12:31:10.000000'),
    (43, 'Title post3', 'Post content 3', '2021-06-03 12:31:8.000000'),
    (44, 'Title post4', 'Post content 4', '2021-06-03 12:31:4.000000');

-- this script clears out the database each time we run our
-- test so that we'll be working a new/clean database each time

SET FOREIGN_KEY_CHECKS = 1;
