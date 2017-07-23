package com.bd.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class McLoggerApplication {
	public static void main(String[] args) {
		SpringApplication.run(McLoggerApplication.class, args);
	}
}
