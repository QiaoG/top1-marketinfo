INSERT INTO enterprise VALUES (null,0,'企业1','1010123');
INSERT INTO enterprise VALUES (null,1,'企业2','1010122');
INSERT INTO enterprise VALUES (null,2,'企业3','1010124');
INSERT INTO enterprise VALUES (null,3,'企业4','1010125');
INSERT INTO enterprise VALUES (null,4,'企业5','1010126');

INSERT INTO `user`(`id`, `mobile`, `nickname`, `role`, `wx_number`, `enterprise_id`, `create_date`) VALUES (1, '13501391707', 'gq', 0, 'gqiao', NULL, NULL);
INSERT INTO `user`(`id`, `mobile`, `nickname`, `role`, `wx_number`, `enterprise_id`, `create_date`) VALUES (2, '13501391708', 'hgj', 1, 'hnj', 1, '2018-01-08 14:04:43');
INSERT INTO `user`(`id`, `mobile`, `nickname`, `role`, `wx_number`, `enterprise_id`, `create_date`) VALUES (3, '13501391709', 'yxp', 2, 'yxp', 2, '2018-01-03 14:04:47');
INSERT INTO `user`(`id`, `mobile`, `nickname`, `role`, `wx_number`, `enterprise_id`, `create_date`) VALUES (4, '13462487653', 'nick_1', 2, 'wxn_1', 4, '2018-01-09 16:04:43');


INSERT INTO news(title,content,news_source,author_id,publisher_id,`create_date`) VALUES ('new-1','新闻-1','测试企业1',3,2,'2017-10-09 10:00:09');
INSERT INTO news(title,content,news_source,author_id,publisher_id,`create_date`) VALUES ('new-2','新闻-2','测试企业2',3,2,'2017-10-09 16:00:09');

INSERT INTO demand(content,type,publisher_id,publish_date,invalid_date) VALUES ('需求-1',1,2,'2017-10-10 10:00:09','2017-11-09 10:00:09');
INSERT INTO demand(content,type,publisher_id,publish_date,invalid_date) VALUES ('需求-2',2,2,'2018-01-02 10:00:09','2018-01-09 10:00:09');

INSERT INTO discuss(`discuss_source`,source_type,create_date,author_id,publisher_id,content) VALUES (1,1,'2018-01-02 10:00:09',3,2,'我的评论1');
INSERT INTO discuss(`discuss_source`,source_type,create_date,author_id,publisher_id,content) VALUES (2,0,'2018-01-03 10:10:09',3,2,'我的评论2');