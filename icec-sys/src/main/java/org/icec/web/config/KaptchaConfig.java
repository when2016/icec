package org.icec.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:mykaptcha.xml"}) 
public class KaptchaConfig {

}
