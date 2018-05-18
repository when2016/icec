sample
===
* 注释

	select #use("cols")# from sys_file where #use("condition")#

cols
===

	id,file_name,file_size,file_type,file_url,create_time,create_by,update_time,update_by,busi_type,busi_no,memo,state,deleted

updateSample
===

	`id`=#id#,`file_name`=#fileName#,`file_size`=#fileSize#,`file_type`=#fileType#,`file_url`=#fileUrl#,`create_time`=#createTime#,`create_by`=#createBy#,`update_time`=#updateTime#,`update_by`=#updateBy#,`busi_type`=#busiType#,`busi_no`=#busiNo#,`memo`=#memo#,`state`=#state#,`deleted`=#deleted#

condition
===

	1 = 1  
	@if(!isEmpty(fileName)){
	 and `file_name`=#fileName#
	@}
	@if(!isEmpty(fileSize)){
	 and `file_size`=#fileSize#
	@}
	@if(!isEmpty(fileType)){
	 and `file_type`=#fileType#
	@}
	@if(!isEmpty(fileUrl)){
	 and `file_url`=#fileUrl#
	@}
	@if(!isEmpty(createTime)){
	 and `create_time`=#createTime#
	@}
	@if(!isEmpty(createBy)){
	 and `create_by`=#createBy#
	@}
	@if(!isEmpty(updateTime)){
	 and `update_time`=#updateTime#
	@}
	@if(!isEmpty(updateBy)){
	 and `update_by`=#updateBy#
	@}
	@if(!isEmpty(busiType)){
	 and `busi_type`=#busiType#
	@}
	@if(!isEmpty(busiNo)){
	 and `busi_no`=#busiNo#
	@}
	@if(!isEmpty(memo)){
	 and `memo`=#memo#
	@}
	@if(!isEmpty(state)){
	 and `state`=#state#
	@}
	@if(!isEmpty(deleted)){
	 and `deleted`=#deleted#
	@}
	
