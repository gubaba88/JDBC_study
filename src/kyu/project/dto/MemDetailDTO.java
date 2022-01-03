package kyu.project.dto;

public class MemDetailDTO {
	private String memId;
	private String memName;
	private String memBirth;
	private int memGender;
	private String memPhone;
	private String memAddress;
	private String memComment;

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}

	public int getMemGender() {
		return memGender;
	}

	public void setMemGender(int memGender) {
		this.memGender = memGender;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public String getMemComment() {
		return memComment;
	}

	public void setMemComment(String memComment) {
		this.memComment = memComment;
	}

	@Override
	public String toString() {
		return "MemDetailDTO [memId=" + memId + ", memName=" + memName + ", memBirth=" + memBirth + ", memGender="
				+ memGender + ", memPhone=" + memPhone + ", memAddress=" + memAddress + ", memComment=" + memComment
				+ "]";
	}
}
