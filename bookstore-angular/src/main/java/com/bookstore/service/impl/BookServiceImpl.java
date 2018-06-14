package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;

/* This is the implementation of book service , it implements methods like findall(), findone(),save(), search(),remove one()*/
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	/* This method provides the whole list of books that are present in the book repository*/
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		
		List<Book> activeBookList = new ArrayList<>();
		
		for (Book book : bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}
	
	/* This method provides the save book in to the book repository*/
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	/* This method provides the find a single book with id in the book repository*/
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}
	/* This method provides the blurry search where you provide some string to search the book repository*/
	public List<Book> blurrySearch(String keyword) {
		List<Book> bookList = bookRepository.findByTitleContaining(keyword);
		
		List<Book> activeBookList = new ArrayList<>();
		
		for (Book book : bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}
	/* This method provides the delete book from the book repository*/
	public void removeOne(Long id) {
		bookRepository.delete(id);
	}
}
