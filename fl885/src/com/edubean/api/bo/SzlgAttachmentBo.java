package com.edubean.api.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 事件附件实体
 * @author lzc
 * @version 2014-08-11
 */
@Entity
@Table(name = "LG_WECHAT_ATTACHMENT")
public class SzlgAttachmentBo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ST_ID", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String stId; 		
	
	@Column(name = "ST_CLUE_ID",  nullable = true, length = 32)
	private String stClueId; 	
	
	@Column(name = "ST_FILE_NAME",  nullable = true, length = 1024)
	private String stFileName;    

	@Column(name = "ST_FILE_ROUTE",  nullable = true, length = 1024)
	private String stFileRoute;      

	@Column(name = "ST_FILE_TYPE",  nullable = true, length = 32)
	private String stFileType;  
	
	@Column(name = "ST_UPLOAD_OPEN_ID",  nullable = true, length = 32)
	private String stUploadOpenId; 
	
	@Column(name = "DT_UPLOAD_TIME", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dtUploadTime;      
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "BL_FILE_CONTENT", columnDefinition = "BLOB", nullable = true)
	private byte[] fileContent;
	
	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getStClueId() {
		return stClueId;
	}

	public void setStClueId(String stClueId) {
		this.stClueId = stClueId;
	}

	public String getStFileName() {
		return stFileName;
	}

	public void setStFileName(String stFileName) {
		this.stFileName = stFileName;
	}

	public String getStFileRoute() {
		return stFileRoute;
	}

	public void setStFileRoute(String stFileRoute) {
		this.stFileRoute = stFileRoute;
	}

	public String getStFileType() {
		return stFileType;
	}

	public void setStFileType(String stFileType) {
		this.stFileType = stFileType;
	}

	public String getStUploadOpenId() {
		return stUploadOpenId;
	}

	public void setStUploadOpenId(String stUploadOpenId) {
		this.stUploadOpenId = stUploadOpenId;
	}

	public Date getDtUploadTime() {
		return dtUploadTime;
	}

	public void setDtUploadTime(Date dtUploadTime) {
		this.dtUploadTime = dtUploadTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	
	
}


