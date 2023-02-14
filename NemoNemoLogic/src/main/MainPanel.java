package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Struct;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import dto.GameDTO;

@SuppressWarnings("serial")
public class MainPanel extends JPanel  {
	private JLabel exmLb;
	private JButton btnLevel1, btnLevel2, btnOut, btn;
	int btnNum;
	private JPanelChange win;
	Controller ct = new Controller();
	
	ArrayList<Integer> res;
	private int gameLevel = 0;
	// 레벨에 따른 게임 번호
	private int gameNum = 0;
	
	// 게임 답 데이터 정보 담을 객체
	static GameDTO gameData;

	
	// 버튼 초기화 시 범위 오류 날 수 있음으로 넉넉히 부여
	// 다른 방법이 없을까?
	private int gameSeq = 40;
    JButton[] gameBtn = new JButton[gameSeq]; // JButton을 담을수있는 그릇생성

	public MainPanel(JPanelChange win) {
		this.win = win;
		setLayout(null);

		btnLevel1 = new JButton("Level 1");
		btnLevel1.setSize(90, 20);
		btnLevel1.setLocation(10, 10);
		add(btnLevel1);
		btnLevel1.addActionListener(new Level1Listener());

		btnLevel2 = new JButton("Level 2");
		btnLevel2.setSize(90, 20);
		btnLevel2.setLocation(110, 10);
		add(btnLevel2);
//		btnLevel2.addActionListener(new MyGameListener());
		btnLevel2.addActionListener(new Level2Listener());

		btnOut = new JButton("로그아웃");
		btnOut.setSize(90, 20);
		btnOut.setLocation(230, 10);
		add(btnOut);
		btnOut.addActionListener(new OutListener());
		// 게임 설명 텍스트 출력
		exmLb = new JLabel(exm, JLabel.CENTER);
		exmLb.setBounds(10, 40, 320, 150);
		add(exmLb);
	}

	// 10이랑 5 Logic 수정을 위한 호출
	class MyGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			exmLb.setText(exm);
//			win.change("FivePanel");
			win.change("TenPanel");
		}
	}

	// 5 * 5 Logic
	class Level1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			exmLb.setText(playGame);
			exmLb.setBounds(10, 40, 320, 150);
			add(exmLb);
			gameLevel = 1;
			
			int k = 10;
			int j = 180;
			// 게임 DB에서 받아와서 버튼 띄우기, 5개씩
			res = ct.levelChoice(gameLevel);
			gameSeq = res.size();
			for (int i = 0; i < res.size(); i++) {
				if (i % 5 == 0) {
					j+=30;
					k = 10;
				}
				// 문제 번호 확인용
//				btn = new JButton(res.get(i) + "");
				// 게임 번호 버튼전환 밑 띄우기
				gameBtn[i] = new JButton((i + 1) + "");
				gameBtn[i].setSize(50, 20);
				gameBtn[i].setLocation(k + (60 * (i % 5)), j);
				add(gameBtn[i]);
				gameBtn[i].addActionListener(new EventHandler());

			}
		}
	}

	// 10 * 10 Logic
	class Level2Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			

			String playGame = "<html><body style='text-align:center;'>" + "=============================================<br/>"
					+ "게임을 선택해 주세요 ( •̀ ω •́ )✧ <br/>" + "=============================================</body></html>";

			exmLb.setText(playGame);
			exmLb.setBounds(10, 40, 320, 150);
			add(exmLb);
			gameLevel = 2;
			
			int k = 10;
			int j = 180;
			// 게임 레벨에 따라 갯수 DB에서 받아와서 버튼 띄우기, 5개씩
			ArrayList<Integer> res = ct.levelChoice(gameLevel);
			gameSeq = res.size();
			
			for (int i = 0; i < res.size(); i++) {
				if (i % 5 == 0) {
					j+=30;
					k = 10;
				}
				// 문제 번호 확인용
//				btn = new JButton(res.get(i) + "");
//				btn = new JButton((i + 1) + "");
//				btn.setSize(50, 20);
//				btn.setLocation(k + (60 * (i % 5)), j);
//				add(btn);
//				btn.addActionListener(new EventHandler());
				gameBtn[i] = new JButton((i + 1) + "");
				gameBtn[i].setSize(50, 20);
				gameBtn[i].setLocation(k + (60 * (i % 5)), j);
				add(gameBtn[i]);
				gameBtn[i].addActionListener(new EventHandler());				
			}
		}
	}

	// 버튼 누르면 로그인 패널 호출
	class OutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel();
			win.change("LoginPanel");
		}
	}
	
	// 게임 번호 누를 시 
	class EventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// e.getActionCommand() : 버튼 객체의 숫자!!!
			// 패널 이동하면서 값 챙기기, gameLevel, gameNum
			String gameNumber = e.getActionCommand();
			gameNum = Integer.parseInt(gameNumber);
			System.out.println(e.getActionCommand());
			
			ct.gamePlay(gameLevel, gameNum);
			
			if (gameLevel == 1) {
				clearPanel();
				win.change("FivePanel");
			} else {
				clearPanel();
				win.change("TenPanel");
			}
		}
		
	}
	
	public void clearPanel() {
		// 텍스트 초기화
		exmLb.setText(exm);
		System.out.println(gameSeq);
		// 버튼 초기화
		if (gameBtn.length>0) {
			for (int i = 0; i < gameSeq; i++) {
				gameBtn[i].setVisible(false);
//			btn.setVisible(false);
			}
		}
		setLayout(null);
	}
	
	
	
	
	

	// 게임 설명
	String exm = "<html><body style='text-align:center;'>"
			+ "=============================================<br/>"
			+ "ː            네모네모 로직 게임 설명             ː<br/>"
			+ "ː                                          ː<br/>"
			+ "ː   1. 쓰인 숫자만큼 연속된 칸을 칠할것 !          ː<br/>"
			+ "ː   2. 숫자와 숫자 사이에는 최소한 한 칸을 비울 것 ! ː<br/>"
			+ "ː   3. 숫자의 순서와 칠해진 칸의 순서가 일치할 것 !  ː<br/>"
			+ "ː           레벨을 누르면 번호가 뜹니다 !         ː<br/>"
			+ "=============================================</body></html>";
	// 게임 선택
	String playGame = "<html><body style='text-align:center;'>" + "=============================================<br/>"
			+ "게임을 선택해 주세요 ( •̀ ω •́ )✧ <br/>" + "=============================================</body></html>";

}

