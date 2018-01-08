INSERT INTO news(title,content,news_source,author_id,publisher_id,`create_date`) VALUES ('new-1','新闻-1','测试企业1',3,2,'2017-10-09 10:00:09');
INSERT INTO news(title,content,news_source,author_id,publisher_id,`create_date`) VALUES ('new-2','新闻-2','测试企业2',3,2,'2017-10-09 16:00:09');

INSERT INTO demand(content,type,publisher_id,publish_date,invalid_date) VALUES ('需求-1',1,2,'2017-10-10 10:00:09','2017-11-09 10:00:09');
INSERT INTO demand(content,type,publisher_id,publish_date,invalid_date) VALUES ('需求-2',2,2,'2018-01-02 10:00:09','2018-01-09 10:00:09');

INSERT INTO discuss(`discuss_source`,source_type,create_date,author_id,publisher_id) VALUES (1,1,'2018-01-02 10:00:09',3,2);
INSERT INTO discuss(`discuss_source`,source_type,create_date,author_id,publisher_id) VALUES (2,0,'2018-01-03 10:10:09',3,2);