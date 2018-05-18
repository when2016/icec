package org.icec.gen.core;

import java.io.FileWriter;
import java.io.IOException;

import org.beetl.core.Template;
import org.beetl.sql.core.SQLManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MdCodeGen {
	private static Logger logger=LoggerFactory.getLogger(MdCodeGen.class);
	String CR = System.getProperty("line.separator");
	public static String serviceTemplate = "";
	static {
		serviceTemplate = GenConfig.getTemplate("/org/icec/gen/core/md.btl");
	}
	public void genCode(SQLManager sqlManager,String table,FileWriter writer)throws IOException {
		Template template = SourceGen.gt.getTemplate(serviceTemplate);
		
		String condition = sqlManager.getDbStyle().genCondition(table);
		String updateSample=sqlManager.getDbStyle().genColAssignPropertyAbsolute(table);
		String cols=sqlManager.getDbStyle().genColumnList(table);
		template.binding("table",table);
		template.binding("condition",condition);
		template.binding("updateSample",updateSample);
		template.binding("cols",cols);
	 
		String mapperCode = template.render();
		writer.write(mapperCode);
		writer.flush();
	}
}
