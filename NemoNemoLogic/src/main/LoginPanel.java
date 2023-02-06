package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;
import dto.UserDTO;

public class LoginPanel extends JPanel {
	// panel01
	JTextField idField, nickField;// 클래스 변수로 선언
	private JPasswordField passwordField;
	private JPanelChange win;

	// 로그인 layout
	public LoginPanel(JPanelChange win) {
		// setBounds ( x좌표 y좌표 w 가로크기 h 세로크기 )
		setLayout(null);
		this.win = win;
		JLabel idLb = new JLabel("아이디 : ");
		idLb.setBounds(32, 40, 67, 15);
		add(idLb);

		idField = new JTextField();
		idField.setBounds(123, 40, 116, 21);
		add(idField);
		idField.setColumns(10);

		JLabel pwLb = new JLabel("암호 : ");
		pwLb.setBounds(32, 84, 67, 15);
		add(pwLb);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(123, 84, 116, 21);
		add(passwordField);

		JLabel nickLb = new JLabel("닉네임 : ");
		nickLb.setBounds(32, 128, 67, 15);
		add(nickLb);

		nickField = new JTextField();
		nickField.setBounds(123, 128, 116, 21);
		add(nickField);

		JButton btn = new JButton("메인으로");
		btn.setSize(90, 20);
		btn.setLocation(32, 10);
		add(btn);
		btn.addActionListener(new MyActionListener());

		JButton btnJoin = new JButton("회원가입");
		btnJoin.setSize(90, 20);
		btnJoin.setLocation(32, 172);
		add(btnJoin);
		btnJoin.addActionListener(new MyJoinListener());

		JButton btnLogin = new JButton("로그인");
		btnLogin.setSize(90, 20);
		btnLogin.setLocation(146, 172);
		add(btnLogin);
		btnLogin.addActionListener(new MyLoginListener());

	}

	// 버튼 누르면 패널 1번 호출
	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			idField.setText("");
			passwordField.setText("");
			nickField.setText("");
			win.change("MainPanel");
		}
	}

	// 회원가입 버튼
	class MyJoinListener implements ActionListener {
		JFrame frame;
		Controller ct = new Controller();

		public void Listener(JFrame f) {
			frame = f;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// win.change("Join");
			win.change("MainPanel");
			String id = idField.getText();
			System.out.println(idField);
			// String pw = passwordField.getText();
			String nick = nickField.getText();
			System.out.println(nick);

			String pw = "";

			// tf_pw 필드에서 패스워드를 얻어옴, char[] 배열에 저장
			char[] secret_pw = passwordField.getPassword();

			// secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
			for (char cha : secret_pw) {
				Character.toString(cha); // cha 에 저장된 값 string으로 변환
				// pw 에 저장하기, pw 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
				pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
			}

			UserDTO dto = new UserDTO(id, pw, nick);
			String res = ct.join(dto);
			// 다이얼로그
			JOptionPane.showMessageDialog(frame, res);
			
			idField.setText("");
			passwordField.setText("");
			nickField.setText("");
//			win.change("Panel02");
		}
	}

	// 로그인 버튼
	class MyLoginListener implements ActionListener {
		JFrame frame;
		Controller ct = new Controller();

		public void Listener(JFrame f) {
			frame = f;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// 버튼을 누르면 이쪽으로 제어가 이동
			String id = idField.getText();
			System.out.println(idField);
			// String pw = passwordField.getText();
			String nick = nickField.getText();
			System.out.println(nick);

			String pw = "";

			// tf_pw 필드에서 패스워드를 얻어옴, char[] 배열에 저장
			char[] secret_pw = passwordField.getPassword();

			// secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
			for (char cha : secret_pw) {
				Character.toString(cha); // cha 에 저장된 값 string으로 변환
				// pw 에 저장하기, pw 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
				pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
			}

			UserDTO dto = new UserDTO(id, pw);
			nick = ct.login(dto);
			// 다이얼로그
			if (nick == null) {
				JOptionPane.showMessageDialog(frame, "아이디나 비밀번호를 확인하세요.");
			} else {
				// 로그인 후 메인으로 이동
				idField.setText("");
				passwordField.setText("");
				nickField.setText("");
				win.change("MainPanel");
				JOptionPane.showMessageDialog(frame, nick + "님 환영합니다!");
			}
		}
	}


}
