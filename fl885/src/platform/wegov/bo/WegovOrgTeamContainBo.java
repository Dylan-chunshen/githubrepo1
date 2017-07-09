package platform.wegov.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WEGOV_ORG_TEAM_CONTAIN")
public class WegovOrgTeamContainBo {

	@Id
	@Column(name = "ST_TEAM_ID", unique = true, nullable = false)
	private String teamId;
	
	@Column(name = "ST_CT_ID")
	private String ctId;
	
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getCtId() {
		return ctId;
	}
	public void setCtId(String ctId) {
		this.ctId = ctId;
	}
	
}
