package com.bookstore.service;

import java.util.List;
/*This is a interface that provides abstract methods for book service like findAll(),findOne(),save(),blurrySearch(),removeOne() which are implemented by BookServiceImpl*/

import com.bookstore.domain.Book;
//interface which defines methods like finding all books ,saving books and search option 
public interface BookService {
	
	List<Book> findAll();
	
	Book findOne(Long id);
	
	Book save(Book book);
	
	List<Book> blurrySearch(String title);
	
	void removeOne(Long id);
}
