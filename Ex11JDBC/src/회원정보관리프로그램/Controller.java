package 회원정보관리프로그램;

import java.util.ArrayList;

public class Controller {
	JoinDAO dao = new JoinDAO();
	
	// main에서 사용자가 요청
	// 요청을 JoinDAO로 보내주는 역할
	
	// DAO가 보내준 결과값을 처리하여
	// Main으로 결과값 전송
	
	
	// main에서 회원가입 진행위해 id, pw, name을 보내주면
	// 받아주는 메서드
	public void join(JoinDTO dto) {
		int row = dao.join(dto);
		// row
		if (row>0) System.out.println("회원가입 성공!");
		else  System.out.println("회원가입 실패!");
	}

	// 회원 삭제
	public void delete(JoinDTO dto) {
		int row = dao.delete(dto);
		// row
		if (row>0) System.out.println("회원 삭제 성공!");
		else  System.out.println("회원 삭제 실패!");
	}
	
	// 회원 검색
	public void selectOne(JoinDTO dto) {
		JoinDTO res = dao.selectOne(dto);
		if (res.getId() != null) {
			System.out.println(res.getId() + "\t" + res.getPw() + "\t" + res.getName());
		}
		else  System.out.println("없는 회원정보입니다!");
	}

	// 리펙토링 기법, 일부러 오류내서 수정
	// 전체 정보 조회
	public void select() {
		ArrayList<JoinDTO> res = dao.select();
		System.out.println("===== 전체 회원 정보 =====");
		System.out.println("ID \t PW \t NAME");
		System.out.println("-----------------------");
		if (res.size() > 0) {
			for (int i = 0; i < res.size(); i++) {
				JoinDTO a = res.get(i);
				System.out.println(a.getId() + "\t" + a.getPw() + "\t" + a.getName());
			}
		}
		else  System.out.println("없는 회원정보입니다!");
	}

	// 로그인
	public void login(JoinDTO dto) {
		
		JoinDTO res = dao.login(dto);
//		boolean res = dao.login(dto);
		
		if (res.getName() != null) {
			System.out.println("로그인 완료");
			System.out.println(res.getName() + "님 반갑습니다.");
		} else {
			System.out.println("아이디나 비밀번호를 확인하세요.");
		}
		
	}
	
	
}
