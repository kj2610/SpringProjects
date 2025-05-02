package com.infy.service;

import java.lang.reflect.UndeclaredThrowableException;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.BookDTO;
import com.infy.entity.Book;
import com.infy.exception.InfyBookException;
import com.infy.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;
	private  static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(BookServiceImpl.class);
	@Override
	public BookDTO getBookDetails(Integer bookId) throws InfyBookException {
		Optional<Book> b = bookRepository.findBookByBookId(bookId);
		Book book = b.orElseThrow(()->new InfyBookException("Service.BOOK_DETAILS_NOT_FOUND"));
		
		BookDTO bookDTO = new BookDTO();
		bookDTO.setAuthorName(book.getAuthorName());
		bookDTO.setBookId(book.getBookId());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setPrice(book.getPrice());
		bookDTO.setPublishedYear(book.getPublishedYear());
		bookDTO.setPublisher(book.getPublisher());
		bookDTO.setTitle(book.getTitle());
		
		return bookDTO;
	}

	@Override
	public void addBook(BookDTO bookDTO) throws InfyBookException {
		
	}

	@Override
	public List<BookDTO> getBookByAuthorName(String authorName) throws InfyBookException {
		
		Iterable<Book> books = bookRepository.findBookByAuthorName(authorName);
		System.out.println(books);
		
		List<Book> bookss = new ArrayList<>();
		List<BookDTO> bookDTOs = new ArrayList<>();
		for(Book book : books) {
			bookss.add(book);
		}
		if(bookss.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND_IN_DATABASE");
		}
		bookss.forEach(b ->{
			BookDTO bookDTO = new BookDTO();
			bookDTO.setAuthorName(b.getAuthorName());
			bookDTO.setBookId(b.getBookId());
			bookDTO.setIsbn(b.getIsbn());
			bookDTO.setPrice(b.getPrice());
			bookDTO.setPublishedYear(b.getPublishedYear());
			bookDTO.setPublisher(b.getPublisher());
			bookDTO.setTitle(b.getTitle());
			bookDTOs.add(bookDTO);		
		});
		return bookDTOs;
	}

	@Override
	public List<BookDTO> getBookGreaterThanEqualToPrice(Integer price) throws InfyBookException {
		Iterable<Book> books = bookRepository.findByPrice(price);
		List<Book> book = new ArrayList<>();
		List<BookDTO>bookDTOs = new ArrayList<>();
		for(Book book1: books) {
			book.add(book1);
		}
		if(book.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND_IN_DATABASE");
		}
		book.forEach(b ->{
			BookDTO bookDTO = new BookDTO();
			bookDTO.setAuthorName(b.getAuthorName());
			bookDTO.setBookId(b.getBookId());
			bookDTO.setIsbn(b.getIsbn());
			bookDTO.setPrice(b.getPrice());
			bookDTO.setPublishedYear(b.getPublishedYear());
			bookDTO.setPublisher(b.getPublisher());
			bookDTO.setTitle(b.getTitle());
			bookDTOs.add(bookDTO);		
		});
		return bookDTOs;
	}

	@Override
	public List<BookDTO> getBookLessThanPrice(Integer price) throws InfyBookException {
		Iterable<Book> book1 = bookRepository.getBookLessThanPrice(price);
		LOGGER.error(book1);
		List<Book> books = new ArrayList<>();
		List<BookDTO>bookDTOs = new ArrayList<>();
		for(Book book : book1) {
			books.add(book);
		}
		if(books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND_IN_DATABASE");
		}
		books.forEach(b ->{
			BookDTO bookDTO = new BookDTO();
			bookDTO.setAuthorName(b.getAuthorName());
			bookDTO.setBookId(b.getBookId());
			bookDTO.setIsbn(b.getIsbn());
			bookDTO.setPrice(b.getPrice());
			bookDTO.setPublishedYear(b.getPublishedYear());
			bookDTO.setPublisher(b.getPublisher());
			bookDTO.setTitle(b.getTitle());
			bookDTOs.add(bookDTO);		
		});
		return bookDTOs;
	}

	@Override
	public List<BookDTO> bookPublishedBetweenYear(LocalDate startYear, LocalDate endYear) throws InfyBookException {
		return null;
	}

	@Override
	public List<BookDTO> bookPublishedAfterYear(LocalDate year) throws InfyBookException {
		return null;
	}

	@Override
	public List<BookDTO> getBookByAuthorNameAndPublisher(String authorName, String publisher) throws InfyBookException {
		return null;
	}

	@Override
	public void updateBookPrice(Integer bookId, Integer price) throws InfyBookException {
		
	}

	@Override
	public void deleteBook(Integer bookId) throws InfyBookException {
		
	}


	
}
