package lt.codeacademy.learn.library.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import lt.codeacademy.learn.library.entities.Book;
import lt.codeacademy.learn.library.interfaces.BookRepository;
import lt.codeacademy.learn.library.services.BookService;

@Controller
@RequestMapping(path = "/user")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;
	
	@RequestMapping(path = {"/view"})
	 public String home(Book book, Model model, String keyword) {
	  if(keyword!=null) {
		  List<Book> list = bookService.getByKeyword(keyword);
	   model.addAttribute("list", list);
	  }else {
		  List<Book> list = bookService.getAllBooks();
	  model.addAttribute("list", list);}
	  return "view";
	 }
	 
	
	//Brigitos
	
	@GetMapping(path = {"/book/display/{id}"})
	void showImage(@PathVariable("id") int id, HttpServletResponse response, Optional<Book> book)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		book = bookService.getBookById1(id);
		response.getOutputStream().close();
	}

	@GetMapping(path = {"/book/bookDetails/{id}"})
	String showProductDetails(@PathVariable("id") int id, Optional<Book> book, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				book = bookService.getBookById1(id);
				log.info("books :: " + book);
				if (book.isPresent()) {
					model.addAttribute("id", book.get().getId());
					model.addAttribute("title", book.get().getTitle());
					model.addAttribute("description", book.get().getDescription());
					model.addAttribute("category", book.get().getCategory());
					model.addAttribute("author", book.get().getAuthor());
					return "bookdetails";
				}
				return "redirect:/user/view";
			}
		return "redirect:/user/view";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/user/view";
		}	
	}
	
}
