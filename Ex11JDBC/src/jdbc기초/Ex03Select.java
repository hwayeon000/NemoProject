package jdbc기초;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex03Select {
	public static void main(String[] args) {
		// numu hada jinjja
		// saram i gram anda
		// a jib gago shipda
		// gkgkgk
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//  ctrl + space : 자동 완성, import

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "12345";

			conn = DriverManager.getConnection(url, user, password);

			// sql문 : select
			String sql = "SELECT * FROM join";
			psmt = conn.prepareStatement(sql);

			// select : excuteQuery()
			rs = psmt.executeQuery();
			// ResultSet의결과값은 coursor(화살표, 커서)
			
			while (rs.next()) {
				// rs.next()가 true일 종안 실행시킬 코드
				// 꺼내오는 데이터의 데이터 타입(컬럼의 이름)
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				System.out.println(id + "\t" + pw + "\t" + name);
			}
			

		} catch (ClassNotFoundException | SQLException e) {
			// Exception을 하나의 catch문에 넗어준다
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		} finally {
			try {
				// 역순 : rs > psmt > conn
				if (rs!=null) rs.close();
				if (psmt!=null) psmt.close();
				if (conn!=null) conn.close();
				
			} catch (SQLException e) {
				System.out.println();
				e.printStackTrace();
			}
		}

	}

}
