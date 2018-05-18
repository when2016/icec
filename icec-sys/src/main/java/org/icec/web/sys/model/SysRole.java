package org.icec.web.sys.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.icec.common.model.BaseModel;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-10-22
*/
@Table(name="sys_role")
public class SysRole  implements BaseModel {
	//编号
	@AutoID
	@SeqID(name="seq_sys_role")
	private Integer id ;
	//创建者
	private Integer createBy ;
	//数据范围
	private String dataScope ;
	//删除标记
	private String delFlag ;
	//英文名称
	private String enname ;
	//是否系统数据
	private String isSys ;
	//角色名称
	private String name ;
	//归属机构
	private Integer officeId ;
	//备注信息
	private String remarks ;
	//角色类型
	private String roleType ;
	//更新者
	private Integer updateBy ;
	//是否可用
	private String useable ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	
	
	private String officeName ;//机构名称
	
	public SysRole() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getCreateBy(){
		return  createBy;
	}
	public void setCreateBy(Integer createBy ){
		this.createBy = createBy;
	}
	
	public String getDataScope(){
		return  dataScope;
	}
	public void setDataScope(String dataScope ){
		this.dataScope = dataScope;
	}
	
	public String getDelFlag(){
		return  delFlag;
	}
	public void setDelFlag(String delFlag ){
		this.delFlag = delFlag;
	}
	
	public String getEnname(){
		return  enname;
	}
	public void setEnname(String enname ){
		this.enname = enname;
	}
	
	public String getIsSys(){
		return  isSys;
	}
	public void setIsSys(String isSys ){
		this.isSys = isSys;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	public Integer getOfficeId(){
		return  officeId;
	}
	public void setOfficeId(Integer officeId ){
		this.officeId = officeId;
	}
	
	public String getRemarks(){
		return  remarks;
	}
	public void setRemarks(String remarks ){
		this.remarks = remarks;
	}
	
	public String getRoleType(){
		return  roleType;
	}
	public void setRoleType(String roleType ){
		this.roleType = roleType;
	}
	
	public Integer getUpdateBy(){
		return  updateBy;
	}
	public void setUpdateBy(Integer updateBy ){
		this.updateBy = updateBy;
	}
	
	public String getUseable(){
		return  useable;
	}
	public void setUseable(String useable ){
		this.useable = useable;
	}
	
	public Date getCreateDate(){
		return  createDate;
	}
	public void setCreateDate(Date createDate ){
		this.createDate = createDate;
	}
	
	public Date getUpdateDate(){
		return  updateDate;
	}
	public void setUpdateDate(Date updateDate ){
		this.updateDate = updateDate;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	
	

}
