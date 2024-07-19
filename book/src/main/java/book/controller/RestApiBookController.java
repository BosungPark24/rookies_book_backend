package book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import book.dto.BookDto;
import book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiBookController {
	
		@Autowired
		private BookService bookService;

		
		@Operation(summary = "도서 목록 조회", description = "등록된 도서 목록을 조회해서 반환합니다.")
		@GetMapping("/book")
		public List<BookDto> openBookList(HttpServletRequest request) throws Exception {
			return bookService.selectBookList();
		}

		
		@Operation(summary = "도서 등록 화면", description = "도서 등록 화면을 보여줍니다.")
		@Parameter(name = "bookDto", description = "도서 정보를 담고 있는 객체", required = true)
		@GetMapping("/book/write")
		public String openBookWrite() throws Exception {
			return "/book/bookWrite";
		}
		
		@Operation(summary = "도서 등록", description = "도서 정보를 저장합니다.")
		@Parameter(name = "bookDto", description = "도서 정보를 담고 있는 객체", required = true)	
		@PostMapping("/book/write")
		public String insertBook(@RequestBody BookDto bookDto, MultipartHttpServletRequest request) throws Exception {
			bookService.insertBook(bookDto, request);
			
		}
		
		@Operation(summary = "도서 상세 조회", description = "ID로 도서 정보를 조회합니다.")
	    @Parameter(name = "bookId", description = "조회할 도서의 ID", required = true)
		@GetMapping("/book/{bookId}")
		public ResponseEntity<BookDto> openBookDetail(@PathVariable("bookId") int bookId) throws Exception {
			BookDto bookDto = bookService.selectBookDetail(bookId);
			if (bookDto == null) {
				Map<String, String> result = new HashMap<>();
				result.put("code", HttpStatus.NOT_FOUND.toString());
				result.put("message", "일치하는 도서가 존재하지 않습니다.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(bookDto);
			}
		}

		
		
		@Operation(summary = "도서 수정", description = "ID로 도서 정보를 수정합니다.")
	    @Parameter(name = "bookId", description = "수정할 도서의 ID", required = true)
	    @Parameter(name = "bookDto", description = "수정할 도서 정보 객체", required = true)
		@PutMapping("/book/{bookId}")
		public void updateBook(@PathVariable("bookId") int bookId, @RequestBody BookDto bookDto) throws Exception {
			bookDto.setBookId(bookId);
			bookService.updateBook(bookDto);
		}
		
		
		@Operation(summary = "도서 삭제", description = "ID로 도서를 삭제합니다.")
	    @Parameter(name = "bookId", description = "삭제할 도서의 ID", required = true)
		@DeleteMapping("/book/{bookId}")
		public void deleteBook(@PathVariable("bookId") int bookId) throws Exception {
			bookService.deleteBook(bookId);
		}


}
