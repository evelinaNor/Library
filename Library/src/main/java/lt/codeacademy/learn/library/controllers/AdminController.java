package lt.codeacademy.learn.library.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lt.codeacademy.learn.library.entities.Book;
import lt.codeacademy.learn.library.services.BookService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
		
	@Autowired
	BookService bookService;
	
	@GetMapping("/index")
	public String showBookList(Model model) {
	    model.addAttribute("books", bookService.findAll());
	    return "admin/index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		System.out.println("showUserList");
	    Book book = bookService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
	    
	    model.addAttribute("book", book);
	    return "admin/update-book";
	}
	
	
	@PostMapping("/update/{id}")
	public String updateBook(@PathVariable("id") int id, Book book, 
	  BindingResult result, Model model,
	   // if (result.hasErrors()) {
	        //book.setId(id);
		@RequestParam ("image") MultipartFile multipartFile) throws IOException {

	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        book.setImage(fileName);
	        
	      Book savedBook = bookService.save(book);
	      
	      String uploadDir = "./book-images/" + savedBook.getId();
	      Path uploadPath = Paths.get(uploadDir);
	      
	      
	      if(!Files.exists(uploadPath)) {
	    	  Files.createDirectories(uploadPath);
	      }
	      
	     try (InputStream inputStream = multipartFile.getInputStream()) {
	      Path filePath = uploadPath.resolve(fileName);
	      System.out.println(filePath.toFile().getAbsolutePath());
	      
	      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	     } catch(IOException e){
	    	 throw new IOException("Could not save upload file: " + fileName);
	    	 
	     }
	        return "redirect:/admin/index";  
	}      
	
	    
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") int id, Model model) {
	    Book book = bookService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
	    bookService.delete(book);
	    return "redirect:/admin/index";
	}
	
	@GetMapping("/signup")
    public String showSignUpForm(Book book) {
        return "admin/add-book";
    }
	
	@PostMapping("/addbook")
    public String addBook(
    		@Validated Book book, 
    		BindingResult result, 
    		Model model, 
    		@RequestParam ("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        book.setImage(fileName);
        
      Book savedBook = bookService.save(book);
      
      String uploadDir = "./book-images/" + savedBook.getId();
      Path uploadPath = Paths.get(uploadDir);
      
      
      if(!Files.exists(uploadPath)) {
    	  Files.createDirectories(uploadPath);
      }
      
     try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      System.out.println(filePath.toFile().getAbsolutePath());
      
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
     } catch(IOException e){
    	 throw new IOException("Could not save upload file: " + fileName);
    	 
     }
        return "redirect:/admin/index";
	}
}

