pageQuery
===
 
 	select
 	@pageTag(){
 	* 
 	@}
 	from sys_log 
 	where  1=1
 	@if(!isEmpty(createBy)){
	 and `create_by` like #'%'+createBy+'%'#
	@}
 	@if(!isEmpty(type)){
	 and `type` = #type#
	@}
 	order by id 
 

sample
===
* 注释

	select #use("cols")# from sys_log where #use("condition")#

cols
===

	id,type,title,create_by,create_date,remote_addr,user_agent,request_uri,method,lose_time,params,exception
