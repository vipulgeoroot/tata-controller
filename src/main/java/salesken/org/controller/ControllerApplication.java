package salesken.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
@EnableAsync
public class ControllerApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ControllerApplication.class, args);
		final Dispatcher dispatcher = configurableApplicationContext.getBean(Dispatcher.class);
		try {
			dispatcher.dispatchCall();
		}catch (Exception exception){
			log.error("Unable to dispatch calls");
			exception.printStackTrace();
		}
	}

}
