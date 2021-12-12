package com.telenor.Telenor;

import com.telenor.Telenor.model.Product;
import com.telenor.Telenor.provider.DataProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelenorApplication {

	@Bean
	public DataProvider dataProvider(){
		return new DataProvider();
	}

	public static void main(String[] args) {
		SpringApplication.run(TelenorApplication.class, args);
	}

}

