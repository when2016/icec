package org.icec.gen;

import java.io.IOException;
import java.util.Properties;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.NameConversion;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.AbstractDBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.icec.gen.core.AutoGen;

public class GenBqlTools {
		
	
	public static void main(String[] args) throws IOException {
		Properties pro=new Properties();
		pro.load(GenBqlTools.class.getResourceAsStream("/jdbc.properties"));
		ConnectionSource source = ConnectionSourceHelper.getSimple(
				(String)pro.get("datasource.driver"),
				(String)pro.get("datasource.url"),
				(String)pro.get("datasource.username"),
				(String)pro.get("datasource.password"));
		// 采用mysql习俗
		AbstractDBStyle style = new MySqlStyle();
		// sql语句放在classpagth的/sql 目录下
		SQLLoader loader = new ClasspathLoader("/sql");
		NameConversion nc = new  UnderlinedNameConversion();
		SQLManager sqlManager = new SQLManager(style,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
		 
		String basepkg="org.icec.web.oa";
		String table="oa_leave";
		AutoGen.go(sqlManager, "icec-oa", basepkg, table);
	}


	
}
