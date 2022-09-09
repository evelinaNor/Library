package lt.codeacademy.learn.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.learn.library.entities.Book;
import lt.codeacademy.learn.library.interfaces.BookRepository;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
	 
	 
	 //Brigitos
	 
	 public Optional<Book> getBookById1(int id) {
			return bookRepository.findById(id);
		}
	 
	 private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
		@GetMapping("/books")
		public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
			try {
				List<Book> books = new ArrayList<Book>();

				if (title == null)
					bookRepository.findAll().forEach(books::add);
				else
					bookRepository.findByTitleContaining(title).forEach(books::add);

				if (books.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(books, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@GetMapping("/books/{id}")
		public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
			Optional<Book> bookData = bookRepository.findById(id);

			if (bookData.isPresent()) {
				return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		@PutMapping("/books/{id}")
		public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
			Optional<Book> bookData = bookRepository.findById(id);

			if (bookData.isPresent()) {
				Book _book = bookData.get();
				_book.setTitle(book.getTitle());
				_book.setDescription(book.getDescription());
				_book.setCategory(book.getCategory());
				_book.setAuthor(book.getAuthor());
				_book.setImage(book.getImage());
				return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		@DeleteMapping("/books/{id}")
		public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") int id) {
			try {
				bookRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		}

		@DeleteMapping("/books")
		public ResponseEntity<HttpStatus> deleteAllBooks() {
			try {
				bookRepository.deleteAll();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		}

}
