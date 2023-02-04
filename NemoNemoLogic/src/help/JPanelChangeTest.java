package help;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class JPanelChangeTest extends JFrame {

	private JPanelChange win;
	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;
	public MyPanel myPanel = null;

	public JPanelChangeTest() {

//		win.setTitle("네모네모 로직~");
//		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		win.myPanel = new MyPanel(win);
//
//		// t.requestFocus(); 이거하면 안되는데
//		win.setFocusable(true); // 이건 된다.
////		win.setSize(400, 300);
//		win.setSize(700, 600);
//		win.setVisible(true);

	}

	public static void main(String[] args) {

		JPanelChange win = new JPanelChange();

		win.setTitle("네모네모 로직 o((>ω< ))o");
		// main panel
		win.jpanel01 = new JPanel01(win);
		// login panel
		win.jpanel02 = new JPanel02(win);
		// 10 logic
		win.myPanel = new MyPanel(win);

		win.setFocusable(true);
		win.add(win.jpanel02);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 프레임을 화면 가운데에 배치
		win.setLocationRelativeTo(null);
		// win.setSize(400,300);
		win.setSize(600, 500);
		win.setVisible(true);
	}
}

@SuppressWarnings("serial")
//페널 변경
class JPanelChange extends JFrame {

	public MyPanel myPanel = null;
	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;

	// 패널 1번 2번 변경 후 재설정
	public void change(String panelName) {
		if (panelName.equals("Panel01")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			jpanel01.revalidate();
			jpanel01.repaint();
		} else if (panelName.equals("MyPanel")) {
			getContentPane().removeAll();
			getContentPane().add(myPanel);
			myPanel.revalidate();
			myPanel.repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			jpanel02.revalidate();
			jpanel02.repaint();
		}
	}
}

//10*10 로직 패널
class MyPanel extends JPanel {

	int x_max = 25;
	int y_max = 25;
	int count = 0;

//	10 * 10
//	int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
	int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };


	private JPanelChange win;
	private JButton btnMain, btnX, btnPaint;
	int click_x = -1, click_y = -1;
	public boolean userClick;

	public MyPanel(JPanelChange win) {
		setLayout(null);
		this.win = win;

		// 게임 페이지에서 나가는 버튼
		btnMain = new JButton("메인으로");
		btnMain.setSize(90, 20);
		btnMain.setLocation(430, 356);
		add(btnMain);
		btnMain.addActionListener(new MyMainListener());
		
		// 파랑으로 칠하기
		btnPaint = new JButton("칠하기");
		btnPaint.setSize(90, 20);
		btnPaint.setLocation(430, 296);
		add(btnPaint);
		btnPaint.addActionListener(new MyPaintListener());
		
		// 노랑으로 칠하기
		btnX = new JButton("X");
		btnX.setSize(90, 20);
		btnX.setLocation(430, 326);
		add(btnX);
		btnX.addActionListener(new MyXListener());
		

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println("a");
//				if ((click_x >= 0 && click_x <= 9) && (click_y <= 9 && click_y >= 0)) {
				if ((click_x >= 0 && click_x <= 14) && (click_y <= 14 && click_y >= 0)) {
					map[click_x][click_y] = 0;
					switch (e.getKeyCode()) {

					case KeyEvent.VK_UP:
						if (--click_y < 0)
							click_y = 0;
						map[click_x][click_y] = 1;
						break;

					case KeyEvent.VK_DOWN:
						if (++click_y > 14)
							click_y = 14;
						map[click_x][click_y] = 1;
						break;

					case KeyEvent.VK_RIGHT:
						if (++click_x > 14)
							click_x = 14;
						map[click_x][click_y] = 1;
						break;

					case KeyEvent.VK_LEFT:
						if (--click_x < 0)
							click_x = 0;
						map[click_x][click_y] = 1;
						break;
					}
				}
				repaint();
			}
		});
		
		// 마우스 클릭 이벤트
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// 마우스가 때어지는 순간 실행
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 마우스를 누르는 순간 실행
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스가 지정 영역 안에 들어 올 경우 실행
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			// 마우스를 눌렀다 떼는 순간 실행
			@Override
			public void mouseClicked(MouseEvent e) {
//				if (e.getX() <= 275 && e.getY() <= 275) {
				if (e.getX() <= 400 && e.getY() <= 400) {
					int x = e.getX() / x_max;
					int y = e.getY() / y_max;
					click_x = x;
					click_y = y;
					
					if(userClick) {
						map[x][y] = 1;
					} else {
						map[x][y] = 3;						
					}
					
					System.out.println("x, y : " + x + ", " + y);
				}
				repaint();
			}
		});
	}

	// 패널 1로 이동, 버튼 기능
	class MyMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("Panel01");
		}
	}
	
	// 칠하기 버튼 기능
	class MyPaintListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			userClick = true;
		}
	}
	
	// X 버튼 기능
	class MyXListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			userClick = false;
		}
	}

	// 칸 칠하기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.ITALIC, 30));
		g.drawString("count", 400, 130);

		g.setFont(new Font("Life", Font.ITALIC, 15));
		g.drawString("life", 400, 150);
		

		// 출력문 테스트중, 힌트 출력 필요
		for (int i = 0; i < 15; i++) {
			g.setFont(new Font("ans", Font.CENTER_BASELINE, 15));
			if (i > 4) {
				g.drawString(" " + i, i * 25, 20);				
			} else {
				g.drawString(" ", i * 25, 20);								
			}
		}
		
		// 125,125 기준으로 라인 그리기
		for (int i = 0; i <= 10; i++) {
			// x, y 위치, 크기, 크기
//			g.drawLine(0, 25 * i, 250, 25 * i);
//			g.drawLine(25 * i, 0, 25 * i, 250);
			g.drawLine(125, 125 + (25 * i), 375, 125 + (25 * i));
			g.drawLine(125 + (25 * i), 125, 125 + (25 * i), 375);
		}

		// 칠하는 영역
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++) {
				// 힌트 출력부 안칠해지도록 범위 지정
				if (i > 4 && j > 4) {
					if (map[i][j] == 1) {
						count++;
						// 사각형 칠하기
						g.setColor(Color.BLUE);
						// i, j 인덱스..
						System.out.println("check : " + i + ", " + j);
						g.fillRect(i * 25, j * 25, 25, 25);
//					g.fillRect(125 + (i * 25), 125 + (j * 25), 25, 25);
					} else if (map[i][j] == 3) {
						
						// g.fillOval(i * 25, j * 25, 25, 25);
						// 사각형 칠하기
						g.setColor(Color.yellow);
						// i, j 인덱스..
						g.fillRect(i * 25, j * 25, 25, 25);
					}
				}
			}

		g.setFont(new Font("Arial", Font.ITALIC, 15));
		g.drawString(count + "", 430, 150);
		count = 0;

	}

}

//1번째 패널
@SuppressWarnings("serial")
class JPanel01 extends JPanel {

	private JButton btnChange1, btnChange2, btnOut;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanelChange win;
	private String gameChoice = "";

	public JPanel01(JPanelChange win) {
		this.win = win;
		setLayout(null);

		btnChange1 = new JButton("Level 1");
		btnChange1.setSize(90, 20);
		btnChange1.setLocation(10, 10);
		add(btnChange1);
		
		btnChange2 = new JButton("Level 2");
		btnChange2.setSize(90, 20);
		btnChange2.setLocation(110, 10);
		add(btnChange2);

		btnOut = new JButton("로그아웃");
		btnOut.setSize(90, 20);
		btnOut.setLocation(230, 10);
		add(btnOut);
		// 게임 설명
		String exm = "<html><body style='text-align:center;'>"
				+ "=============================================<br/>"
				+ "ː            네모네모 로직 게임 설명             ː<br/>"
				+ "ː                                          ː<br/>"
				+ "ː   1. 쓰인 숫자만큼 연속된 칸을 칠할것 !          ː<br/>"
				+ "ː   2. 숫자와 숫자 사이에는 최소한 한 칸을 비울 것 ! ː<br/>"
				+ "ː   3. 숫자의 순서와 칠해진 칸의 순서가 일치할 것 !  ː<br/>"
				+ "=============================================</body></html>";
		
		String playGame = "<html><body style='text-align:center;'>"
				+ "---------------------------------<br/>"
				+ "게임을 선택해 주세요 ( •̀ ω •́ )✧<br/>"
				+ "---------------------------------</body></html>";
				
//		if (gameChoice == ) {
//			
//		}
		JLabel exmLb = new JLabel(exm, JLabel.CENTER);
		exmLb.setBounds(10, 40, 320, 100);
		add(exmLb);

//		jTextArea1 = new JTextArea();
//		jScrollPane1 = new JScrollPane(jTextArea1);
//		jScrollPane1.setSize(200, 150);
//		jScrollPane1.setLocation(10, 240);
//		add(jScrollPane1);

		
		
		
		btnChange2.addActionListener(new MyGameListener());
		btnOut.addActionListener(new OutListener());
	}

	// 버튼 누르면 10 Logic 호출
	class MyGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("MyPanel");
		}
	}

	// 버튼 누르면 패널 2번 호출
	class OutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("Panel02");
		}
	}
}

@SuppressWarnings("serial")
//2번째 패널
class JPanel02 extends JPanel {
	JTextField idField, nickField;// 클래스 변수로 선언
	private JPasswordField passwordField;
	private JPanelChange win;

	// 로그인 layout
	public JPanel02(JPanelChange win) {
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
			win.change("Panel01");
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
			win.change("Panel01");
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
				win.change("Panel01");
				JOptionPane.showMessageDialog(frame, nick + "님 환영합니다!");
			}
		}
	}

}


