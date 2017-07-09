package platform.wegov.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WEGOV_ORG_TEAM")
public class WegovOrgTeamBo {
	
	@Id
	@Column(name = "ST_TEAM_ID", unique = true, nullable = false)
	private String teamId;
	@Column(name = "ST_TEAM_NAME")
	private String teamName;
	@Column(name = "ST_TEAM_ABBR_NAME")
	private String teamAbbrName;
	@Column(name = "ST_TEAM_GROUP")
	private String teamGroup;
	@Column(name = "ST_TEAM_LEVEL")
	private String teamLevel;
	@Column(name = "NM_TEAM_ORDER")
	private Integer teamOrder;
	@Column(name = "ST_TEAM_TITLE")
	private String teamTitle;
	@Column(name = "ST_IS_DELETED")
	private String isDeleted;
	@Column(name = "ST_DESCRIPTION")
	private String description;
	@Column(name = "NM_TOP_TEAM")
	private Integer topTeam;
	@Column(name = "ST_IS_ENTITY")
	private String isEntity;
	@Column(name = "ST_TEAM_LIAISON")
	private String teamLiaison;
	@Column(name = "ST_LIAISON_MOBILE")
	private String liaisonMobile;
	@Column(name = "ST_TEAM_PHONE")
	private String teamPhone;
	@Column(name = "ST_TEAM_ADDRESS")
	private String teamAddress;
	@Column(name = "ST_TEAM_FAX")
	private String teamFax;
	@Column(name = "ST_EXT_JZ_XZQH")
	private String extJzXzqh;
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamAbbrName() {
		return teamAbbrName;
	}
	public void setTeamAbbrName(String teamAbbrName) {
		this.teamAbbrName = teamAbbrName;
	}
	public String getTeamGroup() {
		return teamGroup;
	}
	public void setTeamGroup(String teamGroup) {
		this.teamGroup = teamGroup;
	}
	public String getTeamLevel() {
		return teamLevel;
	}
	public void setTeamLevel(String teamLevel) {
		this.teamLevel = teamLevel;
	}
	public Integer getTeamOrder() {
		return teamOrder;
	}
	public void setTeamOrder(Integer teamOrder) {
		this.teamOrder = teamOrder;
	}
	public String getTeamTitle() {
		return teamTitle;
	}
	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTopTeam() {
		return topTeam;
	}
	public void setTopTeam(Integer topTeam) {
		this.topTeam = topTeam;
	}
	public String getIsEntity() {
		return isEntity;
	}
	public void setIsEntity(String isEntity) {
		this.isEntity = isEntity;
	}
	public String getTeamLiaison() {
		return teamLiaison;
	}
	public void setTeamLiaison(String teamLiaison) {
		this.teamLiaison = teamLiaison;
	}
	public String getLiaisonMobile() {
		return liaisonMobile;
	}
	public void setLiaisonMobile(String liaisonMobile) {
		this.liaisonMobile = liaisonMobile;
	}
	public String getTeamPhone() {
		return teamPhone;
	}
	public void setTeamPhone(String teamPhone) {
		this.teamPhone = teamPhone;
	}
	public String getTeamAddress() {
		return teamAddress;
	}
	public void setTeamAddress(String teamAddress) {
		this.teamAddress = teamAddress;
	}
	public String getTeamFax() {
		return teamFax;
	}
	public void setTeamFax(String teamFax) {
		this.teamFax = teamFax;
	}
	public String getExtJzXzqh() {
		return extJzXzqh;
	}
	public void setExtJzXzqh(String extJzXzqh) {
		this.extJzXzqh = extJzXzqh;
	}
	
}
