create table user (
  uid int(11) not null auto_increment,
  username varchar(32) not null,
  password varchar(32) not null,
  PRIMARY key (uid)
);
insert into user values (1,'admin','123');
insert into user values (2,'demo','123');
create table role(
  rid INT (11) not null auto_increment,
  name varchar(32) not null,
  PRIMARY  key (rid)
);
insert into role values (1,'admin');
insert into role values (2,'customer');
create table permission(
  pid int(11) not null auto_increment,
  name varchar(32) not null,
  url varchar(128),
  PRIMARY key (pid)
);
insert into permission values (1,'add','');
insert into permission values (2,'delete','');
insert into permission values (3,'edit','');
insert into permission values (4,'query','');
###用户角色表###
create table user_role(
  id int(11) not null auto_increment,
  uid int(11) not null,
  rid int (11) not null,
  PRIMARY key (id),
  key ids_uid (uid),
  key ids_rid (rid)
);
insert into user_role values (1,1,1);
insert into user_role values (2,2,2);
##角色权限表##
create table role_permission(
  id int (11) not null auto_increment,
  rid int (11),
  pid int (11),
  PRIMARY  key (id),
  key ids_rid(rid),
  key ids_pid(pid)
);
insert into role_permission values (1,1,1);
insert into role_permission values (2,1,2);
insert into role_permission values (3,1,3);
insert into role_permission values (4,1,4);
insert into role_permission values (5,2,1);
insert into role_permission values (6,2,4);