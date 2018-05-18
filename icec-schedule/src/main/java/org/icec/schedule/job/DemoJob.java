package org.icec.schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoJob implements Job{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("JobName: {}", context.getJobDetail().getKey().getName());
	}

}
