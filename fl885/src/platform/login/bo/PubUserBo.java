package  platform.login.bo;

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
 * <p>Title: PubUserBoÀà/p>
 * <p>Description: Entity for public_user</p>
 * @author Auto generated
 * @version 0.5
 */
@Entity
@Table(name = "public_user")
public class PubUserBo implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(PubUserBo.class);
	
	private String public_user_id;
	
	private String public_user_code;
	
	private String public_user_name;

	private String public_user_tel;

	private String password;
	
	private String email;
	
	private String alipay_account;
	
	private String wechat_account;
	
	private String register_ip;
	
	private String province_code;
	
	private String province_name;
	
	private String city_code;
	
	private String city_name;
	
	private Date createtime; 
	
	/**
	 * ÊÇ·ñÒÑÉ¾³ý
	 */
	private String is_removed;
	
	@Id
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	public String getPublic_user_id() {
		return public_user_id;
	}
	public void setPublic_user_id(String public_user_id) {
		this.public_user_id = public_user_id;
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
	
	@Column(name = "public_user_code")
	public String getPublic_user_code() {
		return public_user_code;
	}
	public void setPublic_user_code(String public_user_code) {
		this.public_user_code = public_user_code;
	}
	
	@Column(name = "public_user_tel")
	public String getPublic_user_tel() {
		return public_user_tel;
	}
	public void setPublic_user_tel(String public_user_tel) {
		this.public_user_tel = public_user_tel;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "alipay_account")
	public String getAlipay_account() {
		return alipay_account;
	}
	public void setAlipay_account(String alipay_account) {
		this.alipay_account = alipay_account;
	}
	
	@Column(name = "wechat_account")
	public String getWechat_account() {
		return wechat_account;
	}
	public void setWechat_account(String wechat_account) {
		this.wechat_account = wechat_account;
	}
	
	@Column(name = "register_ip")
	public String getRegister_ip() {
		return register_ip;
	}
	public void setRegister_ip(String register_ip) {
		this.register_ip = register_ip;
	}
	
	@Column(name = "city_code")
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	
	@Column(name = "city_name")
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	@Column(name = "is_removed")
	public String getIs_removed() {
		return is_removed;
	}
	public void setIs_removed(String is_removed) {
		this.is_removed = is_removed;
	}
	
	@Column(name = "public_user_name")
	public String getPublic_user_name() {
		return public_user_name;
	}
	public void setPublic_user_name(String public_user_name) {
		this.public_user_name = public_user_name;
	}
	
}
