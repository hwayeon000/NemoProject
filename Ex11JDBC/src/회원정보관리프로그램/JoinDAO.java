package 회원정보관리프로그램;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JoinDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// Controller가 보내준 요청(DB에 저장, 삭제..)
	// DataBase에 직접적인 접근을 하는 코드를 통해 결과값을 받아온다
	// 결과값을 Controller로 다시 보내줌

	// 데이터 베이스를 연결하기 위한 과정 중
	// 동적 로딩, 준비물 3개로 권한을 받아오는게 계속 필요
	// 기능(메서드)로 만들어 놓고 필요할떄마다 가져다 사용하자!

	public void getCon() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "12345";

		// 권한이 있는지 없는지를 Connection 객체에 담기
		conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
	}
	
	public void getClose() {
		// 자원반납
		// psmt -> conn
		// select
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 회원가입
	public int join(JoinDTO dto) {
		int row = 0;
		getCon();

		try {
			String sql = "INSERT INTO join VALUES (?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());

			// delete, insert, update.. : 영향을 받는 행이 있음
			// ---> executeUpdate()
			row = psmt.executeUpdate();// 영향을 받는 행이 있는지 없는지 int 결과값

			// row : JDBC 코드를 통해 최종적으로 받아온 결과값

		} catch (SQLException e) {
			System.out.println("회원 가입 : SQL 전송 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}

		return row;
	}

	// 회원삭제
	public int delete(JoinDTO dto) {
		int row = 0;

		getCon();

		try {
			String sql = "DELETE FROM join WHERE name = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());

			row = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("회원 삭제 : 데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}

		return row;
	}

	// 회원 검색
	public JoinDTO selectOne(JoinDTO dto) {
		JoinDTO dto1 = new JoinDTO(null, null, null);
		getCon();

		try {
			String sql = "SELECT * FROM join WHERE name = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());

			rs = psmt.executeQuery();
			
			if (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				dto1.setId(id);
				dto1.setPw(pw);
				dto1.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("회원검색 : 데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}

		return dto1;
	}

	// 회원 조회
	public ArrayList<JoinDTO> select() {
		// new ArrayList<>() 는 object타입 -> JoinDTO로 자동 형변환
		ArrayList<JoinDTO> dto = new ArrayList<>();

		getCon();
		
		try {
			String sql = "SELECT * FROM join";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery(); // 테이블 컬럼에 화살표 생김
			while (rs.next()) {
				// rs.next() --> bollean
				// 데이터가 있으면 실행시킬 코드
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				JoinDTO dto1 = new JoinDTO();
				dto1.setId(id);
				dto1.setPw(pw);
				dto1.setName(name);
				dto.add(dto1);
			}

		} catch (SQLException e) {
			System.out.println("회원조회 : 데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}

		return dto;
	}
	
	// 로그인
	public JoinDTO login(JoinDTO dto) {
//		boolean res = false;
		JoinDTO a = new JoinDTO();
		getCon();
		
		try {
//			String sql = "SELECT * FROM join WHERE id = ?";
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, dto.getId());
			
			String sql = "SELECT * FROM join WHERE id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());

			rs = psmt.executeQuery();
			
			// rs.next() --> 있는지 없는지
//			res = rs.next();
			while (rs.next()) {
				if (rs.getString(2).equals(dto.getPw())) {
					a.setName(rs.getString(3));
					break;
				}else {
					a.setName(null);
				}
			}
		} catch (SQLException e) {
			System.out.println("로그인 : 데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}

//		return res;
		return a;
		
	}
	

}
