package book.dto;

import lombok.Data;

//t_board 테이블에 데이터를 담을 객체 
//해당 객체의 필드는 해당 테이블의 컬럼과 유사 

//https://projectlombok.org/features/Data
@Data
public class BookDto {
	private int bookId;
	private String title;
	private String author;
	private String publisher;
	private String publishDate;
	private String description;
	private String createdAt;
	private String updatedAt;
}
