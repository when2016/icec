package org.icec.gen.core;

import java.io.File;
import java.io.FileWriter;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.core.kit.StringKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoGen {
	private static Logger logger=LoggerFactory.getLogger(AutoGen.class);
	/**
	 * 一步完成所有代码生成
	 */
	public static void go(SQLManager sqlManager, String project, String basepkg, String table) {
		GenConfig config = new GenConfig();
		config.codeGens.add(new MapperCodeGen(basepkg));
		config.codeGens.add(new ServiceCodeGen(basepkg));
		String srcPath = getPath(project,"/src/main/java");
		SourceGen gen = new SourceGen(sqlManager, table, basepkg + ".model", srcPath, config);
		try {
		 	gen.gen();
		 	genSQLFile(sqlManager,table,project);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("自动生成代码失败",e);
		}

	}

	public static void genPojo(SQLManager sqlManager, String project, String basepkg, String table) {
		GenConfig config = new GenConfig();
		String srcPath = getPath(project,"/src/main/java");
		SourceGen gen = new SourceGen(sqlManager, table, basepkg + ".model", srcPath, config);
		try {
			gen.gen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void genPojoDao(SQLManager sqlManager, String project, String basepkg, String table) {
		GenConfig config = new GenConfig();
		config.codeGens.add(new MapperCodeGen(basepkg));
		String srcPath = getPath(project,"/src/main/java");
		SourceGen gen = new SourceGen(sqlManager, table, basepkg + ".model", srcPath, config);
		try {
			gen.gen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void genPojoDaoService(SQLManager sqlManager, String project, String basepkg, String table) {
		GenConfig config = new GenConfig();
		config.codeGens.add(new MapperCodeGen(basepkg));
		config.codeGens.add(new ServiceCodeGen(basepkg));
		String srcPath = GenKit.getJavaSRCPath();
		SourceGen gen = new SourceGen(sqlManager, table, basepkg + ".model", srcPath, config);
		try {
			gen.gen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static String getPath(String project,String relativeToSrc) {
        String srcPath;
        String userDir = System.getProperty("user.dir");
        if (userDir == null) {
            throw new NullPointerException("用户目录未找到");
        }
        File src = new File(userDir);
       String parent= src.getParent();
        
        File resSrc = new File(parent+"/"+project, relativeToSrc);
        if (resSrc.exists()) {
            srcPath = resSrc.toString();
        } else {
            srcPath = src.toString();
        }
        return srcPath;
    }
	/**
	 * 生成sql文件
	 * @param sqlManager
	 * @param table
	 * @throws Exception
	 */
	private static  void genSQLFile(SQLManager sqlManager ,String table,String project) throws Exception {
		String path = "/sql";
		String srcPath = getPath(project,"/src/main/resources");
		String fileName = StringKit.toLowerCaseFirstOne(sqlManager.getNc().getClassName(table));
		String target = srcPath +  path + "/" + fileName + ".md";
		File mdfile=new   File(target);
		if(mdfile.exists()) {
			logger.info("文件已存在，不生成！");
		}else {
			File sqlfile=new   File(srcPath+path);
			if(!sqlfile.exists()) {
				sqlfile.mkdirs();	
			}
			FileWriter writer = new FileWriter(mdfile);
			MdCodeGen gen=new MdCodeGen();
			gen.genCode(sqlManager, table, writer);
			writer.close();
			logger.info("gen \"" + table + "\" success at " + target);
		}
	}
}
