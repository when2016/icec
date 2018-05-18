package org.icec.web.sys.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-11-01
*/
@Table(name="sys_log")
public class SysLog   implements Serializable{
	public final static String LOGTYPE_LOGIN="1";  //登入登出
	public final static String LOGTYPE_REQUEST="2";  //业务请求
	public final static String LOGTYPE_EXCEPTION="3";//异常

	
	public SysLog(String createBy, String exception, String method, String params, String remoteAddr, String requestUri,
			String title, String type, String userAgent) {
		super();
		this.createBy = createBy;
		this.exception = exception;
		this.method = method;
		this.params = params;
		this.remoteAddr = remoteAddr;
		this.requestUri = requestUri;
		this.title = title;
		this.type = type;
		this.userAgent = userAgent;
	}
	//编号
	@AutoID
	private Integer id ;
	//创建者
	private String createBy ;
	//异常信息
	private String exception ;
	//操作方式
	private String method ;
	//操作提交的数据
	private String params ;
	//操作IP地址
	private String remoteAddr ;
	//请求URI
	private String requestUri ;
	//日志标题
	private String title ;
	//日志类型
	private String type ;
	//用户代理
	private String userAgent ;
	//创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate ;
	//用时
	private Long loseTime; 
	
	public SysLog() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public String getCreateBy(){
		return  createBy;
	}
	public void setCreateBy(String createBy ){
		this.createBy = createBy;
	}
	
	public String getException(){
		return  exception;
	}
	public void setException(String exception ){
		this.exception = exception;
	}
	
	public String getMethod(){
		return  method;
	}
	public void setMethod(String method ){
		this.method = method;
	}
	
	public String getParams(){
		return  params;
	}
	public void setParams(String params ){
		this.params = params;
	}
	
	public String getRemoteAddr(){
		return  remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr ){
		this.remoteAddr = remoteAddr;
	}
	
	public String getRequestUri(){
		return  requestUri;
	}
	public void setRequestUri(String requestUri ){
		this.requestUri = requestUri;
	}
	
	public String getTitle(){
		return  title;
	}
	public void setTitle(String title ){
		this.title = title;
	}
	
	public String getType(){
		return  type;
	}
	public void setType(String type ){
		this.type = type;
	}
	
	public String getUserAgent(){
		return  userAgent;
	}
	public void setUserAgent(String userAgent ){
		this.userAgent = userAgent;
	}
	
	public Date getCreateDate(){
		return  createDate;
	}
	public void setCreateDate(Date createDate ){
		this.createDate = createDate;
	}

	public Long getLoseTime() {
		return loseTime;
	}

	public void setLoseTime(Long loseTime) {
		this.loseTime = loseTime;
	}
	
	@Override
	public String toString() {
		return "SysLog [id=" + id + ", createBy=" + createBy + ", exception=" + exception + ", method=" + method
				+ ", params=" + params + ", remoteAddr=" + remoteAddr + ", requestUri=" + requestUri + ", title="
				+ title + ", type=" + type + ", userAgent=" + userAgent + ", createDate=" + createDate + ", loseTime="
				+ loseTime + "]";
	}
	

}
