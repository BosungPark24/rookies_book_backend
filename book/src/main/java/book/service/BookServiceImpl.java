package book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.dto.BookDto;
import book.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Transactional
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<BookDto> selectBookList() {
		return bookMapper.selectBookList();
	}
	
	@Override
	public void insertBook(BookDto bookDto) {
		
		bookMapper.insertBook(bookDto);
	}
	
	@Override
	public BookDto selectBookDetail(int bookId) {
		return bookMapper.selectBookDetail(bookId);
	}
	
	@Override
	public void updateBook(BookDto bookDto) {
		// TODO Auto-generated method stub
		
		bookMapper.updateBook(bookDto);	
	}
	@Override
	public void deleteBook(int bookId) {
	        log.info("Deleting book with id: {}", bookId);
	        BookDto bookDto = bookMapper.selectBookDetail(bookId);
	        if (bookDto == null) {
	            log.warn("Book with id {} not found.", bookId);
	            return;
	        }
	        if (bookDto.getAuthor() == null) {
	            bookDto.setAuthor("Unknown Author");
	        }
	        log.info("Book to be deleted: {}", bookDto);
	        bookMapper.deleteBook(bookDto);
	        log.info("Book deleted successfully.");
	}

	
}
