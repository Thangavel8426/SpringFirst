package com.lablogbook.backend;

import com.lablogbook.backend.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LabExperimentLogbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabExperimentLogbookApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
} 