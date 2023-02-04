package 회원정보관리프로그램;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// id, pw, name 하나로 -> JoinDTO자료형
		int select = 0;
		// 컨트롤러 객체 생성
		Controller ct = new Controller();

		while (select != 6) {
			System.out.print("[1]로그인 [2]회원가입 [3]전체조회 [4]회원검색 [5]회원삭제 [6]프로그램 종료 >> ");
			select = sc.nextInt();

			if (select == 1) {
				// 로그인
				System.out.print("ID를 입력해주세요 >>");
				String id = sc.next();
				System.out.print("Password를 입력해주세요 >>");
				String pw = sc.next();
				JoinDTO dto = new JoinDTO(id, pw);
				ct.login(dto);
				

			} else if (select == 2) {
				// 회원가입
				System.out.print("ID를 입력해주세요 >>");
				String id = sc.next();
				System.out.print("Password를 입력해주세요 >>");
				String pw = sc.next();
				System.out.print("이름를 입력해주세요 >>");
				String name = sc.next();

				// JDBC
				// 동적로딩
				// 준비물 권한확인
				// SQL문
				// 걸과확인
				// 자원반납 close

				// id, pw, name 하나로 -> JoinDTO자료형
				JoinDTO dto = new JoinDTO(id, pw, name);

				// 회원가입에 해당하는 JDBC 코드
				ct.join(dto);

			} else if (select == 3) {
				// 전체조회
				// 메서드 이름 : select()
				ct.select();
			} else if (select == 4) {
				// 회원검색
				System.out.print("확인을 원하는 ID의 이름을 입력해주세요 >>");
				String name = sc.next();
				// 출력형태
				// ex) name이 조자연이라면
				// 조자연님의 id는 mi입니다. pw는 1234입니다!
				
				// 메서드 이름 : selectOne()
				
				JoinDTO dto = new JoinDTO(name);
				ct.selectOne(dto);

			} else if (select == 5) {
				// 회원삭제
				// 사용자에게 이름 입력받아 행 삭제

				// delete
				System.out.println("삭제 할 회원의 이름 입력하세요 >>");
				String name = sc.next();
				JoinDTO dto = new JoinDTO(name);
				ct.delete(dto);

			} else if (select == 6) {
				// 프로그램 종료
				System.out.println("프로그램이 종료되었습니다.");
			} else {
				System.out.println("1~6번을 선택해주세요.");
			}

		}

	}

}
