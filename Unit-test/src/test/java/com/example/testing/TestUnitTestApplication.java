package com.example.testing;

import org.springframework.boot.SpringApplication;

public class TestUnitTestApplication {

	public static void main(String[] args) {
		SpringApplication.from(UnitTestApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
