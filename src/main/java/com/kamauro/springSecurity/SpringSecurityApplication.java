package com.kamauro.springSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		// System.out.println(new BCryptPasswordEncoder().encode("root"));
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
