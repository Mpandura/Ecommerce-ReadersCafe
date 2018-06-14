package com.bookstore.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;
/*This class implements the book list page and the request mapping in string maps to that http and each method gets implemented when certain http is called and serves for dynamic page loading*/
/*Methods implemented in this are addBookPost(), upload(), updateImageePost(), getBook(), getBookList(), remove(), searchBook()*/
@RestController
@RequestMapping("/book")
public class BookResource {
	
	@Autowired
	private BookService bookService;
	/* when the http method ../book/add is called this is implemented and saves the book in book repository*/
	@RequestMapping (value="/add", method=RequestMethod.POST)
	public Book addBookPost(@RequestBody Book book) {
		return bookService.save(book);
	}
	/* when the http method ../book/add/image is called this is implemented and saves the book's image by finding the id of the book in "src/main/resources/static/image/book/imageName" */
	@RequestMapping(value="/add/image", method=RequestMethod.POST)
	public ResponseEntity upload(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			Book book = bookService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}

	/* when the http method ../book/update/image is called this is implemented and updates the book's old image with new one in "src/main/resources/static/image/book/fileName" */
	@RequestMapping(value="/update/image", method=RequestMethod.POST)
	public ResponseEntity updateImagePost(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			Book book = bookService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}	

	/* when the http method ../book/bookList is called this is implemented and returns the list of all books in book repository*/	
	@RequestMapping("/bookList")
	public List<Book> getBookList() {
		return bookService.findAll();
	}

	/* when the http method ../book/update is called this is implemented and updates the book in book repository*/
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Book updateBookPost(@RequestBody Book book) {
		return bookService.save(book);
	}

	/* when the http method ../book/remove is called this is implemented and removes the book in book repository*/
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity remove(
			@RequestBody String id
			) throws IOException {
		bookService.removeOne(Long.parseLong(id));
		String fileName = id+".png";
		
		Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
		
		return new ResponseEntity("Remove Success!", HttpStatus.OK);
	}

	/* when the http method ../book/id is called this is implemented and returns the book with particular id in book repository*/
	@RequestMapping("/{id}")
	public Book getBook(@PathVariable("id") Long id){
		Book book = bookService.findOne(id);
		return book;
	}
	/* when the http method ../book/searchBook is called this is implemented and returns the list of books with the string keyword that is present in book repository*/
	@RequestMapping(value="/searchBook", method=RequestMethod.POST)
	public List<Book> searchBook (@RequestBody String keyword) {
		List<Book> bookList = bookService.blurrySearch(keyword);
		
		return bookList;
	}
}
