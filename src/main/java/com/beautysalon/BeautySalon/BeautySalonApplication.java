package com.beautysalon.BeautySalon;
import com.beautysalon.BeautySalon.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
public class BeautySalonApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BeautySalonApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
	}

}
