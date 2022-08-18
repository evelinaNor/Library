package lt.codeacademy.learn.library.controllers;

import java.util.List;

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

	@Autowired
	private BookService bookService;
	
	@RequestMapping(path = {"/","/search"})
	 public String home(Book book, Model model, String keyword) {
	  if(keyword!=null) {
		  List<Book> list = bookService.getByKeyword(keyword);
	   model.addAttribute("list", list);
	  }else {
		  List<Book> list = bookService.getAllProducts();
	  model.addAttribute("list", list);}
	  return "view";
	 }
	
	
} //https://codebun.com/spring-boot-search-example-using-thymeleaf-and-spring-data-jpa/
