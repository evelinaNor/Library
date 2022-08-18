package lt.codeacademy.learn.library.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppSecurity {
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
	    auth.jdbcAuthentication()
	      .dataSource(dataSource)	      
	      	.usersByUsernameQuery("select username,password,enabled "
	    	        + "from users "
	    	        + "where username = ?")
	      	.authoritiesByUsernameQuery("select username,authority "
	    	        + "from authorities "
	    	        + "where username = ?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
