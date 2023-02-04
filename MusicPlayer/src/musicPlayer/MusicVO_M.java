package musicPlayer;

public class MusicVO_M {

	// 노래 제목, 가수이름, play 시간 + 노래 경로
	private String musicName;
	private String singer;
	private int playTime;
	private String musicPath;
	
	public MusicVO_M(String musicName, String singer, int playTime, String musicPath) {
		this.musicName = musicName;
		this.singer = singer;
		this.playTime = playTime;
		this.musicPath = musicPath;
	}
	
	public String getMusicName() {
		return musicName;
	}
	public String getSinger() {
		return singer;
	}
	public int getPlayTime() {
		return playTime;
	}
	public String getMusicPath() {
		return musicPath;
	}
}
