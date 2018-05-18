package org.icec.web.oa.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;

import org.icec.common.model.BaseModel;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

/*
* 
* gen by icec 2018-03-06
*/
public class OaLeave   implements BaseModel{
	//编号
	private Integer id ;
	//工单状态
	private Integer status ;
	//申请人
	private Integer userId ;
	//删除标识
	private String deleted ;
	//类型
	private String leaveType ;
	//流程id
	private String processid ;
	//原因
	private String reason ;
	//实际用时
	private BigDecimal timeUsed ;
	//标题
	private String title ;
	//申请时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date applyTime ;
	//结束时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endTime ;
	//开始时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startTime ;
	
	public OaLeave() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getStatus(){
		return  status;
	}
	public void setStatus(Integer status ){
		this.status = status;
	}
	
	public Integer getUserId(){
		return  userId;
	}
	public void setUserId(Integer userId ){
		this.userId = userId;
	}
	
	public String getDeleted(){
		return  deleted;
	}
	public void setDeleted(String deleted ){
		this.deleted = deleted;
	}
	
	public String getLeaveType(){
		return  leaveType;
	}
	public void setLeaveType(String leaveType ){
		this.leaveType = leaveType;
	}
	
	public String getProcessid(){
		return  processid;
	}
	public void setProcessid(String processid ){
		this.processid = processid;
	}
	
	public String getReason(){
		return  reason;
	}
	public void setReason(String reason ){
		this.reason = reason;
	}
	
	public BigDecimal getTimeUsed(){
		return  timeUsed;
	}
	public void setTimeUsed(BigDecimal timeUsed ){
		this.timeUsed = timeUsed;
	}
	
	public String getTitle(){
		return  title;
	}
	public void setTitle(String title ){
		this.title = title;
	}
	
	public Date getApplyTime(){
		return  applyTime;
	}
	public void setApplyTime(Date applyTime ){
		this.applyTime = applyTime;
	}
	
	public Date getEndTime(){
		return  endTime;
	}
	public void setEndTime(Date endTime ){
		this.endTime = endTime;
	}
	
	public Date getStartTime(){
		return  startTime;
	}
	public void setStartTime(Date startTime ){
		this.startTime = startTime;
	}
	
	
	

}
