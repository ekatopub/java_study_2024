/*P367*/


/*DB作成用SQL ここは管理画面から手動で入力
create database db_company;
create table db_company.employees (id int, name varchar(10), department_id int);
create table db_company.departments (id int auto_increment, name varchar(10), primary key (id));
/*
アクセスはrootユーザー　パスワードはデフォルトの空文字を想定 




↓　↓　↓　NOUSE　↓　↓　↓
MariaDB [tablea]> CREATE TABLE area
(
area_id int(10) NOT NULL AUTO_INCREMENT,
area_name VARCHAR(20) NOT NULL,
PRIMARY KEY (area_id)
);
INSERT INTO area(area_name) VALUES("tokyo");
SELECT * FROM area;
INSERT INTO user_area VALUES(1,1);
SELECT user.login_id,
user_area.area_name
FROM user
INNER JOIN user_area
ON user.user_id = user_area.user_id
;
*/

