package com.jarry;

import com.jarry.config.MyDBConfig;
import com.jarry.config.MyDBConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {MyDBConfig.class, MyDBConfig2.class})
public class AtomikosDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtomikosDemoApplication.class, args);
	}
}
