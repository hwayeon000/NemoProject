package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.GameDTO;
import dto.UserDTO;

public class DAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String url = "jdbc:oracle:thin:@gjaischool-b.ddns.net:1525:xe";
//			String user = "campus_d_0120_4";
//			String password = "smhrd4";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "C##NEMO";
//			String password = "NEMO1234";
			String user = "C##TEST";
			String password = "12345";
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("데이터 베이스 연동 실패");
			e.printStackTrace();
		}
	}
	
	public void getClose() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();							
			}
			if(conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 회원가입
	public int join(UserDTO dto) {
		getCon();
		int row = 0;
		try {
			String sql = "INSERT INTO USER_INFO( USER_SEQ , USER_ID , USER_PW , USER_NICK ) VALUES ( USER_SEQ.NEXTVAL , ? , ? , ? )";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getNick());
			
			row = psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("SQL 전송 실패");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return row;
	}
	
	// 로그인
	public UserDTO login(UserDTO dto) {
		UserDTO a = new UserDTO();
		getCon();
		try {
			String sql = "SELECT * FROM user_info WHERE user_id = ? and user_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			rs = psmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(4).equals(dto.getPw())) {
					a.setNick(rs.getString(3));
					a.setUserSeq(rs.getInt(1));
					a.setUserCoin(rs.getInt(5));
					break;
				}else {
					a.setNick(null);
				}
			}
		} catch (SQLException e) {
			System.out.println("로그인 : 데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}
		return a;
	}
	
	// 게임 난이도에 따른 게임 종류(개수) 반환
	public ArrayList<Integer> levelChoice(int level_choice) {
		ArrayList<Integer> game_seq = new ArrayList<>();
		getCon();
		try {
			String sql = "SELECT * FROM GAME_INFO WHERE game_level = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, level_choice);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				game_seq.add(rs.getInt("game_seq"));
			}
		} catch (SQLException e) {
			System.out.println("game level : 데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}
		return game_seq;
	}
	
	// 게임 번호의 답 데이터 반환
	public GameDTO gamePlay(int gameLevel, int gameNum) {
		GameDTO ans = new GameDTO(0, "");
		getCon();
		try {
			String sql = "SELECT * FROM (SELECT ROWNUM AS RN, GAME_SEQ, GAME_CODE, GAME_SUBJECT FROM GAME_INFO WHERE GAME_LEVEL = ? ORDER BY GAME_SEQ) WHERE RN = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, gameLevel);
			psmt.setInt(2, gameNum);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				ans.setGameSeq(rs.getInt(2)); // 게임 seq
				ans.setGameCode(rs.getString(3));  // 게임 답데이터
				ans.setGameSubject(rs.getString(4));  // 그림
			}
		} catch (SQLException e) {
			System.out.println("game level seq : 데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}
		return ans;
	}

	// 사용자 게임 플레이타임 정보 저장
	public void userGame(int userSeq ,int game_select , String time) {
		int row = 0;
		int check = 0;
		getCon();
		try {
			String sql = "SELECT GAME_SEQ FROM USER_GAME_INFO WHERE USER_SEQ = ? AND GAME_SEQ = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userSeq);
			psmt.setInt(2, game_select);
			check = psmt.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("SQL 전송 실패");
			e1.printStackTrace();
		}
		if (check > 0) {
			try {
				String sql = "UPDATE USER_GAME_INFO SET GAME_TIME = ? WHERE USER_SEQ = ? AND GAME_SEQ = ? ";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, time);    //  클리어 타임
				psmt.setInt(2, userSeq);
				psmt.setInt(3, game_select);
				row = psmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL 전송 실패");
				e.printStackTrace();
			}
		}else {
			try {
				String sql = "INSERT INTO USER_GAME_INFO ( USER_SEQ , GAME_SEQ , GAME_TIME , GAME_CLEAR ) VALUES ( ? , ? , ? , 1 )";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, userSeq);
				psmt.setInt(2, game_select);
				psmt.setString(3,time);         // 클리어 타임
				row = psmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL 전송 실패");
				e.printStackTrace();
			}finally {
				getClose();
			}
		}
	}

	// 게임 클리어 여부 체크
	public int clear(int userSeq, int gameSeq) {
		int row = 0;
		getCon();
		try {
			String sql = "SELECT GAME_CLEAR FROM USER_GAME_INFO WHERE GAME_CLEAR = 1 AND USER_SEQ = ? AND GAME_SEQ = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,userSeq);
			psmt.setInt(2, gameSeq);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Rank : SQL 전송 실패");
			e.printStackTrace();
		}
		return row;
	}

	// 게임 랭크 출력을 위한 정보
	public ArrayList<GameDTO> rank(int gameSeq) {
		ArrayList<GameDTO> list = new ArrayList<>();
		getCon();
		try {
			String sql = "SELECT A.USER_NICK, B.GAME_TIME\r\n"
					+ "  FROM USER_INFO A , (SELECT *\r\n"
					+ "                        FROM USER_GAME_INFO\r\n"
					+ "                       WHERE GAME_SEQ = ?\r\n"
					+ "                       ORDER BY LENGTH(GAME_TIME),GAME_TIME ASC\r\n"
					+ "                       ) B\r\n"
					+ " WHERE A.USER_SEQ = B.USER_SEQ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, gameSeq);
			rs = psmt.executeQuery();
			while(rs.next()) {
				String nick = rs.getString(1);
				String time = rs.getString(2);
				GameDTO dto = new GameDTO(nick,time);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			getClose();
		}
		return list;
		
		
	}
	
	// 사용자 코인의 변화 업데이트
	public int updateCoin(int coin , int userSeq) {
		getCon();
		int row = 0;
		try {
			String sql = "UPDATE USER_INFO SET USER_COIN = ? WHERE USER_SEQ = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, coin);
			psmt.setInt(2, userSeq);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("user_coin update : 코인 업데이트 실패");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return row;
	}
	
//	public int gaCha(int coin) {
//		int row = 0;
//		getCon();
//		try {
//			String sql = "UPDATE user_info SET user_coin = ? where user_seq = 3";
//			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1,coin);
//			row = psmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("Rank : SQL 전송 실패");
//			e.printStackTrace();
//		}
//		return row;
//	}
	
	// 사용자의 코인 체크
	public int userCoinCheck(int userSeq) {
		int res = 0;
		getCon();
		try {
			String sql = "SELECT USER_COIN FROM USER_INFO WHERE USER_SEQ = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userSeq);
			rs = psmt.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("user_coin check : 코인 체크 실패");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return res;
	}
	
	
}
