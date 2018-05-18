package org.icec.schedule.core;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration 
public class QuartzConfigration {
	  
  //指定quartz.properties  
    @Bean  
    public Properties quartzProperties() throws IOException {  
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();  
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));  
        propertiesFactoryBean.afterPropertiesSet();  
        return propertiesFactoryBean.getObject();  
    }  
  
    @Bean
	public Scheduler scheduler() throws IOException, SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		return scheduler;
	}
}
