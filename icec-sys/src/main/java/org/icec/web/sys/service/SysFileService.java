package org.icec.web.sys.service;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;
import org.icec.web.sys.model.SysFile;
import org.icec.web.sys.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.icec.common.exception.IcecException;
import org.icec.web.sys.dao.SysFileDao;

/*
* 
* gen by icec  2017-12-08
*/
@Service
public class SysFileService   {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private SysFileDao  sysFileDao ;
	private final ResourceLoader resourceLoader; 
	@Autowired  
    public SysFileService(ResourceLoader resourceLoader) {  
        this.resourceLoader = resourceLoader;  
    } 
	@Value("${icec.upload.path}")
	private String filePath;
	
	/**
	 * 获取上传的文件
	 * @param path
	 * @return
	 */
	public Resource readFile(String path) {
		if(StringUtils.isEmpty(filePath)) {
			filePath="upload";
		}
		return  resourceLoader.getResource("file:" + Paths.get(filePath, path));
	}
	/**
	 * 保存上传的文件
	 * @param type
	 * @param multiFile
	 * @param user
	 * @return
	 */
	public Long saveUploadFile(String type,MultipartFile multiFile,SysUser user) {
		if(multiFile==null)return 0l;
		
		String filename = multiFile.getOriginalFilename(); // 得到上传时的文件名
		 logger.debug("上传的文件名称：{}", filename);
		String fileExtName = "";
		int index=filename.lastIndexOf('.');
		if(	index>0){
			fileExtName=filename.substring(index+1);
		}

		SysFile sysfile = new SysFile();
		if(user!=null) {
			sysfile.setCreateBy(user.getId());
		}
		sysfile.setCreateTime(new Date());
		sysfile.setDeleted(0);
		sysfile.setFileName(filename);
		sysfile.setFileSize(multiFile.getSize());
		sysfile.setFileType(multiFile.getContentType());
		sysfile.setState(0);
		sysfile.setBusiType("userPhoto");
		String newfilename= makeFileName(fileExtName);
		if(StringUtils.isEmpty(filePath)) {
			filePath="upload";
		}
		Path typepath=Paths.get(filePath, type);
		Path path=Paths.get(filePath, type,newfilename);
		try {
			// 文件读取并写入
			 try {
				 if(!Files.isDirectory(typepath)) {
					 Files.createDirectories(typepath); 
				 }
		        } catch (IOException e) {
		            e.printStackTrace();
		            throw new IcecException("头像上传失败");
		        }
			Files.copy(multiFile.getInputStream(),path);
			sysfile.setFileUrl("/" + type + "/"+newfilename);
			Long fileId= saveFileRecord(sysfile);
			
			return fileId;
		} catch (Exception e) {
			logger.error("上传图片异常", e);
			throw new IcecException("头像上传失败");
		}
	}
	
	
	public SysFile getFile(Integer fileId) {
		return sysFileDao.single(fileId);
	}
	
	/**
	 * 上传附件后，保存数据库记录
	 * @param createby
	 * @param sysFile
	 * @return
	 */
	public Long saveFileRecord(SysFile sysFile){
		sysFile.setCreateTime(new Date());
		return sysFileDao.insertReturnKey(sysFile).getLong();
	}
	 
	
	public List<SysFile> getFileByBusino(String busino){
		 return getFileByBusino(busino, null);
	}
	/**
	 * 根据类型，业务号查询附件
	 * @param busino
	 * @param busitype
	 * @return
	 */
	public List<SysFile> getFileByBusino(String busino,String busitype){
		 return sysFileDao.queryFile(busino, busitype);
	}
	
	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @param filename
	 *            文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String fileExtName) {
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		if(fileExtName==null){
			return UUID.randomUUID().toString().replace("-", "");
		}else{
			return UUID.randomUUID().toString().replace("-", "") + "." + fileExtName;
		}
	}
}
