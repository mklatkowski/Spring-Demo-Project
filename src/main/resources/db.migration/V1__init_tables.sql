drop table if exists words;
create table words(
                      id int primary key auto_increment,
                      foreignMeaning varchar(255) not null,
                      localMeaning varchar(255) not null,
                      done bit,
                      groupId int
);

drop table if exists categories;
create table categories(
                      id int primary key auto_increment,
                      categoryName varchar(255) not null,
                      done bit,
                      ratio float not null
);

alter table words add foreign key (groupId) references categories (id);