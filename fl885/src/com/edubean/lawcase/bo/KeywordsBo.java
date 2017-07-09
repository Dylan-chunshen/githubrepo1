package com.edubean.lawcase.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;


/**
 * <p>Title: KeywordsBoÀà/p>
 * <p>Description: Entity for JXKH_MODEL_INFO</p>
 * @author Auto generated
 * @version 0.5
 */
@Entity
@Table(name = "keywords")
public class KeywordsBo implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(KeywordsBo.class);
	
	private String id;
	private String case_id;
	private String keyword_code;
	private String keyword_name;
	private Date createtime;
	private String isremoved;
	
	@Id
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "case_id")
	public String getCase_id() {
		return case_id;
	}
	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}
	
	@Column(name = "keyword_code")
	public String getKeyword_code() {
		return keyword_code;
	}
	public void setKeyword_code(String keyword_code) {
		this.keyword_code = keyword_code;
	}
	
	@Column(name = "keyword_name")
	public String getKeyword_name() {
		return keyword_name;
	}
	public void setKeyword_name(String keyword_name) {
		this.keyword_name = keyword_name;
	}
	
	@Column(name = "isremoved")
	public String getIsremoved() {
		return isremoved;
	}
	public void setIsremoved(String isremoved) {
		this.isremoved = isremoved;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
