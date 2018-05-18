findByParentIdsLike
===

	select #use("cols")# from sys_area  where del_flag=0 
	and parent_ids like 	#parentIds+'%'#

findById
===

	SELECT a.*,b.name parentName
	FROM sys_area a
    LEFT JOIN sys_area b 
        ON a.parent_id=b.id where a.id=#id#

query
===
* 查询所有的区域
	
	select #use("cols")# from sys_area  where del_flag=0 order by parent_ids

sample
===
* 注释

	select #use("cols")# from sys_area where #use("condition")#

cols
===

	id,parent_id,parent_ids,name,sort,code,type,create_by,create_date,update_by,update_date,remarks,del_flag

updateSample
===

	`id`=#id#,`parent_id`=#parentId#,`parent_ids`=#parentIds#,`name`=#name#,`sort`=#sort#,`code`=#code#,`type`=#type#,`create_by`=#createBy#,`create_date`=#createDate#,`update_by`=#updateBy#,`update_date`=#updateDate#,`remarks`=#remarks#,`del_flag`=#delFlag#

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
	@if(!isEmpty(code)){
	 and `code`=#code#
	@}
	@if(!isEmpty(type)){
	 and `type`=#type#
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
	
