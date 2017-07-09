package platform.wegov.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WEGOV_ORG_MEMBER_USER")
public class WegovOrgMemberUserBo {
	
	@Id
	@Column(name = "ST_MEMBER_ID")
	private String member;
	@Column(name = "ST_TEAM_ID")
	private String teamId;
	@Column(name = "ST_TM_NAME")
	private String tmName;
	
	
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}
	
	
	
}
