package org.icec.web.sys.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.icec.common.model.BaseModel;
import org.icec.common.model.TreeModel;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-10-27
*/
@Table(name="sys_area")
public class SysArea   extends TreeModel implements BaseModel{
	//编号
	@AutoID
	@SeqID(name="seq_sys_area")
	private Integer id ;
	//创建者
	private Integer createBy ;
	//排序
	private Integer sort =30;
	//更新者
	private Integer updateBy ;
	//区域编码
	private String code ;
	//删除标记
	private String delFlag ;
	//名称
	private String name ;
	//父级编号
	private Integer parentId ;
	//所有父级编号
	private String parentIds ;
	//备注信息
	private String remarks ;
	//区域类型
	private String type ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	
	//父机构名称
	private String parentName;
	
	
	public SysArea() {
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


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	 

	 

}
