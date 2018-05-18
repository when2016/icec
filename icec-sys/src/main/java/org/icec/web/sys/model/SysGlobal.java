package org.icec.web.sys.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

/*
* 
* gen by icec 2018-01-16
*/
public class SysGlobal   implements Serializable{
	//编号
	private Integer id ;
	//logo id
	private Long logo ;
	//全称
	private String fullname ;
	//名称
	private String name ;
	//版权
	private String poweredby ;
	//版本
	private String version ;
	//创建日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime ;
	
	public SysGlobal() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Long getLogo(){
		return  logo;
	}
	public void setLogo(Long logo ){
		this.logo = logo;
	}
	
	public String getFullname(){
		return  fullname;
	}
	public void setFullname(String fullname ){
		this.fullname = fullname;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	public String getPoweredby(){
		return  poweredby;
	}
	public void setPoweredby(String poweredby ){
		this.poweredby = poweredby;
	}
	
	public String getVersion(){
		return  version;
	}
	public void setVersion(String version ){
		this.version = version;
	}
	
	public Date getCreateTime(){
		return  createTime;
	}
	public void setCreateTime(Date createTime ){
		this.createTime = createTime;
	}
	
	
	

}
