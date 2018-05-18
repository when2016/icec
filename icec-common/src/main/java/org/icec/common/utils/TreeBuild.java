package org.icec.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.icec.common.model.JsTreeData;
import org.icec.common.model.TreeModel;

public class TreeBuild {
	/** 
     * 使用递归方法建树 
     * @param TreeModels 
     * @return 
     */  
    public static List<? extends TreeModel> buildByRecursive(List<? extends TreeModel> treeModels) {
    	 
        List<TreeModel> trees = new ArrayList<TreeModel>();  
        for (TreeModel treeModel : treeModels) {  
            if ((treeModel.getParentId()==0)) {  
                findChildren(treeModel,treeModels,trees);  
            }  
        }  
        return trees;  
    }  
  
    /** 
     * 递归查找子节点 
     * @param TreeModels 
     * @return 
     */  
    private static void findChildren(TreeModel treeModel,List<? extends TreeModel> treeModels,List<TreeModel> trees) {  
    	trees.add(treeModel);
    	boolean flag=false;
    	for (TreeModel it : treeModels) { 
    		
    		 if(treeModel.getId().equals(it.getParentId())) {  
    			 findChildren(it,treeModels,trees); 
    			 flag=true; 
    		 }else {
    			 if(flag==true) {
    				 break;
    			 }
    		 }
        }  
       
    }  
    public static List<JsTreeData> buildJsTree(List<? extends TreeModel> treeModels) {
    	 return buildJsTree( treeModels,0);
    }
    public static List<JsTreeData> buildJsTree(List<? extends TreeModel> treeModels,Integer root) {
    	 List<JsTreeData> trees = new ArrayList<JsTreeData>();  
         for (TreeModel treeModel : treeModels) {  
             if ((treeModel.getParentId()==root)) { 
            	 JsTreeData data=new JsTreeData(treeModel.getId()+"","#", treeModel.getName(), true,treeModel);
            	 trees.add(data);
                 findChildren2jstree(treeModel,treeModels,data,trees);  
             }  
         }  
    	return trees;
    }
    private static void  findChildren2jstree (TreeModel treeModel,List<? extends TreeModel> treeModels,JsTreeData data,List<JsTreeData> trees) {  
    	List<JsTreeData> children = new ArrayList<JsTreeData>(); 
    	for (TreeModel it : treeModels) { 
    		 if(treeModel.getId().equals(it.getParentId())) {  
    			 JsTreeData data2=new JsTreeData(it.getId()+"",it.getParentId()+"",it.getName(),it);
    			 children.add(data2);
    			 trees.add(data2);
    			 findChildren2jstree(it,treeModels,data2,trees); 
    		 } 
        }
    	if(children.size()==0) {
    		data.setLeaf(true);
    	}
    	
    }  
    
    /**
     * 构建树状结构
     * @param treeModels
     * @return
     */
    public static List<TreeModel> buildTree(List<? extends TreeModel> treeModels){
    	List<TreeModel> trees = new ArrayList<TreeModel>();  
        for (TreeModel treeModel : treeModels) {  
            if ((treeModel.getParentId()==0)) {  
            	trees.add(treeModel);
            	addChildren(treeModel,treeModels);  
            }  
        }  
        return trees; 
    }
    
    private static void addChildren(TreeModel treeModel,List<? extends TreeModel> treeModels) {
    	for (TreeModel it : treeModels) { 
   		 if(treeModel.getId().equals(it.getParentId())) {  
   			treeModel.getChildren().add(it);
   			addChildren(it,treeModels); 
   		 } 
       } 
    }
    
}
