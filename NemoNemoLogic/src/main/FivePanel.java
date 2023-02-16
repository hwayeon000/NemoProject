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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import dto.GameDTO;

@SuppressWarnings("serial")
public class FivePanel extends JPanel {
	int x_max = 25;
	int y_max = 25;
	// 패널 구성
	private JPanelChange win;
	private JButton btnMain, btnX, btnPaint, btnStart, btnHint, btnSucc;
	int click_x = -1, click_y = -1;
	// 버튼에 따라 다르게 칠하기 위한 변수
	public boolean userClick;
	int level = 5;
	int len = 0;
	// 5 * 5
	int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, };
	// 답데이터 가져오기
	static GameDTO gameData;
	static String ans = "00000,00000,00000,00000,00000";
	static String gameSubject = "";
	// 답 데이터를 이차원 배열로
	int[][] ansArr = new int[level][level];
	// 컨트롤러 통신을 위한 객체 생성
	Controller ct = new Controller();
	// 답 체크할 변수
	int totalCount = 0;
	int userCount = 0;
	int count = 0;
	// 답데이터 체크 후 성공시 띄울 다이얼!
	JFrame frame1;
	// 목숨 3, 코인 담을 변수
	static int life = 3;
	static int userCoin = 0;
	//시간 체크!
	static long start;
	static long end;

	// 패널 생성자
	public FivePanel(JPanelChange win) {
		setLayout(null);
		this.win = win;
		// life (430, 70) y + 30

		// 시작하기 버튼
		btnStart = new JButton("시작하기");
		btnStart.setSize(110, 20);
		btnStart.setLocation(430, 100);
		add(btnStart);
		btnStart.addActionListener(new DataListener());

		// 정답, 파란색
		btnPaint = new JButton("■");
		btnPaint.setSize(50, 20);
//		btnPaint.setLocation(430, 296);
		btnPaint.setLocation(430, 130);
		add(btnPaint);
		btnPaint.addActionListener(new MyPaintListener());

		// x, 노랑색
		btnX = new JButton("X");
		btnX.setSize(50, 20);
		btnX.setLocation(490, 130);
		add(btnX);
		btnX.addActionListener(new MyXListener());

		// 힌트 사용 버튼, 코인 차감
		btnHint = new JButton("힌트 사용");
		btnHint.setSize(110, 20);
		btnHint.setLocation(430, 160);
		add(btnHint);
		btnHint.addActionListener(new HintListener());

		// 정답 체크
		btnSucc = new JButton("(⌐■_■) 정답");
		btnSucc.setSize(110, 20);
		btnSucc.setLocation(430, 190);
		add(btnSucc);
		btnSucc.addActionListener(new SuccessListener());
		
		// 랭킹 조회
		btnSucc = new JButton("랭킹 조회");
		btnSucc.setSize(110, 20);
		btnSucc.setLocation(430, 220);
		add(btnSucc);
		btnSucc.addActionListener(new RankListener());

		// 게임 페이지에서 나가는 버튼
		btnMain = new JButton("메인으로");
		btnMain.setSize(110, 20);
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
				if ((click_x >= 0 && click_x <= 7) && (click_y <= 7 && click_y >= 0)) {
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
				if (e.getX() <= 225 && e.getY() <= 225) {
					int x = e.getX() / x_max;
					int y = e.getY() / y_max;
					click_x = x;
					click_y = y;

					if (userClick) {
						map[x][y] = 1;
					} else {
						map[x][y] = 3;
					}
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
			// 힌트 데이터, 목숨 초기화
			life = 3;
			ans = "00000,00000,00000,00000,00000";
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

	// 정답 버튼 기능 ====================
	class SuccessListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("check: " + totalCount + ", " + count + ", " + userCount);
			if (totalCount == userCount && count == userCount) {
				// 게임 끝나는 시간 체크
				end = System.currentTimeMillis();
				int getCoin = 1;
				if (life==3) getCoin = 1;
				else getCoin = 0;
				// 코인 업데이트
				ct.UserCoinUpdate(getCoin);
				// 시간 담아 업데이트
				String time = Long.toString((end - start) / 1000 / 60) + "," + Long.toString((end - start) / 1000 % 60);
				// 체크
				System.out.print(Long.toString((end - start) / 1000 / 60) + "분"
						+ Long.toString((end - start) / 1000 % 60) + "초");
				ct.UserGameUpdate(time);
				
				JOptionPane.showMessageDialog(frame1,
						"<html><body style='text-align:center;'>" + gameSubject + "<br/>축하합니다!</body></html>");
			} else {
				JOptionPane.showMessageDialog(frame1, "다시 확인해 보세요!!");
			}
		}
	}
	
	// 힌트 사용 버튼, 코인이 있으면 가능 아니면 불가
	class HintListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (userCoin == 0) {
				JOptionPane.showMessageDialog(frame1, "코인이 없어요! 코인을 모으고 사용해주세요~!");
			} else {
				--userCoin;
				// 코인 차감 업데이트
				ct.UserCoinUpdate(userCoin);
				--life;
				Random rd = new Random();
				int choice1 = rd.nextInt(level);
				int choice2 = rd.nextInt(level);
				String text = "힌트 사용 완료! 코인 한개 차감!\n" + userCoin + "개 남았습니다!";
				System.out.println(choice1 + "열, " + choice2 + "행");
				// 임시
				if (ansArr[choice1][choice2] == 1) {
					map[choice2 + len][choice1 + len] = 1;
				} else {
					map[choice2 + len][choice1 + len] = 3;
					//목숨도 깔까..?
				}
				if (life == 0) {
					text = "목숨이 모두 소진되었습니다.\n 진행이 불가능합니다..";
					
				}
				JOptionPane.showMessageDialog(frame1, text);
			}
			paintComponent(getGraphics());
		}
	}

	// 랭킹 조회
	class RankListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 랭킹 정보 받아오기
			ArrayList<GameDTO> list = ct.Rank();
			if (list.size() != 0) {
				// 텍스트 정렬
				ArrayList<String> textRank = new ArrayList<>();
				for(int i = 0; i < list.size(); i++) {
					String[] time = list.get(i).getGameTime().split(",");
					String sterRank= (i+1) + "등    " + list.get(i).getUserNick()+"     "+time[0] + "분 " + time[1] + "초";
					textRank.add(sterRank);
				}
				String textRank1 = "<html><body style='text-align:center;'>=========== 랭크 ===========<br/>";
				for (int i = 0; i < textRank.size(); i++) {
					textRank1 += textRank.get(i) + "<br/>";
				}
				textRank1 += "</body></html>";
				JOptionPane.showMessageDialog(frame1, textRank1);
				
			} else {
				JOptionPane.showMessageDialog(frame1, "<html><body style='text-align:center;'>아직 도전자가 없네요!<br/>  도전해보세요!! (｡･∀･)ﾉﾞ<br/></body></html>");
			}
		}
	}
	
	// 시작!!!! 코인을 같이 가져오기
	class DataListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gameData = ct.deliverData();
			userCoin = ct.UserCoinCheck();
			ans = gameData.getGameCode();
			gameSubject = gameData.getGameSubject();
			paintComponent(getGraphics());
			start = System.currentTimeMillis();
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
		g.drawString("count", 430, 30);

		// life label 셋팅
		g.setFont(new Font("Life", Font.ITALIC, 15));
//		g.drawString("life", 430, 150);
		g.drawString("life", 430, 50);

		// life 셋팅
		g.setFont(new Font("life", Font.ITALIC, 15));
		g.drawString(life + "", 460, 50);
		
		// coin label 셋팅
		g.setFont(new Font("coin", Font.ITALIC, 15));
//		g.drawString("life", 430, 150);
		g.drawString("coin", 430, 70);

		// coin 셋팅
		g.setFont(new Font("coin", Font.ITALIC, 15));
		g.drawString(userCoin + "", 460, 70);
		
		

		// 정답 체크 데이터 초기화
		count = 0;
		userCount = 0;

		// 답데이터 y, x
		ansArr = PrintQuestion.arrMake(ans, level);
		// x hint
		String[] hintArrX = PrintQuestion.getHintArrX(ansArr, level);
		// y hint
		String[] hintArrY = PrintQuestion.getHintArrY(ansArr, level);

		len = (level + 1) / 2;

		// 힌트 출력부 5 * 5
		for (int i = 0; i < 8; i++) {
			g.setFont(new Font("ans", Font.CENTER_BASELINE, 18));
			for (int j = 0; j < map.length; j++) {
				// X
				if (i < len && j > len - 1) {
					String[] a = hintArrX[j - len].split(",");
					// length = 3, 2, 1
					if (a.length > len - 1 - i) {
						int n = a.length;
						g.drawString(" " + a[i - (len - n)], i * 25, j * 25 + 20);
					} else {
						g.drawString(" ", i * 25, j * 25 + 20);
					}
					// Y
				} else if (i > len - 1 && j < len) {
					String[] a = hintArrY[i - len].split(",");
					if (a.length > len - 1 - j) {
						int n = a.length;
						g.drawString(" " + a[j - (len - n)], i * 25, j * 25 + 20);
//						g.drawString(" " + a[2-j], i * 25, j * 25 + 20);
					} else {
						g.drawString(" ", i * 25, j * 25 + 20);
					}
				}
			}
		}

		// 75, 75 기준으로 라인 그리기
		for (int i = 0; i <= level; i++) {
			// x, y 위치, 크기, 크기
//			g.drawLine(0, 25 * i, 250, 25 * i);
//			g.drawLine(25 * i, 0, 25 * i, 250);
			g.drawLine(75, 75 + (25 * i), 200, 75 + (25 * i));
			g.drawLine(75 + (25 * i), 75, 75 + (25 * i), 200);
		}

		// 칠하는 영역
		for (int i = 0; i < level + len; i++) {
			for (int j = 0; j < level + len; j++) {
				// 힌트 출력부 안칠해지도록 범위 지정
				if (i > len - 1 && j > len - 1) {
					if (map[i][j] == 1) {
						count++;
						if (ansArr[j-len][i-len] == 1) userCount++;
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
