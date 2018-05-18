package org.icec.web.sys.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;

import org.icec.common.model.BaseModel;
import org.icec.common.model.TreeModel;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-10-24
*/
public class SysOffice   extends TreeModel implements BaseModel{
	//编号
	private Integer id ;
	//创建者
	private Integer createBy ;
	//排序
	private Integer sort =30;
	//更新者
	private Integer updateBy ;
	//副负责人
	private String deputyPerson ;
	//主负责人
	private String primaryPerson ;
	//是否启用
	private String useable ;
	//联系地址
	private String address ;
	//归属区域
	private String areaId ;
	//区域编码
	private String code ;
	//删除标记
	private String delFlag ;
	//邮箱
	private String email ;
	//传真
	private String fax ;
	//机构等级
	private String grade ;
	//负责人
	private String master ;
	//名称
	private String name ;
	//父级编号
	private Integer parentId ;
	//所有父级编号
	private String parentIds ;
	//电话
	private String phone ;
	//备注信息
	private String remarks ;
	//机构类型
	private String type ;
	//邮政编码
	private String zipCode ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	
	//父机构名称
	private String parentName;
	//区域名称
	private String areaName ;
	
	public SysOffice() {
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
	
	public Integer getSort(){
		return  sort;
	}
	public void setSort(Integer sort ){
		this.sort = sort;
	}
	
	public Integer getUpdateBy(){
		return  updateBy;
	}
	public void setUpdateBy(Integer updateBy ){
		this.updateBy = updateBy;
	}
	
	public String getDeputyPerson(){
		return  deputyPerson;
	}
	public void setDeputyPerson(String deputyPerson ){
		this.deputyPerson = deputyPerson;
	}
	
	public String getPrimaryPerson(){
		return  primaryPerson;
	}
	public void setPrimaryPerson(String primaryPerson ){
		this.primaryPerson = primaryPerson;
	}
	
	public String getUseable(){
		return  useable;
	}
	public void setUseable(String useable ){
		this.useable = useable;
	}
	
	public String getAddress(){
		return  address;
	}
	public void setAddress(String address ){
		this.address = address;
	}
	
	public String getAreaId(){
		return  areaId;
	}
	public void setAreaId(String areaId ){
		this.areaId = areaId;
	}
	
	public String getCode(){
		return  code;
	}
	public void setCode(String code ){
		this.code = code;
	}
	
	public String getDelFlag(){
		return  delFlag;
	}
	public void setDelFlag(String delFlag ){
		this.delFlag = delFlag;
	}
	
	public String getEmail(){
		return  email;
	}
	public void setEmail(String email ){
		this.email = email;
	}
	
	public String getFax(){
		return  fax;
	}
	public void setFax(String fax ){
		this.fax = fax;
	}
	
	public String getGrade(){
		return  grade;
	}
	public void setGrade(String grade ){
		this.grade = grade;
	}
	
	public String getMaster(){
		return  master;
	}
	public void setMaster(String master ){
		this.master = master;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	public Integer getParentId(){
		return  parentId;
	}
	public void setParentId(Integer parentId ){
		this.parentId = parentId;
	}
	
	public String getParentIds(){
		return  parentIds;
	}
	public void setParentIds(String parentIds ){
		this.parentIds = parentIds;
	}
	
	public String getPhone(){
		return  phone;
	}
	public void setPhone(String phone ){
		this.phone = phone;
	}
	
	public String getRemarks(){
		return  remarks;
	}
	public void setRemarks(String remarks ){
		this.remarks = remarks;
	}
	
	public String getType(){
		return  type;
	}
	public void setType(String type ){
		this.type = type;
	}
	
	public String getZipCode(){
		return  zipCode;
	}
	public void setZipCode(String zipCode ){
		this.zipCode = zipCode;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	 
	
	
	

}
