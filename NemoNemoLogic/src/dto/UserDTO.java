package dto;

public class UserDTO {
	private String id;
	private String pw;
	private String nick;
	private Integer userSeq;
	private Integer userCoin;
	private Integer userHint;

	public UserDTO(String id, String pw, String nick) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
	}

	public UserDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public UserDTO(int userSeq) {
		this.userSeq = userSeq;
	}

	public UserDTO() {
	}

    
	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setUserCoin(Integer userCoin) {
		this.userCoin = userCoin;
	}

	public void setUserHint(Integer userHint) {
		this.userHint = userHint;
	}

	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public Integer getUserCoin() {
		return userCoin;
	}

	public Integer getUserHint() {
		return userHint;
	}

	public Integer getUserSeq() {
		return userSeq;
	}

	public String getNick() {
		return nick;
	}

}
