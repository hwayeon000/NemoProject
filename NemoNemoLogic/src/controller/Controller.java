package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DAO;
import dto.GameDTO;
import dto.UserDTO;

public class Controller {
    // DB클래스 불러오기
    static DAO dao = new DAO();

	static Scanner sc = new Scanner(System.in);
	// static MusicPlayer player = new MusicPlayer();
	// MusicVO m = player.play(0); // 메인음악
	int row = 0;
	static String userNick = "";
	static int level = 0;
	static int userCoin = 0;
	static int userSeq = 0;
	static int gameSeq = 0;
	static long start;
	static long end;
	static String time;
	static GameDTO gameData = new GameDTO(level, "", "test");

	// 회원가입
	public String join(UserDTO dto) {
		row = dao.join(dto);
		if (row > 0) {
			return ("회원가입 성공!");
		} else {
			return ("아이디 또는 닉네임이 중복되었습니다.");
		}
	}

	// 로그인
	public String login(UserDTO dto) {
		UserDTO res = dao.login(dto);
//		boolean res = dao.login(dto);
		if (res.getNick() != null) {
			userSeq = res.getUserSeq();
			userCoin = res.getUserCoin();
			userNick = res.getNick();
			System.out.println(res.getNick() + "님 반갑습니다.\n");
			return res.getNick();
		} else {
			System.out.println("아이디나 비밀번호를 확인하세요.");
			System.out.println();
			return null;
		}
	}

	// 레벨 선택, 5*5 또는 10*10
	public ArrayList<Integer> levelChoice(int gameLevel) {
		ArrayList<Integer> res = dao.levelChoice(gameLevel);
		return res;
	}

	// 난이도에 따른 게임 선택
	public void gamePlay(int gameLevel, int gameNum) {
		gameSeq = gameNum;
		level = gameLevel;
		// 받아온 게임 정보
		gameData = dao.gamePlay(gameLevel, gameNum);
		System.out.println("ct:" + gameData.getGameCode());
	}
	
	// 게임 정보
	public GameDTO deliverData() {
		return gameData;
	}
	
	// 유저의 코인 정보
	public int UserCoinCheck() {
		return userCoin;
	}
	
	// 유저의 코인 정보
	public int UserCoinUpdate(int coin) {
		dao.updateCoin(coin, userSeq);
		return userCoin;
	}
	
	// 랭크 보여주기
	public static ArrayList<GameDTO> Rank() {
		ArrayList<GameDTO> gameUserRank = new ArrayList<>();
		gameUserRank = dao.rank(gameSeq);

		return gameUserRank;
	}
	
	// 게임 정보 업데이트
	public void UserGameUpdate(String time) {
		String time1 = time;
		dao.userGame(userSeq, gameSeq, time1);
	}


}
