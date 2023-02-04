package musicPlayer;

import java.util.Scanner;

public class Main_V {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MusicPlayer_C player = new MusicPlayer_C();
		
		int choice = 0;
		while (choice != 5) {
			System.out.print("[1]재생 [2]정지 [3]다음곡 [4]이전곡 [5]종료 >> ");
			choice = sc.nextInt();
			System.out.println();
			if (choice == 1) {
				// 재생기능
				MusicVO_M m = player.play();
				printMusicnfo(m);
			} else if (choice == 2) {
				// 정지
				System.out.println(player.stop());
			} else if (choice == 3) {
				// 다음곡
				MusicVO_M m = player.nextPlay();
				printMusicnfo(m);
			} else if (choice == 4) {
				// 이전곡
				MusicVO_M m = player.prePlay();
				printMusicnfo(m);
			} else if (choice == 5) {
				player.stop();
				System.out.println("프로그램 종료");
			} else {
				System.out.println("번호를 확인해 주세요.");
			}
			// 개행
			System.out.println();
			
		}


	}
	
	public static String changeTime(MusicVO_M m) {
		return m.getPlayTime()/60 + "분" + m.getPlayTime()%60 + "초";
	}
	

	public static void printMusicnfo(MusicVO_M m) {
		System.out.println(m.getSinger() + ", " + m.getMusicName() + ", "
				+ changeTime(m));
	}
	
	
}
