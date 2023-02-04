package jdbc기초;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex02Delete {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement psmt = null;
		
		// 데이블에서 삭제하고 싶은 행의 name 입력받자
		System.out.print("삭제하고 싶은 ID의 이름 입력 >> ");
		String name = sc.next();
		
		// 사용자가 입력받은 이름에 해당하는 id, pw, name(개의 행)
		// 삭제하는 JDBC코드 작성
		
		// SQL : delete 데이블명 조건식
		
		// 1. 동적로딩
		// 2. 권한확인(준비물 3개)
		// 3. SQL문 준비
		// 4. 전송
		// 5. 결과 확인
		// 6. 사용한객체 닫아주기
		
		// Delete ---> Select(executeQuery, 커서)
		// [1]학생추가 [2]학생삭제 [3]학생전체조회 [4]프로그램종료
		// MVC
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "12345";
			
			// 권한이 있는지 없는지를 Connection 객체에 담기
			conn = DriverManager.getConnection(url, user, password);
			
			// Query문 준비하기
			String sql = "DELETE FROM join WHERE name = ?";
			psmt = conn.prepareStatement(sql);

			// ? 인자를 채워줘야 함
			// 1. ?의 데이터 타입
			// 2. 어떤 값이 들어가야하는지 (사용자가 입력한 값이 저장된 변수)
			// 3 몇번째 물음표로 들어가는지
			psmt.setString(1, name);
			
			// SQL문을 보내고 DB에서 실행
			// 영향울 받은 행이 있는지 없는지에 대한 결과값을 받아옴
			int row = psmt.executeUpdate();

			if (row>0) {
				System.out.println("데이터 삭제 성공");
			} else {
				System.out.println("데이터 삭제 실패");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("동적 로딩 실패");
			// 1. ojdbc8.jar 파일이 없거나
			// 2. Class 이름을 잘못 입력한 경우
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				// if문 실행코드 한줄이면 중괄호 생략 가능
				if (psmt!=null) psmt.close();
				if (conn!=null) conn.close();
				
			} catch (SQLException e) {
				System.out.println();
				e.printStackTrace();
			}
		}
		
		
		
	}

}
