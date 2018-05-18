pageQuery
===
* 分页查询
 
	select
	@pageTag(){
	#use("cols")#  
	@} 
	from  sys_global
	where #use("condition")#

sample
===
* 注释

	select #use("cols")# from sys_global where #use("condition")#

cols
===

	id,name,fullname,logo,poweredby,version,create_time

updateSample
===

	`id`=#id#,`name`=#name#,`fullname`=#fullname#,`logo`=#logo#,`poweredby`=#poweredby#,`version`=#version#,`create_time`=#createTime#

condition
===

	 1 = 1  
@if(!isEmpty(name)){
 and `name`=#name#
@}
@if(!isEmpty(fullname)){
 and `fullname`=#fullname#
@}
@if(!isEmpty(logo)){
 and `logo`=#logo#
@}
@if(!isEmpty(poweredby)){
 and `poweredby`=#poweredby#
@}
@if(!isEmpty(version)){
 and `version`=#version#
@}
@if(!isEmpty(createTime)){
 and `create_time`=#createTime#
@}
