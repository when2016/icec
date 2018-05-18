package org.icec.web.sys.model;

import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateIgnore;
import org.icec.common.model.BaseModel;
import org.icec.common.model.TreeModel;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-10-22
*/
@Table(name = "sys_menu")
public class SysMenu extends TreeModel implements BaseModel {
	// 编号
	@AutoID
	@SeqID(name = "seq_sys_menu")
	private Integer id;
	// 排序
	private Integer sort = 30;
	// 创建者
	private Integer createBy;
	// 删除标记
	private String delFlag;
	// 链接
	private String href;
	// 图标
	private String icon;
	// 是否在菜单中显示
	private String isShow;
	// 名称
	private String name;
	// 父级编号
	private Integer parentId;
	// 所有父级编号
	private String parentIds;
	// 权限标识
	private String permission;
	// 备注信息
	private String remarks;
	// 目标
	private String target;
	// 菜单类型 1：菜单 2：按钮
	private String type;
	// 更新者
	private Integer updateBy;

	// 创建时间
	private Date createDate;
	// 更新时间
	private Date updateDate;
	// 父菜单名称
	private String parentName;
	
	
    
	public SysMenu() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
