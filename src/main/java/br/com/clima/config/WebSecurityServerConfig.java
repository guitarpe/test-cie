package br.com.clima.config;

import br.com.clima.application.domain.repository.cie.IUsersRepository;
import br.com.clima.application.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityServerConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManagerBean();
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder auth, final IUsersRepository usersRepository) throws Exception {

		auth.userDetailsService(username -> new UserDTO(usersRepository.findByUsername(username)));
		//.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//return new BCryptPasswordEncoder(10);
		return NoOpPasswordEncoder.getInstance();
	}
}
