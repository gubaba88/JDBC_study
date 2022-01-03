package kyu.project.dto;

public class BoardDTO {
	private int gameNumber;
	private String gameName;

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public String toString() {
		return "BoardDTO [gameNumber=" + gameNumber + ", gameName=" + gameName + "]";
	}
}
