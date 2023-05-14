select * from user_table

select * from subcategory
select * from category
delete from subcategory
delete from category
drop table category
drop table subcategory
select * from user_table
select s.subcname from subcategory s,category c where c.category_id=s.categoryid and c.userid=10
select s.subcname from subcategory s,category c where c.category_id=s.categoryid and c.userid=9

 SELECT s.subcname FROM user_table u 
             JOIN category c ON u.id = c.userid 
             JOIN subcategory s ON c.category_id = s.categoryid WHERE u.id = 3
select * from vendor
drop table vendor