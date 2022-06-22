package com.estudo.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
@Slf4j
public class Application {

	private static final String ACCESS_URLS_MESSAGE_LOG =
			"\n\n Access URLs:\n----------------------------------------------------------\n\t External: \thttp://{}:{}/swagger-ui.html Profiles: {}\n----------------------------------------------------------\n";

	public static void main(final String[] args) {
		try {
			System.setProperty("spring.devtools.restart.enabled", "false");
			final SpringApplication app =
					new SpringApplication(Application.class);
			final Environment env = app.run().getEnvironment();
			log.info(
					ACCESS_URLS_MESSAGE_LOG,
					InetAddress.getLocalHost().getHostAddress(),
					env.getProperty("server.port"),
					env.getActiveProfiles());
		} catch (Exception e) {
			log.error("Start Error.", e);
		}
	}

}
