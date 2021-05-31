SET FOREIGN_KEY_CHECKS = 0;

truncate table blog_post;
truncate  table author;
truncate table comment;
truncate table author_posts;

INSERT INTO blog_post(id, title, content)
VALUES(41, 'Title post1', 'Post content 1'),
    (42, 'Title post2', 'Post content 2'),
    (43, 'Title post3', 'Post content 3'),
    (44, 'Title post4', 'Post content 4'),
    (45, 'Title post5', 'Post content 5');

-- this script clears out the database each time we run our
-- test so that we'll be working a new/clean database each time

SET FOREIGN_KEY_CHECKS = 1;
