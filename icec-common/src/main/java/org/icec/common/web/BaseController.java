package org.icec.common.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.icec.common.base.tips.DataTip;
import org.icec.common.base.tips.ErrorTip;
import org.icec.common.base.tips.SuccessTip;
import org.icec.common.base.tips.Tip;
import org.icec.common.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	@Value("${default_page_size}")  
	private int default_page_size=10;

	public static final Tip  SUCC=new SuccessTip();
	public static final Tip  FAIL=new ErrorTip("操作失败");
	
	public SuccessTip succ(String msg) {
		return new SuccessTip(msg);
	}
	public ErrorTip fail(String msg) {
		return new ErrorTip(msg);
	}
	public Tip data(Object data) {
		return new DataTip(data);
	}
	public int getDefault_page_size() {
		return default_page_size;
	}

	public void setDefault_page_size(int default_page_size) {
		this.default_page_size = default_page_size;
	}
	
	
	/**
     * 返回前台文件流
     *
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
     }
}
