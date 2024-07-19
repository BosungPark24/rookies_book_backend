package book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import book.dto.BookDto;

@Mapper
public interface BookMapper {
	List<BookDto> selectBookList();

	BookDto selectBookDetail(int bookId);

	void insertBook(BookDto bookDto);

	void updateBook(BookDto bookDto);

	void deleteBook(BookDto bookDto);
}
