create schema book;
use book;
create table books (
IBNS_code int primary key,
book_title varchar(255),
author varchar(255),
genre varchar(255),
selling_price decimal(10, 2),
publication_year int,
publisher varchar(255),
page_number int
);