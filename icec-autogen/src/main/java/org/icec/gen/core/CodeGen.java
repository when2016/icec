package org.icec.gen.core;

import org.beetl.sql.core.db.TableDesc;

public interface CodeGen {
	public void genCode(String project,String entityPkg,String entityClass,TableDesc  tableDesc,GenConfig config,boolean isDisplay);
}

