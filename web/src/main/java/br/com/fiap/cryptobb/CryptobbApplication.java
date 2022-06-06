package br.com.fiap.cryptobb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CryptobbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptobbApplication.class, args);
	}

}