findPermissionsByUserId
===
* 根据用户id查权限标识
	
	select DISTINCT permission from sys_menu a,sys_role_menu b ,sys_user_role c ,sys_role d
	where c.role_id=d.id and d.useable=1 and d.del_flag=0 and  a.id=b.menu_id and b.role_id=c.role_id and c.user_id=#userId# 
	and a.del_flag=0  
	
findMenuByUserId
===
* 根据用户id查菜单,类型是菜单,并是显示的
	
	select DISTINCT a.*  from sys_menu a,sys_role_menu b ,sys_user_role c,sys_role d 
	where c.role_id=d.id and d.useable=1 and d.del_flag=0 and  a.id=b.menu_id and b.role_id=c.role_id and c.user_id=#userId# 
	and a.del_flag=0  and type=1 and a.is_show=1 order by a.sort ,a.id 

findByRoleId
===
* 根据角色id查菜单

	select #use("cols")# from sys_menu a,sys_role_menu b 
	where a.id=b.menu_id and b.role_id=#roleId# and a.del_flag=0 

findById
===

	SELECT a.*,b.name parentName
	FROM sys_menu a
    LEFT JOIN sys_menu b 
        ON a.parent_id=b.id where a.id=#id#

findByParentIdsLike
===

	select #use("cols")# from sys_menu  where del_flag=0 
	and parent_ids like 	#parentIds+'%'#
	
query
===
* 角色查询
	
	select  *   from sys_menu  where del_flag=0   order by    parent_ids,sort 

sample
===
* 注释

	select #use("cols")# from sys_menu where #use("condition")#

cols
===

	id,parent_id,parent_ids,name,sort,href,target,icon,is_show,type,permission,create_by,create_date,update_by,update_date,remarks,del_flag

updateSample
===

	`id`=#id#,`parent_id`=#parentId#,`parent_ids`=#parentIds#,`name`=#name#,`sort`=#sort#,`href`=#href#,`target`=#target#,`icon`=#icon#,`is_show`=#isShow#,`type`=#type#,`permission`=#permission#,`create_by`=#createBy#,`create_date`=#createDate#,`update_by`=#updateBy#,`update_date`=#updateDate#,`remarks`=#remarks#,`del_flag`=#delFlag#

condition
===

	1 = 1  
	@if(!isEmpty(parentId)){
	 and `parent_id`=#parentId#
	@}
	@if(!isEmpty(parentIds)){
	 and `parent_ids`=#parentIds#
	@}
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(sort)){
	 and `sort`=#sort#
	@}
	@if(!isEmpty(href)){
	 and `href`=#href#
	@}
	@if(!isEmpty(target)){
	 and `target`=#target#
	@}
	@if(!isEmpty(icon)){
	 and `icon`=#icon#
	@}
	@if(!isEmpty(isShow)){
	 and `is_show`=#isShow#
	@}
	@if(!isEmpty(type)){
	 and `type`=#type#
	@}
	@if(!isEmpty(permission)){
	 and `permission`=#permission#
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
	
