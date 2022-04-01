show databases;

use mybatis;

show tables;

SET foreign_key_checks = 0;

select * from user;

delete from user where id in ('1','2','10','11','12');

commit;

drop table user;

create table mybatis.user
(
    id int auto_increment primary key,
    username varchar(50) null
);
