findByParentIdsLike
===

	select #use("cols")# from sys_office  where del_flag=0 
	and parent_ids like 	#parentIds+'%'#

findById
===

SELECT a.*,b.name parentName,c.name areaName
FROM sys_office a
    LEFT JOIN sys_office b 
        ON a.parent_id=b.id
        LEFT JOIN sys_area c 
        ON a.area_id=c.id where a.id=#id#
        
findByType
===
* 查询所有的区域
	
	select a.*,b.name as areaName from sys_office a LEFT JOIN sys_area b 
        ON a.area_id=b.id    where a.del_flag=0
        @if(!isEmpty(type)){
	 and a.`type`=#type#
	@}
         order by  a.parent_ids
sample
===
* 注释

	select #use("cols")# from sys_office where #use("condition")#

cols
===

	id,parent_id,parent_ids,name,sort,area_id,code,type,grade,address,zip_code,master,phone,fax,email,USEABLE,PRIMARY_PERSON,DEPUTY_PERSON,create_by,create_date,update_by,update_date,remarks,del_flag

updateSample
===

	`id`=#id#,`parent_id`=#parentId#,`parent_ids`=#parentIds#,`name`=#name#,`sort`=#sort#,`area_id`=#areaId#,`code`=#code#,`type`=#type#,`grade`=#grade#,`address`=#address#,`zip_code`=#zipCode#,`master`=#master#,`phone`=#phone#,`fax`=#fax#,`email`=#email#,`USEABLE`=#useable#,`PRIMARY_PERSON`=#primaryPerson#,`DEPUTY_PERSON`=#deputyPerson#,`create_by`=#createBy#,`create_date`=#createDate#,`update_by`=#updateBy#,`update_date`=#updateDate#,`remarks`=#remarks#,`del_flag`=#delFlag#

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
	@if(!isEmpty(areaId)){
	 and `area_id`=#areaId#
	@}
	@if(!isEmpty(code)){
	 and `code`=#code#
	@}
	@if(!isEmpty(type)){
	 and `type`=#type#
	@}
	@if(!isEmpty(grade)){
	 and `grade`=#grade#
	@}
	@if(!isEmpty(address)){
	 and `address`=#address#
	@}
	@if(!isEmpty(zipCode)){
	 and `zip_code`=#zipCode#
	@}
	@if(!isEmpty(master)){
	 and `master`=#master#
	@}
	@if(!isEmpty(phone)){
	 and `phone`=#phone#
	@}
	@if(!isEmpty(fax)){
	 and `fax`=#fax#
	@}
	@if(!isEmpty(email)){
	 and `email`=#email#
	@}
	@if(!isEmpty(useable)){
	 and `USEABLE`=#useable#
	@}
	@if(!isEmpty(primaryPerson)){
	 and `PRIMARY_PERSON`=#primaryPerson#
	@}
	@if(!isEmpty(deputyPerson)){
	 and `DEPUTY_PERSON`=#deputyPerson#
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
	
