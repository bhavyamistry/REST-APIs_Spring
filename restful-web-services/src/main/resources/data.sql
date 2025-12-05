INSERT INTO user_details(id,birth_date,name)
values(10001, current_date(), 'A');

INSERT INTO user_details(id,birth_date,name)
values(10002, current_date(), 'B');

INSERT INTO user_details(id,birth_date,name)
values(10003, current_date(), 'C');

INSERT INTO post(id,description,user_id)
values(20001, 'I want to lean AWS', 10001);

INSERT INTO post(id,description,user_id)
values(20002, 'I want to lean DevOPs', 10001);

INSERT INTO post(id,description,user_id)
values(20003, 'I want to lean AWS', 10002);

INSERT INTO post(id,description,user_id)
values(20004, 'I want to lean DevOPs', 10002);