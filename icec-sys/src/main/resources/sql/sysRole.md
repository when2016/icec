getRoleItems
===
     select 
     u.id,u.name 
     from 
     sys_user u left join sys_user_role ur on ur.user_id=u.id 
     where ur.role_id=#roleTypeId# group by u.id
queryUnselect
===
      select 
      u.id,u.name
      from 
      sys_user u
      where 
      id not in ( select user_id from sys_user_role where role_id  = #roleTypeId#)
      
saveAll
===   
   
      insert into sys_user_role(user_id,role_id) VALUES(#userId#,#roleTypeId#);
      
deleteRoleMenuByRoleId
===

	delete from 	sys_role_menu where role_id=#roleId#

insertRoleMenu
===

	insert into sys_role_menu values(#roleId#,#menuId#)

findRoleByUserId
===

	select #use("cols")# from sys_role a,sys_user_role b 
	where a.id=b.role_id and b.user_id=#userId#
	
insertUserRole
===
	
	insert into sys_user_role values(#userId#,#roleId#)
	
deleteUserRoleByUserId
===

	delete from 	sys_user_role where user_id=#userId#
	
deleteUserRoleByUserIdAndRoleId
===
	
	delete from 	sys_user_role where user_id=#userId# and role_id=#roleId#	
		

findById
===

	select a.*,b.name officeName 	from sys_role a
	LEFT JOIN sys_office b 
        ON a.officeId=b.id 	where del_flag=0  and id=#id#

queryRole
===
* 角色查询
	
	select
 	@pageTag(){
 	* 
 	@}
 	from sys_role 
 	where del_flag=0
 	@if(!isEmpty(name)){
	 and `name` like #'%'+name+'%'#
	@}
 	order by id 

sample
===
* 注释

	select #use("cols")# from sys_role where #use("condition")#

cols
===

	id,office_id,name,enname,role_type,data_scope,is_sys,useable,create_by,create_date,update_by,update_date,remarks,del_flag

updateSample
===

	`id`=#id#,`office_id`=#officeId#,`name`=#name#,`enname`=#enname#,`role_type`=#roleType#,`data_scope`=#dataScope#,`is_sys`=#isSys#,`useable`=#useable#,`create_by`=#createBy#,`create_date`=#createDate#,`update_by`=#updateBy#,`update_date`=#updateDate#,`remarks`=#remarks#,`del_flag`=#delFlag#

condition
===

	1 = 1  
	@if(!isEmpty(officeId)){
	 and `office_id`=#officeId#
	@}
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(enname)){
	 and `enname`=#enname#
	@}
	@if(!isEmpty(roleType)){
	 and `role_type`=#roleType#
	@}
	@if(!isEmpty(dataScope)){
	 and `data_scope`=#dataScope#
	@}
	@if(!isEmpty(isSys)){
	 and `is_sys`=#isSys#
	@}
	@if(!isEmpty(useable)){
	 and `useable`=#useable#
	@}
	@if(!isEmpty(createBy)){
	 and `create_by`=#createBy#
	@}
	@if(!isEmpty(createDate)){
	 and `create_date`=#createDate#
	@}
	@if(!isEmpty(updateBy)){
	 and `update_by`=#updateBy#
	@}
	@if(!isEmpty(updateDate)){
	 and `update_date`=#updateDate#
	@}
	@if(!isEmpty(remarks)){
	 and `remarks`=#remarks#
	@}
	@if(!isEmpty(delFlag)){
	 and `del_flag`=#delFlag#
	@}
	
