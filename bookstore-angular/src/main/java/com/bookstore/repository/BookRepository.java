package com.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.Book;

//Interface to enable to create, read, update and delete details related to Books
public interface BookRepository extends CrudRepository<Book, Long>{
	List<Book> findByTitleContaining(String keyword);

	//Book findOne(Long id);

//	Book findOne(Long id);
}
