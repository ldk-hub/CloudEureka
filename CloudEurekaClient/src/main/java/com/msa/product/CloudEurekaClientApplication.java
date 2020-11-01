package com.msa.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient //����ī Ŭ���̾�Ʈ ���
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class CloudEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaClientApplication.class, args);
	}
	
	@Bean
	@LoadBalanced//������ ���� �ε�뷱���� ����Ұ����� ���  *���� �κ� ������� �л��û
	public RestTemplate restTemplate() {
		return new RestTemplate();
		
	}
	
	
	
	@RestController
	class EurekaClientController{
		
		@Autowired
		RestTemplate restTemplate;
		
		@GetMapping("/eureka/client")
		public String eurekaClient() {
			String result = restTemplate.getForObject("http://eurekaclient/eureka/client", String.class);//url���� ����� ����īŬ���̾�Ʈ�� ������Ƽ���� ���ø����̼� ���̹��� �������������dns�����ش�.
			
			return result;
			
		}
	}
	

}
