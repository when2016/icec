package org.icec.web.sys.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-12-08
*/
public class SysFile   implements Serializable{
	//编号
	private Integer id ;
	//删除标识
	private Integer deleted ;
	//大小
	private Long fileSize ;
	//状态
	private Integer state ;
	//业务单号
	private String busiNo ;
	//业务类型
	private String busiType ;
	private Integer createBy ;
	//文件名
	private String fileName ;
	//类型
	private String fileType ;
	//存储位置
	private String fileUrl ;
	//注释
	private String memo ;
	private Integer updateBy ;
	private Date createTime ;
	private Date updateTime ;
	
	public SysFile() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getDeleted(){
		return  deleted;
	}
	public void setDeleted(Integer deleted ){
		this.deleted = deleted;
	}
	
	public Long getFileSize(){
		return  fileSize;
	}
	public void setFileSize(Long fileSize ){
		this.fileSize = fileSize;
	}
	
	public Integer getState(){
		return  state;
	}
	public void setState(Integer state ){
		this.state = state;
	}
	
	public String getBusiNo(){
		return  busiNo;
	}
	public void setBusiNo(String busiNo ){
		this.busiNo = busiNo;
	}
	
	public String getBusiType(){
		return  busiType;
	}
	public void setBusiType(String busiType ){
		this.busiType = busiType;
	}
	
	public Integer getCreateBy(){
		return  createBy;
	}
	public void setCreateBy(Integer createBy ){
		this.createBy = createBy;
	}
	
	public String getFileName(){
		return  fileName;
	}
	public void setFileName(String fileName ){
		this.fileName = fileName;
	}
	
	public String getFileType(){
		return  fileType;
	}
	public void setFileType(String fileType ){
		this.fileType = fileType;
	}
	
	public String getFileUrl(){
		return  fileUrl;
	}
	public void setFileUrl(String fileUrl ){
		this.fileUrl = fileUrl;
	}
	
	public String getMemo(){
		return  memo;
	}
	public void setMemo(String memo ){
		this.memo = memo;
	}
	
	public Integer getUpdateBy(){
		return  updateBy;
	}
	public void setUpdateBy(Integer updateBy ){
		this.updateBy = updateBy;
	}
	
	public Date getCreateTime(){
		return  createTime;
	}
	public void setCreateTime(Date createTime ){
		this.createTime = createTime;
	}
	
	public Date getUpdateTime(){
		return  updateTime;
	}
	public void setUpdateTime(Date updateTime ){
		this.updateTime = updateTime;
	}
	
	
	

}
