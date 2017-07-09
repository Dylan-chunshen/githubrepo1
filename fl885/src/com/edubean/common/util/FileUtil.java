package com.edubean.common.util;

import gov.util.date.DateUtil;
import gov.util.log.LogHelper;
import gov.util.spring.SpringContext;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 附件上传至本地
 * @author Dylan
 * @on 2016-05-11
 */
public class FileUtil {
	private static String parentPath = (String)SpringContext.getInstance().getBean("attachfilePath");
	private static String basePath = "";
	
	private static String getBasePath(){
		if("".equals(basePath)){
			if(parentPath.length()>=2&&':'==parentPath.charAt(1)){
				basePath = parentPath;
			}else {
				try {
					basePath = java.net.URLDecoder.decode(IPUtil.class.getResource("/").getPath(),"utf-8");
					basePath = basePath + File.separator + parentPath;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return basePath;
	}
	
	/**
	 * 获取配置文件所指定的全路径-且创建当前月份的子文件夹
	 * @param request
	 * @return
	 */
	public static String getAbsoluteAttachPath(HttpServletRequest request){
		String path = "";
		createFolder(getBasePath());
		path=basePath+"\\"+DateUtil.getStrDate(new Date(), "yyyy-MM");
		createFolder(path);
		return path;
	}
	
	/**
	 * 获取配置文件所指定的全路径-且创建以childFilePath为名称的子文件夹
	 * @param request
	 * @param 字文件夹名
	 * @return
	 */
	public static String getAbsoluteAttachPath(HttpServletRequest request,String childFilePath){
		String path = "";
		createFolder(getBasePath());
		path=(StringUtils.isNotBlank(childFilePath))?basePath+"/"+childFilePath.trim():basePath;
		createFolder(path);
		path=path.replace("\\", "");
		return path;
	}
	
	public static void createFolder(String path){
		File file = new File(path);
		if (file.exists() == false)
			file.mkdir();
	}
	
	public static String getAttachPath(){
		return DateUtil.getStrDate(new Date(), "yyyyMM");
	}
	
	public static void saveAttach(FileItem fileItem,HttpServletRequest request,String fileName){
		String filePath =getAbsoluteAttachPath(request);
		File file=new File(filePath+"\\"+fileName);
		try {
			fileItem.write(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存文件至硬盘路径
	 * @param file
	 * @param request
	 * @param fileName
	 */
	public static void saveAttach(File file,HttpServletRequest request,String fileName){
		String filePath =getAbsoluteAttachPath(request);
		try {
			if (null != file) {
				FileInputStream fileIn;
				fileIn = new FileInputStream(file);

				FileOutputStream outputStream = new FileOutputStream(
						filePath+"\\"+fileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fileIn.read(buffer)) > 0) {
					outputStream.write(buffer, 0, len);
				}
				fileIn.close();
				outputStream.close();
			}
		} catch (Exception e) {
			LogHelper.getInstance().logError("文件保存失败 ！"+e);
		}
	}
	
	/**
	 * 保存附件文件至服务器硬盘
	 * @param cmf          附件对象
	 * @param request      辅助获取存储路径
	 * @param fileName     硬盘中的文件名称
	 */
	public static void saveAttach(CommonsMultipartFile cmf, HttpServletRequest request,String fileName){
		String filePath =getAbsoluteAttachPath(request);
		try {
			if(null != cmf){
				InputStream fileIn = cmf.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(filePath+"\\"+fileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = fileIn.read(buffer)) > 0){
					outputStream.write(buffer, 0, len);
				}
				fileIn.close();
				outputStream.close();
			}
		} catch (Exception e) {
			LogHelper.getInstance().logError("文件保存失败 ！"+e);
		}
	}
	
	/**
	 * 保存文件至硬盘路径：AbsoluteAttachPath/年月/文件名称
	 * @param file
	 * @param request
	 * @param ny   
	 * @param fileName
	 */
	public static void saveFile(HttpServletRequest request, CommonsMultipartFile cmf, String ny){
		String filePath =getAbsoluteAttachPath(request,ny);
		String fileName = cmf.getFileItem().getName();
		try {
			if(null != cmf){
				InputStream fileIn = cmf.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(filePath+"\\"+fileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = fileIn.read(buffer)) > 0){
					outputStream.write(buffer, 0, len);
				}
				fileIn.close();
				outputStream.close();
			}
		} catch (Exception e) {
			LogHelper.getInstance().logError("文件保存失败 ！"+e);
		}
	}
	
	/**
	 * 保存文件至硬盘路径：AbsoluteAttachPath/年月/文件名称[带有uuid作区别]
	 * @param file
	 * @param request
	 * @param ny   
	 * @param fileName
	 */
	public static void saveFile(HttpServletRequest request, CommonsMultipartFile cmf, String ny, String uuid){
		String filePath =getAbsoluteAttachPath(request,ny);
		String fileName = uuid+FileUtil.getSimpleFileName(cmf.getFileItem().getName());
		try {
			if(null != cmf){
				InputStream fileIn = cmf.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(filePath+"\\"+fileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = fileIn.read(buffer)) > 0){
					outputStream.write(buffer, 0, len);
				}
				fileIn.close();
				outputStream.close();
			}
		} catch (Exception e) {
			LogHelper.getInstance().logError("文件保存失败 ！"+e);
		}
	}
	
	public static String getFileName(String id,String simpleName,String path){
		String fullFileName = getBasePath() + File.separator + path + File.separator + id + simpleName;
		return fullFileName;
	}
	
	/**
	 * 根据路径获取文件流
	 * @param filePath
	 * @return byte[]
	 */
	public static byte[] getFileBytes(String filePath){
		byte[] file = null;
		if(StringUtils.isNotBlank(filePath)){
			FileInputStream fis = null;
			try{
				fis = new FileInputStream(filePath);
				file = new byte[fis.available()];
				fis.read(file);
			}catch(IOException e){
				e.printStackTrace();
				LogHelper.getInstance().logError(e.getMessage());
			}finally{
				if(fis!= null){
					try{
						fis.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		return file;
	}
	
	/**
	 * 生成对应的文件流以供下载
	 * @param fileName 文件名称
	 * @param content  文件码流
	 * @param response
	 */
	public static void createFileFlowForDownLoad(String fileName, byte[] content, HttpServletResponse response){
		if(StringUtils.isNotBlank(fileName)&&content!=null){
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			BufferedInputStream bis  = null;
	        BufferedOutputStream bos = null;
	        InputStream is           = null;    
			try {
				is = new ByteArrayInputStream(content);
				response.reset();
		        response.setContentType("application/vnd.ms-excel;charset=utf-8");
				response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName.toString()).getBytes(), "iso-8859-1"));
		        ServletOutputStream out = response.getOutputStream();
				
		        bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);
	            byte[] buff = new byte[2048];
	            int bytesRead;
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))){
	                bos.write(buff, 0, bytesRead);
	            }
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally {
				if (bis != null){
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	            if (bos != null){
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	            if (is != null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	            if (os != null){
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        }
		}
	}
	
	/** 
	 * 获取纯文件名称
	 * @param fileName
	 * @return
	 */
	public static String getSimpleFileName(String fileName){
		String simpleFileName = "";
		if(StringUtils.isNotBlank(fileName)&&fileName.indexOf("\\")>-1){
			simpleFileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		}else{
			simpleFileName = fileName;
		}
		return simpleFileName;
	}
	
}
