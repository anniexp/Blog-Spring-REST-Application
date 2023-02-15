package com.foodieblog.foodieblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.foodieblog.foodieblog.repositories")
@EntityScan(basePackages = {"com.foodieblog.foodieblog.entities"})
@SpringBootApplication
public class FoodieblogApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoodieblogApplication.class, args);
	}
}
