package org.icec.common.exception;
/**
 * 自定义异常
 * @author xxjin
 *
 */
public class IcecException extends RuntimeException{
	public final static int SERVER_ERROR=500;//服务器异常
	public final static int BIZ_ERROR=600;   //业务异常
	//友好提示的code码
	protected int code;
	

	public IcecException() {
		super();
	}

	public IcecException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IcecException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IcecException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public IcecException(int code,String message) {
		super(message);
		this.code=code;
	}
	public IcecException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
