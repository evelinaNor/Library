package lt.codeacademy.learn.library.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lt.codeacademy.learn.library.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


	@Query(value = "SELECT * FROM book b where b.title like %:keyword%", nativeQuery = true)
	List<Book> findByKeyword(@Param("keyword") String keyword);
	
	
	
}
