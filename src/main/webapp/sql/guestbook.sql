create table guestbook(
	seq integer auto_increment primary key,
	user_id varchar(10) not null,
	title varchar(50) not null,
	content varchar(200) not null,
	reg_date datetime not null default now(),
	read_count integer not null default 0
);

select * from guestbook where seq = 3;
select * from guestbook g left join reply r on g.seq = r.seq where g.seq=1;
create table reply(
	reply_seq integer auto_increment primary key,
	content varchar(100) not null,
	reg_date datetime not null default now(),
	seq integer not null,
	foreign key (seq) references guestbook(seq)
);

select * from reply;
delete from reply;