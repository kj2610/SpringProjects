package com.infy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	
	@Query("select b from Book b where b.authorName=:authorName")
	Iterable<Book> findBookByAuthorName(String authorName);
	
	@Query("select b from Book b where b.bookId=:bookId")
	Optional<Book> findBookByBookId(Integer bookId);
	
	@Query("select b from Book b where b.price >= :price")
	Iterable<Book> findByPrice(@Param("price") Integer price);
	
	@Query("select b from Book b where b.price <= :price")
	Iterable<Book>getBookLessThanPrice(@Param("price") Integer price);
}