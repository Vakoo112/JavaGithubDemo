create table team (id bigint not null auto_increment,starttime date, endtime date, maxstudentsenrolled integer,quality varchar(255),course_id bigint, primary key (id) );
create table student(id bigint not null auto_increment, firstname varchar(255), lastname varchar(255),idnumber varchar(255), adress varchar(255), contact_id bigint, primary key (id)  );
create table course (id bigint not null auto_increment,description varchar(255), name varchar(255), primary key (id) );
create table contact (id bigint not null auto_increment,contacttype varchar(255), contactvalue varchar(255), student_id bigint,  primary key(id))  ;
create table team_student(student_id bigint  not null, team_id bigint  not null, primary key (student_id,team_id ) )  ;
alter table team add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (course_id) references course(id);
alter table contact add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (student_id) references student(id);
alter table team_student add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (team_id) references team(id);
alter table team_student add constraint FK6iv5l89qmitedn5m2a71kta2t foreign key (student_id) references student(id);