package org.icec.schedule;

import org.icec.schedule.service.TaskInfo;
import org.icec.schedule.service.JobTaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ScheduleApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(ScheduleApplication.class, args);
		JobTaskService taskService=	context.getBean(JobTaskService.class);
		TaskInfo info=new TaskInfo();
		info.setId(1);
		info.setJobDescription("");
		info.setJobGroup("a");
		info.setJobName("org.icec.schedule.job.DemoJob");
		info.setJobStatus("1");
		info.setCreateTime("");
		info.setCronExpression("0/5 * * * * ?");
		taskService.addJob(info);
	}
}
