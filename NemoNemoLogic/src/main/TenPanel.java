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
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TenPanel extends JPanel{

	int x_max = 25;
	int y_max = 25;
	int count = 0;

//	10 * 10
// 힌트 출력부 포함, 힌트부 : ( (10 + 1) / 2 )
	int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	private JPanelChange win;
	private JButton btnMain, btnX, btnPaint;
	int click_x = -1, click_y = -1;
	public boolean userClick;

	public TenPanel(JPanelChange win) {
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

					if (userClick) {
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
			// 패널 초기화
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = 0;
				}
			}
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

	// 칸 칠하기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.ITALIC, 30));
		g.drawString("count", 400, 130);

		g.setFont(new Font("Life", Font.ITALIC, 15));
		g.drawString("life", 400, 150);

		// 출력문 테스트중, 힌트 출력 필요
//		for (int i = 0; i < 15; i++) {
//			g.setFont(new Font("ans", Font.CENTER_BASELINE, 15));
//			if (i > 4) {
//				g.drawString(" " + i, i * 25, 20);
//			} else {
//				g.drawString(" ", i * 25, 20);
//			}
//		}
		int num = 10;
		String ans = "0100000010,1101001011,1001111001,1101111011,1110110111,1111111111,1101111011,1000110001,1100110011,0110110110";
		// 답데이터 이중배열로
		int[][] res = PrintQuestion.arrMake(ans, num); 
		// x hint
		String[] hintArrX = PrintQuestion.getHintArrX(res, num);
		// y hint
		String[] hintArrY = PrintQuestion.getHintArrY(res, num);
 		
		// 10 * 10 힌트 출력부
		for (int i = 0; i < 15; i++) {
			g.setFont(new Font("ans", Font.CENTER_BASELINE, 18));
			for (int j = 0; j < map.length; j++) {
				// X
				if (i<5&&j>4) {
					String[] a = hintArrX[j - 5].split(",");
					int n = a.length;
					if (a.length > 4-i) {
						g.drawString(" " + a[i-(5-n)], i * 25, j * 25 + 20);
					} else {
						g.drawString(" ", i * 25, j * 25 + 15);
					}
				// Y  
				} else if (i>4&&j<5) {
					String[] a = hintArrY[i-5].split(",");
					int n = a.length;
					if (a.length > 4-j) {
						g.drawString(" " + a[j-(5-n)], i * 25, j * 25 + 20);
//						g.drawString(" " + a[2-j], i * 25, j * 25 + 20);
					} else {
						g.drawString(" ", i * 25, j * 25 + 20);
					}
				}
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
						g.fillRect(i * 25, j * 25, 25, 25);
					} else if (map[i][j] == 3) {
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
