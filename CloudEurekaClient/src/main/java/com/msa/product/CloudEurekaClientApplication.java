package com.msa.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient //유레카 클라이언트 명시
@SpringBootApplication
public class CloudEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaClientApplication.class, args);
	}
	
	@Bean
	@LoadBalanced//리본을 통해 로드밸런싱을 사용할것으로 명시  *라운드 로빈 방식으로 분산요청
	public RestTemplate restTemplate() {
		return new RestTemplate();
		
	}
	
	
	
	@RestController
	class EurekaClientController{
		
		@Autowired
		RestTemplate restTemplate;
		
		@GetMapping("/eureka/client")
		public String eurekaClient() {
			String result = restTemplate.getForObject("http://eurekaclient/eureka/client", String.class);//url에서 명시한 유레카클라이언트는 프로퍼티에서 어플리케이션 네이밍을 지정했을경우의dns를써준다.
			
			return result;
			
		}
	}
	

}
