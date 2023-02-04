package jdbc기초;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex01Insert {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// connection, preparedStatment가 전역변수로 사용 될 수 있도록 만들기
		Connection conn = null;
		PreparedStatement psmt = null;
		// null : 아무것도 안들어가 있는 상태
		
		// 휘발성 데이터
		// : 다시 실행시키면 날아가는 데이터
		// : 유지가 안되는 데이터

		// 비휘발성 데이터
		// : 다시실행시켜도 유지가 되는 데이터
		// : 컴퓨터 껏다 켜도 유지가되는 데이터

		// 비휘발성 데이터 만드는 방법
		// 1. 문서 (메모장, 한글, 엑셀 ...)
		// 2. 서버
		// 3. 데이터 베이스

		// +
		// 1) Syntax Error
		// : 빨간줄
		// 2) Exception Error
		// : 예외상황, 실행을 시켜야 알 수 있는 오류

		// JDBC ( API )
		// : Interface

		System.out.print("ID 입력 >> ");
		String id = sc.next();
		System.out.print("PW 입력 >> ");
		String pw = sc.next();
		System.out.print("이름 입력 >> ");
		String name = sc.next();

		// JDBC 연결과정
		// 1. 데이터베이스 연결

		// 1-1) 드라이버 동적로딩
		// 내가 어떤 DB언어를 사용할 것인지 작성
//		Class.forName("oracle.jdbc.driver.OracleDriver"); // 오류 
//				---> 예외상황이 발생 할 수 있어!, try catch 문으로 감싸기
		// "동적로딩 실패"
		// : Driver클래스 이름이 잘못입력되어 있거나
		// : Class파일이 없거나( ojdbc8.jar가 없다 )

		// 동적 로딩
		// : 실행하는 순간 자료형이 결정되는 것
		// (Oracle자료형으로 결정되게 만들어 주는 것)
		// static (정적인 공간) : 실행되는 순간 바로 로딩이 되는 것
		// int a = 5; syso(a); <-- 바로 실행이 되는 것들이 static에 저장됨
		// static에 저장되는 것들은 글씨체가 기울임꼴

		// oracle.jdbc.driver --> 패키지명
		// OracleDriver --> 클래스명

		// 1-1)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 1-2) 데이터베이스 연결에 필요한 준비물
			// 데이터베이스의 주소 jdbc:oracle:thin:@localhost:1521:xe
			// 데이터베이스 계정, 비밀번호 system, 12345
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			// jdbc:oracle:thin --> thin 드라이버
			// @localhost --> 컴퓨터ip
			// 1521 --> port번호
			// xe --> 오라클 별칭
			String user = "system";
			String password = "12345";

			// DriverManager가 내가 권한을 정말 가지고 있는 지 확인
			// 권한을 받으면 아래쪽에서 계속 받은 권한 사용 필요
			conn = DriverManager.getConnection(url, user, password);

			// 2. Query문 전송 ( sql문 전송 )
			// 2-1) SQL문 준비하기
			String sql = "INSERT INTO join VALUES (?, ?, ?)";
			// ?(입력값) : 어떤 값을 사용자가 입력할 지 모르는 부분 ? 처리

			// 준비된 상태
			psmt = conn.prepareStatement(sql);

			// 2-3) ?인자 채우기 ( 사용자가 입력한 데이터 )
			// setString ---> ?에 들어갈 데이터의 데이터 타입이 명시
			// (몇번째 물음표, 어떤 데이터)
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);

//			psmt : sql문이랑 ?인자들 다 채워진 채 들어가 있는 상태
			int row = psmt.executeUpdate();
			// executeUpdate = 준비 된 sql문 db로 보낸 후 실행시키는 키워드
			// : 영향을 받은 행이 있는지 없는지에 대한 결과값을 받아온다
			// : 성공 ( 영향을 받은 행이 있다면 ) > 0 --> if
			// : 실패 ( 영향을 받은 행이 없다면 ) <= 0 --> else
			
			if (row > 0) {
				System.out.println("데이터 추가 성공");
			} else {
				System.out.println("데이터 추가 실패");
			}
			
			

		} catch (ClassNotFoundException e) {
			System.out.println("동적 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			// 지금까지 사용 한 객체들 닫아주기
			// finally
			// try finally
			// try catch finally
			// 예외상황이 발생해도 실행되는 코드
			
			// 사용 한 역순으로 닫아줘야 함!!
			// close();
			
			try {
				// conn을 사용을 안한상태
				// finally 무조건 실행
				// conn과 psmt가 사용 된 적 있는지 판단
				// 사용 된 적 있으면 닫아주자!
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				// 사용이 되면 conn, psmt에는 null값이 안들어잇음
				
			} catch (SQLException e) {
				System.out.println();
				e.printStackTrace();
			}
			
			
			
			
			
			
		}

	}
}
