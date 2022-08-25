package lt.codeacademy.learn.library;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lt.codeacademy.learn.library.LibraryApplication;
import lt.codeacademy.learn.library.controllers.AdminController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LibraryApplicationTest {

	@Autowired
	AdminController adminController;
	
	@Test
	void contextLoads() {
		Assertions.assertThat(adminController).isNotNull();
	}
}
