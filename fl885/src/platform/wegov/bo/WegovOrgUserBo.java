package platform.wegov.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WEGOV_ORG_USER")
public class WegovOrgUserBo {

	@Id
	@Column(name = "ST_USER_ID", unique = true, nullable = false)
	private String userId;
	@Column(name = "ST_ACCOUNT")
	private String account;
	@Column(name = "ST_NAME")
	private String name;
	@Column(name = "ST_ABBR_NAME")
	private String abbrName;
	@Column(name = "ST_SEX")
	private String sex;
	@Column(name = "ST_IS_VAILD")
	private String isValid;
	@Column(name = "ST_IS_DELETED")
	private String isDeleted;
	@Column(name = "ST_PASSWORD")
	private String password;
	@Column(name = "NM_RANK")
	private int rank;
	@Column(name = "NM_RANK_ORDER")
	private int rankOrder;
	@Column(name = "ST_PLACE")
	private String place;
	@Column(name = "ST_TEAM_ID")
	private String teamId;
	@Column(name = "ST_EMAIL")
	private String email;
	@Column(name = "ST_PHONE")
	private String phone;
	@Column(name = "ST_ADDRESS")
	private String address;
	@Column(name = "DT_CREAT")
	private Date create;
	@Column(name = "DT_DELETE")
	private Date delete;
	@Column(name = "ST_DESCRIPTION")
	private String description;
	@Column(name = "ST_LOGIN_STATUS")
	private String loginStatus;
	@Column(name = "ST_IP_RANGE")
	private String ipRange;
	@Column(name = "DT_LAST_LOGIN")
	private Date lastLogin;
	@Column(name = "ST_LAST_IP")
	private String lastIp;
	@Column(name = "ST_LAST_HOST")
	private String lastHost;
	@Column(name = "DT_LAST_LOGOUT")
	private Date lastLogout;
	@Column(name = "DT_LAST_FAIL_LOGIN")
	private Date lastFailLogin;
	@Column(name = "ST_LAST_FAIL_IP")
	private String lastFailIp;
	@Column(name = "ST_LAST_FAIL_HOST")
	private String lastFailHost;
	@Column(name = "NM_LAST_FAIL_TIME")
	private int lastFailTime;
	@Column(name = "ST_IP")
	private String ip;
	@Column(name = "ST_DUTY_LEVEL")
	private String dutyLevel;
	@Column(name = "ST_MOBILE")
	private String mobile;
	@Column(name = "ST_RECFLAG")
	private String recflag;
	@Column(name = "ST_TITLE")
	private String title;
	@Column(name = "NM_GLOBAL_ORDER")
	private int globalOrder;
	@Column(name = "ST_EMERGENT_PHONE")
	private String emegentPhone;
	@Column(name = "ST_NAME_FULL_PY")
	private String nameFullPy;
	@Column(name = "ST_NAME_PY")
	private String namePy;
	@Column(name = "NM_DUTY_LEVEL")
	private int dutyLevelNo;
	@Column(name = "ST_STATE")
	private String state;
	@Column(name = "ST_MACHINE_CODE")
	private String machineCode;
	@Column(name = "DT_LAST_PASSWORD")
	private Date lastPassword;
	@Column(name = "ST_SESSION_ID")
	private String sessionId;
	@Column(name = "ST_CA")
	private String ca;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbrName() {
		return abbrName;
	}
	public void setAbbrName(String abbrName) {
		this.abbrName = abbrName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getRankOrder() {
		return rankOrder;
	}
	public void setRankOrder(int rankOrder) {
		this.rankOrder = rankOrder;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreate() {
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}
	public Date getDelete() {
		return delete;
	}
	public void setDelete(Date delete) {
		this.delete = delete;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getIpRange() {
		return ipRange;
	}
	public void setIpRange(String ipRange) {
		this.ipRange = ipRange;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public String getLastHost() {
		return lastHost;
	}
	public void setLastHost(String lastHost) {
		this.lastHost = lastHost;
	}
	public Date getLastLogout() {
		return lastLogout;
	}
	public void setLastLogout(Date lastLogout) {
		this.lastLogout = lastLogout;
	}
	public Date getLastFailLogin() {
		return lastFailLogin;
	}
	public void setLastFailLogin(Date lastFailLogin) {
		this.lastFailLogin = lastFailLogin;
	}
	public String getLastFailIp() {
		return lastFailIp;
	}
	public void setLastFailIp(String lastFailIp) {
		this.lastFailIp = lastFailIp;
	}
	public String getLastFailHost() {
		return lastFailHost;
	}
	public void setLastFailHost(String lastFailHost) {
		this.lastFailHost = lastFailHost;
	}
	public int getLastFailTime() {
		return lastFailTime;
	}
	public void setLastFailTime(int lastFailTime) {
		this.lastFailTime = lastFailTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDutyLevel() {
		return dutyLevel;
	}
	public void setDutyLevel(String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRecflag() {
		return recflag;
	}
	public void setRecflag(String recflag) {
		this.recflag = recflag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGlobalOrder() {
		return globalOrder;
	}
	public void setGlobalOrder(int globalOrder) {
		this.globalOrder = globalOrder;
	}
	public String getEmegentPhone() {
		return emegentPhone;
	}
	public void setEmegentPhone(String emegentPhone) {
		this.emegentPhone = emegentPhone;
	}
	public String getNameFullPy() {
		return nameFullPy;
	}
	public void setNameFullPy(String nameFullPy) {
		this.nameFullPy = nameFullPy;
	}
	public String getNamePy() {
		return namePy;
	}
	public void setNamePy(String namePy) {
		this.namePy = namePy;
	}
	public int getDutyLevelNo() {
		return dutyLevelNo;
	}
	public void setDutyLevelNo(int dutyLevelNo) {
		this.dutyLevelNo = dutyLevelNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public Date getLastPassword() {
		return lastPassword;
	}
	public void setLastPassword(Date lastPassword) {
		this.lastPassword = lastPassword;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getCa() {
		return ca;
	}
	public void setCa(String ca) {
		this.ca = ca;
	}
	
	
	
}
