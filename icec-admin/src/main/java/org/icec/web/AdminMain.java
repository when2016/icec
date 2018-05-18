package org.icec.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.Banner;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages= {"org.icec"})
public class AdminMain extends SpringBootServletInitializer {
	/**
	 * 为了将来支持war发布
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdminMain.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AdminMain.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

	
}
