package com.software.impact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.software.impact.services.MatriceService;

@SpringBootApplication
public class SoftwareImpactApplication implements ApplicationRunner {

	@Autowired
	MatriceService matriceService;

	public static void main(String[] args) {
		SpringApplication.run(SoftwareImpactApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args)  {
			matriceService.readModel();

	}
}
