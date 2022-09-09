package lt.codeacademy.learn.library;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lt.codeacademy.learn.library.controllers.AdminController;
import lt.codeacademy.learn.library.entities.Book;
import lt.codeacademy.learn.library.security.AppSecurity;
import lt.codeacademy.learn.library.security.SecurityConfiguration;
import lt.codeacademy.learn.library.services.BookService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTests {
	 
	
	@MockBean
	BookService bookService;
	
	@Autowired
	MockMvc mockMvc;

	
	@Test
	void failIfBookServiceNotAutowired() {
		Assertions.assertThat(bookService).isNotNull();
	}

	@Test 
	void failsIfNoBooksProvidedByService() throws Exception {
		Book book = new Book(2, "Alice","Dummy", "Author Dummy", "Category Dummy");
		List<Book> books = Arrays.asList(book);
		
		Mockito.when(bookService.getAllBooks()).thenReturn(books);
		
		mockMvc.perform(get("/admin/index"))
		.andExpect(status().isOk());
	}
	
	@Test //kad grizta kelias
	void shouldReturnPathToAddBookTemplate() throws Exception {
		mockMvc.perform(get("/admin/signup"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void whenSavingMethodSaveShouldBeCalled() {
		
		Book book = new Book (1, "Dummy", "Dummy dummy", "Dummy", "Dummy");
		
		bookService.save(book);
		
		verify(bookService, times(1)).save(book);
	}
}
