drop table if exists User;
drop table if exists Product;

create table User (
    user_id varchar(5) primary key, 
    name varchar(16), 
    age integer, 
    email varchar(16), 
    password varchar(16)
);

create table Product (
    product_id varchar(5) primary key,
	name varchar(16),
	price float,
	expiration_date date
);

create table user_product (
    user_id varchar(5),
    product_id varchar(5),
    constraint user_product_pk primary key (user_id, product_id)
);

alter table user_product add constraint user_product_u_fk foreign key (user_id) references User(user_id) on delete cascade;
alter table user_product add constraint user_product_p_fk foreign key (product_id) references Product(product_id) on delete cascade;
