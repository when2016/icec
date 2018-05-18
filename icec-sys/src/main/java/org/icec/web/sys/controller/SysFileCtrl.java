package org.icec.web.sys.controller;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.icec.common.base.tips.SuccessTip;
import org.icec.common.base.tips.Tip;
import org.icec.common.web.BaseController;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysFile;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SysFileCtrl extends BaseController{
	Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private SysFileService sysFileService;
	@RequiresUser
	@PostMapping("/sys/singleUpload")
	@ResponseBody
	public Tip upload(String type,@RequestParam("file") MultipartFile multiFile,@CurrentUser SysUser user) {
		 if (multiFile.isEmpty()) {  
			 return FAIL;
		 }
		
		Long fileId= sysFileService.saveUploadFile(type, multiFile, user);
		if(fileId>0) {
				SuccessTip tip=	succ("上传成功");tip.setData(fileId);
		 return tip;
		}else {
			return FAIL;
		}
	}
	@RequiresUser
	@GetMapping("/sys/showFile/{fid}")
	@ResponseBody
	public ResponseEntity<InputStreamResource> showImg(@PathVariable("fid") Integer fileId,HttpServletResponse response) {
		SysFile sysFile=sysFileService.getFile(fileId);
		try {
			Resource resource=  sysFileService.readFile(sysFile.getFileUrl());
			 HttpHeaders headers = new HttpHeaders();
	            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	            headers.add("Content-Disposition", "inline;filename=\"" + URLEncoder.encode(sysFile.getFileName()) + "\"");
	            headers.add("Pragma", "no-cache");
	            headers.add("Expires", "0");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType(sysFile.getFileType()))
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (IOException e) {
            logger.error("查询附件并呈现--异常", e);
          
            HttpHeaders falseHeaders = new HttpHeaders();
            falseHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
            falseHeaders.add("Content-Disposition", "inline;filename=\"" + "404.jpg" + "\"");
            falseHeaders.add("Pragma", "no-cache");
            falseHeaders.add("Expires", "0");
            return ResponseEntity
                    .ok()
                    .headers(falseHeaders)
                    .contentType(MediaType.parseMediaType("image/jpeg"))
                    .body(null);
        }  
	}
	@RequiresUser
	@GetMapping("/sys/downloadFile/{fid}")
	public void download(@PathVariable("fid") Integer fileId,HttpServletResponse response) {
		SysFile sysFile=sysFileService.getFile(fileId);
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=" +  URLEncoder.encode(sysFile.getFileName()) );
		OutputStream os = null; //输出流
		try {
			os = response.getOutputStream();
			Resource resource=  sysFileService.readFile(sysFile.getFileUrl());
			IOUtils.copy(resource.getInputStream(), os);
			os.flush();
			
		} catch (IOException e) {
			logger.error("下载文件异常",e);
		}finally {
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("关闭文件流异常",e);
				}
			}
		}
	}
	
}
