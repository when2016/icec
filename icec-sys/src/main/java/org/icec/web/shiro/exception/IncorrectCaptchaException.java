package org.icec.web.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;
/**
 * 无效验证码异常
 * @author loongsec
 *
 */
public class IncorrectCaptchaException extends AuthenticationException {  
 
   public IncorrectCaptchaException() {  
       super();  
   }  
 
   public IncorrectCaptchaException(String message, Throwable cause) {  
       super(message, cause);  
   }  
 
   public IncorrectCaptchaException(String message) {  
       super(message);  
   }  
 
   public IncorrectCaptchaException(Throwable cause) {  
       super(cause);  
   }  
}  