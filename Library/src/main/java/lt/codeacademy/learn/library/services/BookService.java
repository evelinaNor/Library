package lt.codeacademy.learn.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.learn.library.entities.Book;
import lt.codeacademy.learn.library.interfaces.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public Iterable<Book> findAll() {		
		return bookRepository.findAll();
	}

	public Optional<Book> findById(int id) {
		return bookRepository.findById(id);
	}

	public void delete(Book book) {		
		bookRepository.delete(book);
	}

	public Book getBooksById(int id) {
		
		return bookRepository.findById(id).get();
	}

	 public List<Book> getByKeyword(String keyword){
		  return bookRepository.findByKeyword(keyword);
	 }
	 
	 public List<Book> getAllBooks(){
		  List<Book> list =  (List<Book>)bookRepository.findAll();
		  return list;
		 }
	 

}
