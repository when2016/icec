package org.icec.web.sys.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;

import org.icec.common.model.BaseModel;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-11-05
*/
public class SysDict   implements BaseModel{
	//编号
	private Integer id ;
	//父级编号
	private Integer parentId ;
	//排序（升序）
	private Integer sort;
	//创建者
	private Integer createBy ;
	//删除标记
	private String delFlag ;
	//描述
	private String description ;
	//标签名
	private String label ;
	//备注信息
	private String remarks ;
	//类型
	private String type ;
	//更新者
	private Integer updateBy ;
	//数据值
	private String value ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	public SysDict() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getParentId(){
		return  parentId;
	}
	public void setParentId(Integer parentId ){
		this.parentId = parentId;
	}
	
	public Integer getSort(){
		return  sort;
	}
	public void setSort(Integer sort ){
		this.sort = sort;
	}
	
	public Integer getCreateBy(){
		return  createBy;
	}
	public void setCreateBy(Integer createBy ){
		this.createBy = createBy;
	}
	
	public String getDelFlag(){
		return  delFlag;
	}
	public void setDelFlag(String delFlag ){
		this.delFlag = delFlag;
	}
	
	public String getDescription(){
		return  description;
	}
	public void setDescription(String description ){
		this.description = description;
	}
	
	public String getLabel(){
		return  label;
	}
	public void setLabel(String label ){
		this.label = label;
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
	
	public Integer getUpdateBy(){
		return  updateBy;
	}
	public void setUpdateBy(Integer updateBy ){
		this.updateBy = updateBy;
	}
	
	public String getValue(){
		return  value;
	}
	public void setValue(String value ){
		this.value = value;
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
	
	
	

}
