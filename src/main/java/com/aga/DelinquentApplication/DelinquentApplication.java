package com.aga.DelinquentApplication;

import com.aga.DelinquentApplication.logic.Logic;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelinquentApplication implements CommandLineRunner {

	@Autowired
	Logic logic;

	public static void main(String[] args) {

		SpringApplication.run(DelinquentApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		logic.menu();

	}
}
