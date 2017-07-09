package  com.edubean.entrust.bo;

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
 * <p>Title: EntrustBo类/p>
 * <p>Description: Entity for JXKH_MODEL_INFO</p>
 * @author Auto generated
 * @version 0.5
 */
@Entity
@Table(name = "entrust")
public class EntrustBo implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(EntrustBo.class);
	
	private String id;
	
	private String title;
	/**
	 * 咨询内容
	 */
	private String content;
	/**
	 * 咨询人ip
	 */
	private String ip;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 创建时间
	 */
	private Date createtime; 
	/**
	 * 咨询人所在省份代码
	 */
	private String province_code;
	/**
	 * 警种类型名称
	 */
	private String province_name;
	/**
	 * 是否已删除
	 */
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
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "isremoved")
	public String getIsremoved() {
		return isremoved;
	}
	public void setIsremoved(String isremoved) {
		this.isremoved = isremoved;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "province_code")
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	
	@Column(name = "province_name")
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
