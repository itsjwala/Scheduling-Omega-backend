 insert into employee(email,name,phone_number,wissen_id,active) values('jigar.wala@wissen.com','jigar wala','8888888888','WT250',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('sagar.ailani@wissen.com','sagar ailani','8888888888','WT251',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('prithvi.morya@wissen.com','prithvi morya','8888888888','WT252',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('anirudh.balakka@wissen.com','anirudh balakka','8888888888','WT253',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('rishi.ghai@wissen.com','rishi ghai','8888888888','WT254',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('suhaib.khan@wissen.com','suhaib khan','8888888888','WT255',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('dheeraj.bhutani@wissen.com','dheeraj bhutani','8888888888','WT256',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('nilesh.dahiphale@wissen.com','nilesh dahiphale','8888888888','WT257',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('nilesh.dahiphale@wissen.com','nilesh dahiphale','8888888888','WT257',1);
 insert into employee(email,name,phone_number,wissen_id,active) values('rushabh.shah@wissen.com','rushabh shah','8888888888','WT258',1);



insert into interviewer(id) values(1);
insert into interviewer(id) values(2);
insert into interviewer(id) values(3);
insert into interviewer(id) values(4);


insert into level(level) values('R1');
insert into level(level) values('R2');


insert into technology(technology) values('Angular');
insert into technology(technology) values('Java');

insert into interviewer_levels(interviewer_id,levels_id) values(1,1);
insert into interviewer_levels(interviewer_id,levels_id) values(2,1);
insert into interviewer_levels(interviewer_id,levels_id) values(3,1);
insert into interviewer_levels(interviewer_id,levels_id) values(4,1);


insert into interviewer_technologies(interviewer_id,technologies_id) values(1,1);
insert into interviewer_technologies(interviewer_id,technologies_id) values(1,2);
insert into interviewer_technologies(interviewer_id,technologies_id) values(2,1);
insert into interviewer_technologies(interviewer_id,technologies_id) values(2,2);
insert into interviewer_technologies(interviewer_id,technologies_id) values(3,1);
insert into interviewer_technologies(interviewer_id,technologies_id) values(3,2);
insert into interviewer_technologies(interviewer_id,technologies_id) values(4,1);
insert into interviewer_technologies(interviewer_id,technologies_id) values(4,2);


insert into candidate(name,phone_num) values('akshay kumar','828258288522');
insert into candidate(name,phone_num) values('salman khan','54848484884');


insert into available_slot(from_timestamp,to_timestamp,interviewer_id) values('2019-07-16 05:00:00','2019-07-16 08:00:00',1);
insert into available_slot(from_timestamp,to_timestamp,interviewer_id) values('2019-07-17 05:00:00','2019-07-17 08:00:00',1);
insert into available_slot(from_timestamp,to_timestamp,interviewer_id) values('2019-07-18 05:00:00','2019-07-18 08:00:00',1);

insert into available_slot(from_timestamp,to_timestamp,interviewer_id) values('2019-07-16 02:00:00','2019-07-16 03:00:00',2);
insert into available_slot(from_timestamp,to_timestamp,interviewer_id) values('2019-07-17 02:00:00','2019-07-17 03:00:00',2);
insert into available_slot(from_timestamp,to_timestamp,interviewer_id) values('2019-07-18 02:00:00','2019-07-18 03:00:00',2);
insert into available_slot(from_timestamp,to_timestamp,interviewer_id,active) values('2019-07-18 08:00:00','2019-07-18 09:00:00',1,0);





insert into schedule_slot(interview_description,candidate_id,hr_id,interviewer_id,slot_id,level_id,technology_id)
values('room 314',1,5,1,1,1,1);

insert into schedule_slot(interview_description,candidate_id,hr_id,interviewer_id,slot_id,level_id,technology_id)
values('room 314',1,5,1,2,1,2);



