package org.icec.web.sys.controller;

import java.util.Properties;

import org.icec.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemInfoCtrl extends BaseController{

	@RequestMapping("sys/environment")
	public String environment(Model modelMap) {
		Properties props = System.getProperties();
		Runtime runtime = Runtime.getRuntime();
		long freeMemory = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		long maxMemory = runtime.maxMemory();
		long usedMemory = totalMemory - freeMemory;
		long useableMemory = maxMemory - totalMemory + freeMemory;
		int div = 1000;
		double freeMemoryMB = ((double) freeMemory) / div / div;
		double totalMemoryMB = ((double) totalMemory) / div / div;
		double usedMemoryMB = ((double) usedMemory) / div / div;
		double maxMemoryMB = ((double) maxMemory) / div / div;
		double useableMemoryMB = ((double) useableMemory) / div / div;
		modelMap.addAttribute("props", props);
		modelMap.addAttribute("maxMemoryMB", maxMemoryMB);
		modelMap.addAttribute("usedMemoryMB", usedMemoryMB);
		modelMap.addAttribute("useableMemoryMB", useableMemoryMB);
		modelMap.addAttribute("totalMemoryMB", totalMemoryMB);
		modelMap.addAttribute("freeMemoryMB", freeMemoryMB);
		return "sys/environment";
	}
}
