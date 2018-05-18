package org.icec.gen.core;

import java.io.IOException;

import org.beetl.core.Template;
import org.beetl.sql.core.db.TableDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapperCodeGen implements CodeGen{
	private static Logger logger=LoggerFactory.getLogger(MapperCodeGen.class);
	String CR = System.getProperty("line.separator");
	String pkg = null;
	public MapperCodeGen(){
		
	}
	 
	public MapperCodeGen(String basepkg){
		this.pkg = basepkg+".dao";
	}
	public static String mapperTemplate="";
	static {
		mapperTemplate = GenConfig.getTemplate("/org/icec/gen/core/mapper.btl");
	}
	
	@Override
	public void genCode(String project,String entityPkg, String entityClass, TableDesc tableDesc, GenConfig config,
			boolean isDisplay) {
		if(pkg==null){
			pkg = entityPkg;
		}
		Template template = SourceGen.gt.getTemplate(mapperTemplate);
		String mapperClass = entityClass+"Dao";
		template.binding("className", mapperClass);
		template.binding("package",pkg);
		template.binding("entityClass", entityClass);
		
		String mapperHead = "import "+entityPkg+"."+entityClass+";"+CR;
		template.binding("imports", mapperHead);
		String mapperCode = template.render();
		if(isDisplay){
			System.out.println();
			System.out.println(mapperCode);
		}else{
			try {
				SourceGen.saveSourceFile(project, pkg, mapperClass, mapperCode);
			} catch (IOException e) {
				throw new RuntimeException("mapper代码生成失败",e);
			}
		}
		logger.info("mapper succeed！");
	}

}
