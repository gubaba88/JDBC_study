package kyu.project.dto;

public class PostDTO {
	private int postNumber;
	private String postTitle;
	private String postTopic;
	private String postPasswd;
	private String postContent;
	private String postDate;
	private int gameNumber;
	private String memId;
	
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostTopic() {
		return postTopic;
	}
	public void setPostTopic(String postTopic) {
		this.postTopic = postTopic;
	}
	public String getPostPasswd() {
		return postPasswd;
	}
	public void setPostPasswd(String postPasswd) {
		this.postPasswd = postPasswd;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public int getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	@Override
	public String toString() {
		return "PostDTO [postNumber=" + postNumber + ", postTitle=" + postTitle + ", postTopic=" + postTopic
				+ ", postPasswd=" + postPasswd + ", postContent=" + postContent + ", postDate=" + postDate
				+ ", gameNumber=" + gameNumber + ", memId=" + memId + "]";
	}
}
