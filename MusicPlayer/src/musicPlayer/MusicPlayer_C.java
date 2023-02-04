package musicPlayer;

import java.util.ArrayList;

import javazoom.jl.player.MP3Player;

public class MusicPlayer_C {
	// controller class
	// 재생, 정지, 다음곡, 이전곡 -> 4개의 기능
	// class는 설계도 : controller class를 사용하려면 반드시 객체를 생성해야 사용 할 수 있다!!
	// 클래스 호출시 생성자 가장 먼저 호출
	// 노래를 재생하려면 먼저 노래정보 필요 -> 생성자에 먼저 담아주자!
	ArrayList<MusicVO_M> musicList = new ArrayList<>();
	MP3Player mp3 = new MP3Player();

	// 플레이할 곡의 인덱스 체크
	int curIndex = 0;
	String path = "C://music/";

	// 리턴 값 없음, void도 없음
	public MusicPlayer_C() {
		// 리스트에 MusicVO 타입 객체 담기 -> 객체 생성 -> new MusicVO_M()
		musicList.add(new MusicVO_M("FANCY", "TWICE", 117, path + "TWICE - FANCY.mp3"));
		musicList.add(new MusicVO_M("DallaDalla", "Itzy", 100, path + "Itzy - Dalla Dalla.mp3"));
		musicList.add(new MusicVO_M("2002", "Anne Marie", 120, path + "Anne Marie - 2002.mp3"));
		musicList.add(new MusicVO_M("SOLO", "Jennie", 150, path + "JENNIE - SOLO.mp3"));
		musicList.add(new MusicVO_M("벌써 12시", "CHUNG HA", 100, path + "CHUNG HA - 벌써 12시.mp3"));
		musicList.add(new MusicVO_M("bad guy", "Billie Eilish", 130, path + "Billie Eilish - bad guy.mp3"));
	}

	// 재생기능
	public MusicVO_M play() {
		// 현재 재생중인 곡이 있는지
		checkPlayMusic();
		return musicList.get(curIndex);
	}

	// 다음곡
	public MusicVO_M nextPlay() {
		curIndex++;
		if (curIndex >= musicList.size()) {
			curIndex = 0;
		}
		checkPlayMusic();
		return musicList.get(curIndex);
	}

	// 이전곡
	public MusicVO_M prePlay() {
		curIndex--;
		if (curIndex < 0) {
			curIndex = musicList.size() - 1;
		}
		checkPlayMusic();
		return musicList.get(curIndex);
	}

	// 정지
	public String stop() {
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		curIndex = 0;
		return "노래가 정지되었습니다.";
	}

	public void checkPlayMusic() {
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(musicList.get(curIndex).getMusicPath());
	}
}
