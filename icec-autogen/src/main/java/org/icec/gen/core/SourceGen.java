package org.icec.gen.core;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.beetl.sql.core.JavaType;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.ColDesc;
import org.beetl.sql.core.db.MetadataManager;
import org.beetl.sql.core.db.TableDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourceGen {
	private static Logger logger=LoggerFactory.getLogger(SourceGen.class);
	MetadataManager mm;
	SQLManager sm ;
	String table;
	String pkg;
	String srcPath;
	GenConfig config;
	public static String srcHead ="";
	public static String defaultPkg = "com.test";
	static String CR = System.getProperty("line.separator");
	/**
	 * 代码生成的Beetl的GroupTemplate，与BeetSQL 不同
	 */
	public static GroupTemplate gt = null;
	static {
		Configuration conf = null;
		try {
			conf = Configuration.defaultConfiguration();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conf.setStatementStart("@");
		conf.setStatementEnd(null);
		gt = new GroupTemplate(new StringTemplateResourceLoader(),conf);
		
	}
	
	public SourceGen(SQLManager sm,String table,String pkg,String srcPath,GenConfig config){
		this.mm = sm.getMetaDataManager();
		this.sm = sm;
		this.table = table;
		this.pkg = pkg;
		this.srcPath = srcPath;
		this.config = config;
	}
	/**
	 * 生成代码
	 * 
	 */
	public void gen() throws Exception{
		final TableDesc  tableDesc = mm.getTable(table);
		String className = sm.getNc().getClassName(tableDesc.getName());
		String ext = null;
		
		if(config.getBaseClass()!=null){
			ext = config.getBaseClass();
		}
		
		Set<String> cols = tableDesc.getCols();
		List<Map> attrs = new ArrayList<Map>();
		for(String col:cols){
			
			ColDesc desc = tableDesc.getColDesc(col);
			Map attr = new HashMap();
			attr.put("comment", desc.remark);
			String attrName = sm.getNc().getPropertyName(null, desc.colName);
			attr.put("name", attrName);
			attr.put("methodName", getMethodName(attrName));
			
			attr.put("type", desc.remark);
			
			String type = JavaType.getType(desc.sqlType, desc.size, desc.digit);
			if(config.isPreferBigDecimal()&&type.equals("Double")){
				type = "BigDecimal";
			}		
			if(config.isPreferDate()&&type.equals("Timestamp")){
				type ="Date";
			}
			
			attr.put("type", type);
			attr.put("desc", desc);
			attrs.add(attr);
		}
		
		if(config.getPropertyOrder()==config.ORDER_BY_TYPE) {
			// 主键总是拍在前面，int类型也排在前面，剩下的按照字母顺序排
			Collections.sort(attrs,new Comparator<Map>() {

				@Override
				public int compare(Map o1, Map o2) {
					ColDesc desc1  = (ColDesc)o1.get("desc");
					ColDesc desc2  = (ColDesc)o2.get("desc");
					int score1 = score(desc1);
					int score2 = score(desc2);
					if(score1==score2){
						return desc1.colName.compareTo(desc2.colName);
					}else{
						return score2-score1;
					}
					
						
				}
				
				private int score(ColDesc desc){
					if(tableDesc.getIdNames().contains(desc.colName)){
						return 99;
					}else if(JavaType.isInteger(desc.sqlType)){
						return 9;
					}else if(JavaType.isDateType(desc.sqlType)){
						return  -9;
					}else{
						return  0;
					}
				}

				
			});
		}
		
		Template template = gt.getTemplate(config.template);
		template.binding("attrs", attrs);
		template.binding("className", className);
		template.binding("ext", ext);
		template.binding("package", pkg);
		template.binding("imports", srcHead);
		template.binding("comment", tableDesc.getRemark());
		String code = template.render();
		if(config.isDisplay()){
			System.out.println(code);
		}else{
			saveSourceFile(srcPath,pkg,className,code);
		}
		logger.info("pojo succeed！");
		for(CodeGen codeGen:config.codeGens){
			codeGen.genCode(srcPath,pkg, className, tableDesc, config,config.isDisplay());
		}
	
		
	
		
	}
	
	public static  void saveSourceFile(String srcPath,String pkg,String className,String content) throws IOException{
		String file = srcPath+File.separator+pkg.replace('.',File.separatorChar);
		File f  = new File(file);
		f.mkdirs();
		File target = new File(file,className+".java");
		FileWriter writer = new FileWriter(target);
		writer.write(content);;
		writer.close();
	}
	
	private String getMethodName(String name){
		if(name.length()==1){
			return name.toUpperCase();
		}
		char ch1 = name.charAt(0);
		char ch2 = name.charAt(1);
		if(Character.isLowerCase(ch1)&&Character.isUpperCase(ch2)){
			//aUname---> getaUname();
			return name;
		}else if(Character.isUpperCase(ch1)&&Character.isUpperCase(ch2)){
			//ULR --> getURL();
			return name ;
		}else{
			//general  name --> getName()
			char upper = Character.toUpperCase(ch1);
			return upper+name.substring(1);
		}
	}
}
