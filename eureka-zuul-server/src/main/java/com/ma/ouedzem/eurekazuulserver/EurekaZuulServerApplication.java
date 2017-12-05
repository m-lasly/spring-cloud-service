package com.ma.ouedzem.eurekazuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulServer
public class EurekaZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaZuulServerApplication.class, args);
	}
}
