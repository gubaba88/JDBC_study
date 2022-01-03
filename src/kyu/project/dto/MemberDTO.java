package kyu.project.dto;

public class MemberDTO {
	private int memNumber;
	private String memId;
	private String memPasswd;
	private String memEmail;
	private int memPoint;
	private String joinDate;

	public int getMemPoint() {
		return memPoint;
	}

	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}

	public int getMemNumber() {
		return memNumber;
	}

	public void setMemNumber(int memNumber) {
		this.memNumber = memNumber;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPasswd() {
		return memPasswd;
	}

	public void setMemPasswd(String memPasswd) {
		this.memPasswd = memPasswd;
	}

	public String getmemEmail() {
		return memEmail;
	}

	public void setmemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [memNumber=" + memNumber + ", memId=" + memId + ", memPasswd=" + memPasswd + ", memEmail="
				+ memEmail + ", memPoint=" + memPoint + ", joinDate=" + joinDate + "]";
	}

}
