getDictItemsByTypeValue
===

	select a.* from sys_dict a , sys_dict b where a.del_flag=0 and a.parent_id=b.id and b.type=#type# and a.value=#value# order by sort

getDictItemsByType
===
	
	select a.* from sys_dict a , sys_dict b where a.del_flag=0 and a.parent_id=b.id and b.type=#type# order by sort 


getDictItems
===

	select * from sys_dict  where del_flag=0 and parent_id=#parentId# order by sort 

queryDict
===

	select
	@pageTag(){
	* 
	@}
	from sys_dict 
	where del_flag=0 and parent_id=0
	@if(!isEmpty(type)){
	 and `type` like #'%'+type+'%'#
	@}
	@if(!isEmpty(description)){
	 and `description` like #'%'+description+'%'#
	@}
	order by sort 

sample
===
* 注释

	select #use("cols")# from sys_dict where #use("condition")#

cols
===

	id,value,label,type,description,sort,parent_id,create_by,create_date,update_by,update_date,remarks,del_flag

updateSample
===

	`id`=#id#,`value`=#value#,`label`=#label#,`type`=#type#,`description`=#description#,`sort`=#sort#,`parent_id`=#parentId#,`create_by`=#createBy#,`create_date`=#createDate#,`update_by`=#updateBy#,`update_date`=#updateDate#,`remarks`=#remarks#,`del_flag`=#delFlag#

condition
===

	1 = 1  
	@if(!isEmpty(value)){
	 and `value`=#value#
	@}
	@if(!isEmpty(label)){
	 and `label`=#label#
	@}
	@if(!isEmpty(type)){
	 and `type`=#type#
	@}
	@if(!isEmpty(description)){
	 and `description`=#description#
	@}
	@if(!isEmpty(sort)){
	 and `sort`=#sort#
	@}
	@if(!isEmpty(parentId)){
	 and `parent_id`=#parentId#
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
	
