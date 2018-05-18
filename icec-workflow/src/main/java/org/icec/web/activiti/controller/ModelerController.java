package org.icec.web.activiti.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.icec.common.base.tips.Tip;
import org.icec.common.web.BaseController;
import org.icec.web.activiti.ToWeb;
import org.icec.web.activiti.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 模型管理
 */
@Controller
@RequestMapping("workflow/models")
public class ModelerController extends BaseController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
   private ModelService modelService;
    @RequiresPermissions("act:process:edit")
    @GetMapping("addModel")
    public String addModel() {
    	return "workflow/modelAdd";
    }
    /**
     * 新建一个空模型
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresPermissions("act:process:edit")
    @PostMapping("newModel")
    @ResponseBody
    public Tip newModel(String name, String key, String description, String category) throws UnsupportedEncodingException {
    	Model model=modelService.create(name, key, description, category);
        return data(model.getId());
        		//ToWeb.buildResult().redirectUrl("/modeler.html?modelId="+model.getId());
    }


    /**
     * 发布模型为流程定义
     * @param id
     * @return
     * @throws Exception
     */
    @RequiresPermissions("act:process:edit")
    @PostMapping("deployment/{id}")
    @ResponseBody
    public Tip deploy(@PathVariable("id")String id) throws Exception {
    	try {
    		modelService.deploy(id);
    	}catch(ActivitiException e) {
    		return FAIL;
    	}

        return SUCC;
    }
    @GetMapping("getOne/{id}")
    @ResponseBody
    public Object getOne(@PathVariable("id") String id) {
        Model model = repositoryService.createModelQuery().modelId(id).singleResult();
        return ToWeb.buildResult().setObjData(model);
    }
    /**
	 * 进入查询界面
	 * @return
	 */
    @RequiresPermissions("act:process:edit")
	@RequestMapping("list")
	public String list() {
		return "workflow/modelList";
	}
    @RequiresPermissions("act:process:edit")
    @PostMapping("getList")
    @ResponseBody
    public ToWeb.Rows getList(@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer rowSize, @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer page, String category) {
    	ModelQuery modelQuery = repositoryService.createModelQuery().latestVersion().orderByModelName().desc();
    	if (StringUtils.isNotEmpty(category)){
			modelQuery.modelCategory(category);
		}
    	List<Model> list = modelQuery.listPage(rowSize * (page - 1)
                , rowSize);
        long count = modelQuery.count();

        return  ToWeb.Rows.buildRows().setCurrent(page)
                        .setTotalPages((int) (count/rowSize+1))
                        .setTotalRows(count)
                        .setList(list)
                        .setRowSize(rowSize);
        
    }
    @RequiresPermissions("act:process:edit")
    @PostMapping("delete/{id}")
    @ResponseBody
    public Tip delete(@PathVariable("id")String id){
        repositoryService.deleteModel(id);
        return SUCC;
    }

   
}
