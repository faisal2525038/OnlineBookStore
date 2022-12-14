package com.onlinebookstore.service;

import com.onlinebookstore.dto.Book;
import com.onlinebookstore.exception.BookException;
import java.util.List;

public interface BookService {

	Book addBook(Book book) throws BookException;

	Book getBookById(Integer bookId) throws BookException;

	Book updateBook(Book book) throws BookException;

	String deleteBookById(Integer bookId) throws BookException;

	List<Book> getAllBooks() throws BookException;
	
	List<Book> findByBookName(String bookName) throws BookException;

}
