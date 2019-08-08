package com.jqm.gateway;

import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTest {
	
	@Value("#{${swagger.api}}")
	private Map<String, String> api;
	
//	@Test
	public void test() {
		System.out.println(api.size());
		api.forEach((k,v) ->{
			System.out.println(k+":"+v);
		});
	}
}
