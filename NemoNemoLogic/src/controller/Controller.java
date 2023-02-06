package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DAO;
import dto.UserDTO;

public class Controller {
    // DB클래스 불러오기
    DAO dao = new DAO();

	static Scanner sc = new Scanner(System.in);
	// static MusicPlayer player = new MusicPlayer();
	// MusicVO m = player.play(0); // 메인음악
	int row = 0;
	static String userNick = "";
	static int userCoin = 0;
	static int level = 0;
	static int userSeq = 0;
	static int gameSeq = 0;
	static long start;
	static long end;
	static String time;

	public String join(UserDTO dto) { // 회원가입
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

	public ArrayList<Integer> levelChoice(int level_choice) {
		ArrayList<Integer> res = dao.levelChoice(level_choice);
		return res;
	}



}
