package main;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import dto.GameDTO;
import main.FivePanel.DataListener;
import main.FivePanel.MyMainListener;
import main.FivePanel.MyPaintListener;
import main.FivePanel.MyXListener;
import main.FivePanel.SuccessListener;

@SuppressWarnings("serial")
public class TenPanel extends JPanel {
	int x_max = 25;
	int y_max = 25;
	// 패널 구성
	private JPanelChange win;
	private JButton btnMain, btnX, btnPaint, btnStart;
	int click_x = -1, click_y = -1;
	// 버튼에 따라 다르게 칠하기 위한 변수
	public boolean userClick;
	int level = 10;
	// 10 * 10
	// 힌트 출력부 포함, 힌트부 : ( (10 + 1) / 2 )
	int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
	// 답데이터 가져오기
	String ans = "0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000";
	static GameDTO gameData;
	static String gameSubject = "";
	// 답 데이터를 이차원 배열로
	int[][] ansArr = new int[level][level];
	// 컨트롤러 통신을 위한 객체 생성
	Controller ct = new Controller();
	// 답 체크할 변수
	int totalCount = 0;
	int count = 0;
	// 답데이터 체크 후 성공시 띄울 다이얼!
	JFrame frame1;
	// 목숨 3
	static int life = 3;
	static int userCoin = 0;

	// 패널 생성자
	public TenPanel(JPanelChange win) {
		setLayout(null);
		this.win = win;
		// life (430, 70) y + 30
		// 시작하기 버튼
		btnStart = new JButton("시작하기");
		btnStart.setSize(90, 20);
		btnStart.setLocation(430, 100);
		add(btnStart);
		btnStart.addActionListener(new DataListener());

		// 파랑으로 칠하기
		btnPaint = new JButton("선택");
		btnPaint.setSize(90, 20);
//				btnPaint.setLocation(430, 296);
		btnPaint.setLocation(430, 130);
		add(btnPaint);
		btnPaint.addActionListener(new MyPaintListener());

		// 노랑으로 칠하기
		btnX = new JButton("X");
		btnX.setSize(90, 20);
		btnX.setLocation(430, 160);
		add(btnX);
		btnX.addActionListener(new MyXListener());

		// 정답 체크
		btnPaint = new JButton("정답!");
		btnPaint.setSize(90, 20);
		btnPaint.setLocation(430, 190);
		add(btnPaint);
		btnPaint.addActionListener(new SuccessListener());

		// 게임 페이지에서 나가는 버튼
		btnMain = new JButton("메인으로");
		btnMain.setSize(90, 20);
		btnMain.setLocation(430, 356);
		add(btnMain);
		btnMain.addActionListener(new MyMainListener());
		
		
		

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

					if (userClick) {
						map[x][y] = 1;
					} else {
						map[x][y] = 3;
					}

					System.out.println("10p click x, y : " + x + ", " + y);
				}
				repaint();
			}
		});
	}

	// 패널 1로 이동, 버튼 기능
	class MyMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 패널 초기화
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = 0;
				}
			}
			// 답데이터 초기화
			ans = "0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000";
			win.change("MainPanel");
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

	// 정답 버튼 기능 =======================================
	class SuccessListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(totalCount + ", " + count);
			if (totalCount == count)
				JOptionPane.showMessageDialog(frame1,
						"<html><body style='text-align:center;'>" + gameSubject + "<br/>축하합니다!</body></html>");
			else
				JOptionPane.showMessageDialog(frame1, "다시 확인해 보세요!!");
		}
	}

	// 시작!!!! 코인을 같이 가져오기
	class DataListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			gameData = ct.deliverData();
			ans = gameData.getGameCode();
			gameSubject = gameData.getGameSubject();
			paintComponent(getGraphics());

			// 답 체크
			int num = 0;
			for (int i = 0; i < ansArr.length; i++) {
				for (int j = 0; j < ansArr.length; j++) {
					if (ansArr[i][j] == 1) {
						num += 1;
					}
				}
			}
			totalCount = num;
			System.out.println("답 체크 " + totalCount);
		}
	}

	// 칸 칠하기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.ITALIC, 30));
//		g.drawString("count", 430, 130);
		g.drawString("count", 430, 50);

		g.setFont(new Font("Life", Font.ITALIC, 15));
//		g.drawString("life", 430, 150);
		g.drawString("life", 430, 70);

		// 나중에 life 셋팅으로 변경할 것
		// ----------------------------------------------------------//
		g.setFont(new Font("Arial", Font.ITALIC, 15));
		g.drawString(count + "", 460, 70);
		count = 0;
		// 답데이터 이중배열로
		ansArr = PrintQuestion.arrMake(ans, level);
		// x hint
		String[] hintArrX = PrintQuestion.getHintArrX(ansArr, level);
		// y hint
		String[] hintArrY = PrintQuestion.getHintArrY(ansArr, level);
		
		int len = (level + 1 ) / 2;
		
		// 10 * 10 힌트 출력부
		for (int i = 0; i < 15; i++) {
			g.setFont(new Font("ans", Font.CENTER_BASELINE, 18));
			for (int j = 0; j < map.length; j++) {
				// X
				if (i < len && j > len - 1) {
					String[] a = hintArrX[j - len].split(",");
					int n = a.length;
					if (a.length > len - 1 - i) {
						g.drawString(" " + a[i - (len - n)], i * 25, j * 25 + 20);
					} else {
						g.drawString(" ", i * 25, j * 25 + 15);
					}
					// Y
				} else if (i > len -1 && j < len) {
					String[] a = hintArrY[i - len].split(",");
					int n = a.length;
					if (a.length > len - 1 - j) {
						g.drawString(" " + a[j - (len - n)], i * 25, j * 25 + 20);
//						g.drawString(" " + a[2-j], i * 25, j * 25 + 20);
					} else {
						g.drawString(" ", i * 25, j * 25 + 20);
					}
				}
			}
		}

		// 125,125 기준으로 라인 그리기
		for (int i = 0; i <= level; i++) {
			// x, y 위치, 크기, 크기
//			g.drawLine(0, 25 * i, 250, 25 * i);
//			g.drawLine(25 * i, 0, 25 * i, 250);
			g.drawLine(125, 125 + (25 * i), 375, 125 + (25 * i));
			g.drawLine(125 + (25 * i), 125, 125 + (25 * i), 375);
		}

		// 칠하는 영역
		for (int i = 0; i < level + len; i++) {
			for (int j = 0; j < level + len; j++) {
				// 힌트 출력부 안칠해지도록 범위 지정
				if (i > len - 1 && j > len - 1) {
					if (map[i][j] == 1) {
						count++;
						// 사각형 칠하기
						g.setColor(Color.BLUE);
						// i, j 인덱스..
						g.fillRect(i * 25, j * 25, 24, 24);
					} else if (map[i][j] == 3) {
						// 사각형 칠하기
						g.setColor(Color.yellow);
						// i, j 인덱스..
						g.fillRect(i * 25, j * 25, 24, 24);
					}
				}
			}
		}

	}

	public void Listener(JFrame f) {
		frame1 = f;
	}

}
