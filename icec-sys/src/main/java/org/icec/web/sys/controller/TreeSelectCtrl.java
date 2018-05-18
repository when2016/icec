package org.icec.web.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TreeSelectCtrl {
	@RequestMapping("sys/treeselect")
	public String treeselect(String url,ModelMap model) {
		model.addAttribute("url", url);
		return "common/treeselect";
	}
}
