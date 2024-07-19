package book.service;

import java.util.List;

import book.dto.BookDto;

public interface BookService {
	List<BookDto> selectBookList();

	void insertBook(BookDto bookDto);

	BookDto selectBookDetail(int bookId);

	void updateBook(BookDto bookDto);

	void deleteBook(int bookId);

	
}
