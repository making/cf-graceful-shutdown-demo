package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloCfApplication {
	Logger log = LoggerFactory.getLogger(HelloCfApplication.class);

	@RequestMapping("/")
	String hello(@RequestParam(name = "count", defaultValue = "5") int count) throws Exception {
		log.info("====Start====");
		for (int i = 0; i < count; i++) {
			log.info("In Progress ({}/{})", i + 1, count);
			Thread.sleep(1000);
		}
		log.info("====End  ====");
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloCfApplication.class, args);
	}
}
