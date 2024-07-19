package book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import book.dto.BookDto;
import book.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	//도서 목록 조회
	@GetMapping("/book")
	public ModelAndView openBookList(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/book/bookList");
		
		List<BookDto> list = bookService.selectBookList();
		mv.addObject("list", list);
		
		return mv;
	}
	//도서 등록 화면
	@GetMapping("/book/write")
	public String openBookWrite() throws Exception {
		return "/book/bookWrite";
	}
		
	//도서 등록 
	@PostMapping("/book/write")
	public String insertBook(BookDto bookDto) throws Exception {
		bookService.insertBook(bookDto);
		return "redirect:/book";	
	}
	
	//도서 상세 조회 요청을 처리할 메서드
	// /board/openBoardDetail.do?boardIdx=0000
	@GetMapping("/book/{bookId}")
	public ModelAndView openBookDetail(@PathVariable("bookId") int bookId) throws Exception {
		ModelAndView mv = new ModelAndView("/book/bookDetail");
			
		BookDto bookDto = bookService.selectBookDetail(bookId);
		mv.addObject("book", bookDto);
			
		return mv;
		}
	
	
	//도서 목록 수정 과정
	@PutMapping("/book/{bookId}")
	public String updateBook(@PathVariable("bookId") int bookId, BookDto bookDto) throws Exception {
		bookService.updateBook(bookDto);
		return "redirect:/book";
	}
	
	//도서 목록 삭제
	@DeleteMapping("/book/{bookId}")
	public String deleteBook(@PathVariable("bookId") int bookId) throws Exception {
		bookService.deleteBook(bookId);
		return "redirect:/book";
	}
}

