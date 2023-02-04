package 회원정보관리프로그램;

public class JoinDTO {
	// Data Object, 나만의 자료형
	// id, pw, name ---> private
	
	private String id;
	private String pw;
	private String name;
	
	// 생성자를 통해서 객체생성과 동시에 한번에 초기화 시켜주자
	public JoinDTO(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	// 오버로딩 : 중복정의(매개변수 갯수나 데이터 타입 다른경우)
	public JoinDTO(String name) {
		this.name = name;
	}
	
	public JoinDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public JoinDTO() {
		
	}
	
	// getter / setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
