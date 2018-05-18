package org.icec.web.core.log;

import org.icec.web.sys.model.SysLog;
import org.icec.web.sys.service.SysLogService;
import org.icec.web.sys.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 */
public class LogTaskFactory {

	private static Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
	private static SysLogService sysLogService = SpringContextHolder.getBean(SysLogService.class);

	/**
	 * 请求日志
	 * 
	 * @param operationLog
	 * @return
	 */
	public static TimerTask requestLog(SysLog operationLog) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					operationLog.setCreateDate(new Date());
					sysLogService.save(operationLog);
				} catch (Exception e) {
					logger.error("创建业务日志异常!", e);
				}
			}
		};
	}

	/**
	 * 异常日志
	 * 
	 * @param userId
	 * @param exception
	 * @return
	 */
	public static TimerTask exceptionLog( SysLog operationLog, final Exception exception) {
		return new TimerTask() {
			@Override
			public void run() {
				 
				operationLog.setType(SysLog.LOGTYPE_EXCEPTION);
				String msg = getExceptionMsg(exception);
				operationLog.setException(msg);
				operationLog.setCreateDate(new Date());
				try {
					sysLogService.save(operationLog);
				} catch (Exception e) {
					logger.error("创建异常日志异常!", e);
				}
			}
		};
	}

	/**
	 * 获取异常的具体信息
	 *
	 * @author fengshuonan
	 * @Date 2017/3/30 9:21
	 * @version 2.0
	 */
	public static String getExceptionMsg(Exception e) {
		StringWriter sw = new StringWriter();
		try {
			e.printStackTrace(new PrintWriter(sw));
		} finally {
			try {
				sw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return sw.getBuffer().toString().replaceAll("\\$", "T");
	}
}
