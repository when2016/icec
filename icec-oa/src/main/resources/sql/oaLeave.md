pageQuery
===
* 分页查询
 
	select
	@pageTag(){
	#use("cols")#  
	@} 
	from  oa_leave
	where #use("condition")#

sample
===
* 注释

	select #use("cols")# from oa_leave where #use("condition")#

cols
===

	id,processid,title,user_id,leave_type,start_time,end_time,reason,time_used,apply_time

updateSample
===

	`id`=#id#,`processid`=#processid#,`title`=#title#,`user_id`=#userId#,`leave_type`=#leaveType#,`start_time`=#startTime#,`end_time`=#endTime#,`reason`=#reason#,`time_used`=#timeUsed#,`apply_time`=#applyTime#

condition
===

	 1 = 1  
@if(!isEmpty(processid)){
 and `processid`=#processid#
@}
@if(!isEmpty(title)){
 and `title`=#title#
@}
@if(!isEmpty(userId)){
 and `user_id`=#userId#
@}
@if(!isEmpty(leaveType)){
 and `leave_type`=#leaveType#
@}
@if(!isEmpty(startTime)){
 and `start_time`=#startTime#
@}
@if(!isEmpty(endTime)){
 and `end_time`=#endTime#
@}
@if(!isEmpty(reason)){
 and `reason`=#reason#
@}
@if(!isEmpty(timeUsed)){
 and `time_used`=#timeUsed#
@}
@if(!isEmpty(applyTime)){
 and `apply_time`=#applyTime#
@}
