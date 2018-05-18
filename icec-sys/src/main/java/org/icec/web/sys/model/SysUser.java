package org.icec.web.sys.model;
import java.math.*;
import java.util.Date;

import org.apache.shiro.authc.AuthenticationToken;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.icec.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-10-21
*/
@Table(name="sys_user")
public class SysUser  implements BaseModel ,AuthenticationToken{
	/**
	 * 登录标记（0：正常；1：删除；）
	 */
	public static final String LOGIN_FLAG_NORMAL = "0";
	public static final String LOGIN_FLAG_DELETE = "1";
	
	//编号
	@AutoID
	@SeqID(name="seq_sys_user")
	private Integer id ;
	//归属公司
	private String companyId ;
	//创建者
	private Integer createBy ;
	//删除标记
	private String delFlag ;
	//邮箱
	private String email ;
	//是否可登录
	private String loginFlag ;
	//最后登陆IP
	private String loginIp ;
	//登录名
	private String loginName ;
	//手机
	private String mobile ;
	//姓名
	private String name ;
	//工号
	private String no ;
	//归属部门
	private String officeId ;
	//密码
	private String password ;
	//电话
	private String phone ;
	//用户头像
	private String photo ;
	//备注信息
	private String remarks ;
	//更新者
	private Integer updateBy ;
	//用户类型
	private String userType ;
	//创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate ;
	//最后登陆时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date loginDate ;
	//更新时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateDate ;
	
	private String companyName;//公司名称
	private String officeName;//部门名称
	
	public SysUser() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public String getCompanyId(){
		return  companyId;
	}
	public void setCompanyId(String companyId ){
		this.companyId = companyId;
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
	
	public String getEmail(){
		return  email;
	}
	public void setEmail(String email ){
		this.email = email;
	}
	
	public String getLoginFlag(){
		return  loginFlag;
	}
	public void setLoginFlag(String loginFlag ){
		this.loginFlag = loginFlag;
	}
	
	public String getLoginIp(){
		return  loginIp;
	}
	public void setLoginIp(String loginIp ){
		this.loginIp = loginIp;
	}
	
	public String getLoginName(){
		return  loginName;
	}
	public void setLoginName(String loginName ){
		this.loginName = loginName;
	}
	
	public String getMobile(){
		return  mobile;
	}
	public void setMobile(String mobile ){
		this.mobile = mobile;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	public String getNo(){
		return  no;
	}
	public void setNo(String no ){
		this.no = no;
	}
	
	public String getOfficeId(){
		return  officeId;
	}
	public void setOfficeId(String officeId ){
		this.officeId = officeId;
	}
	
	public String getPassword(){
		return  password;
	}
	public void setPassword(String password ){
		this.password = password;
	}
	
	public String getPhone(){
		return  phone;
	}
	public void setPhone(String phone ){
		this.phone = phone;
	}
	
	public String getPhoto(){
		return  photo;
	}
	public void setPhoto(String photo ){
		this.photo = photo;
	}
	
	public String getRemarks(){
		return  remarks;
	}
	public void setRemarks(String remarks ){
		this.remarks = remarks;
	}
	
	public Integer getUpdateBy(){
		return  updateBy;
	}
	public void setUpdateBy(Integer updateBy ){
		this.updateBy = updateBy;
	}
	
	public String getUserType(){
		return  userType;
	}
	public void setUserType(String userType ){
		this.userType = userType;
	}
	
	public Date getCreateDate(){
		return  createDate;
	}
	public void setCreateDate(Date createDate ){
		this.createDate = createDate;
	}
	
	public Date getLoginDate(){
		return  loginDate;
	}
	public void setLoginDate(Date loginDate ){
		this.loginDate = loginDate;
	}
	
	public Date getUpdateDate(){
		return  updateDate;
	}
	public void setUpdateDate(Date updateDate ){
		this.updateDate = updateDate;
	}
	
	
	/**
	 * 返回用户名
	 */
	@Override
	public Object getPrincipal() {
		return this.getLoginName();
	}

	/**
	 * 返回密码
	 */
	@Override
	public Object getCredentials() {
		return this.getPassword();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUser other = (SysUser) obj;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		return true;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

}
