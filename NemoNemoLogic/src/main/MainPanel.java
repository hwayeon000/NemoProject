package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Controller;


@SuppressWarnings("serial")
public class MainPanel extends JPanel  {
	// panel01

	private JLabel exmLb;
	private JButton btnLevel1, btnLevel2, btnOut, btn;
	int btnNum;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanelChange win;
	Controller ct = new Controller();
	
	private int gameLevel = 0;
	private String gameNum = "";

	// 게임 설명
	String exm = "<html><body style='text-align:center;'>" + "=============================================<br/>"
			+ "ː            네모네모 로직 게임 설명             ː<br/>" + "ː                                          ː<br/>"
			+ "ː   1. 쓰인 숫자만큼 연속된 칸을 칠할것 !          ː<br/>" + "ː   2. 숫자와 숫자 사이에는 최소한 한 칸을 비울 것 ! ː<br/>"
			+ "ː   3. 숫자의 순서와 칠해진 칸의 순서가 일치할 것 !  ː<br/>" + "ː           레벨을 누르면 번호가 뜹니다 !         ː<br/>"
			+ "=============================================</body></html>";
	// 게임 선택
	String playGame = "<html><body style='text-align:center;'>" + "=============================================<br/>"
			+ "게임을 선택해 주세요 ( •̀ ω •́ )✧ <br/>" + "=============================================</body></html>";

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
		btnLevel2.addActionListener(new MyGameListener());
//		btnChange2.addActionListener(new Level2Listener());

		btnOut = new JButton("로그아웃");
		btnOut.setSize(90, 20);
		btnOut.setLocation(230, 10);
		add(btnOut);
		btnOut.addActionListener(new OutListener());


		exmLb = new JLabel(exm, JLabel.CENTER);
		exmLb.setBounds(10, 40, 320, 150);
		add(exmLb);
		int cnt = 5;
		
//		jTextArea1 = new JTextArea();
//		jScrollPane1 = new JScrollPane(jTextArea1);
//		jScrollPane1.setSize(200, 150);
//		jScrollPane1.setLocation(10, 240);
//		add(jScrollPane1);

	}

	// 10이랑 5 Logic 수정을 위한 호출
	class MyGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			exmLb.setText(exm);
			
//			win.change("FivePanel");
			win.change("TenPanel");
//			win.change("Level2Listener");
			
		}
	}

	// 버튼 누르면 5*5
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
			ArrayList<Integer> res = ct.levelChoice(gameLevel);
			for (int i = 0; i < res.size(); i++) {
				if (i % 5 == 0) {
					j+=30;
					k = 10;
				}
				btn = new JButton(res.get(i) + "");
				btn.setSize(50, 20);
				btn.setLocation(k + (60 * (i % 5)), j);
				btnNum = (i+1);
				add(btn);
				/// 여기!!!!!!!!!!!!!!!!
				btn.addActionListener(new EventHandler());
			}
		}
	}

	// 패널 체크용, 비횔성화
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
			for (int i = 0; i < res.size(); i++) {
				if (i % 5 == 0) {
					j+=30;
					k = 10;
				}
				btn = new JButton(res.get(i) + "");
				btn.setSize(50, 20);
				btn.setLocation(k + (60 * (i % 5)), j);
				add(btn);
				btn.addActionListener(new EventHandler());
			}
		}
	}

	// 버튼 누르면 패널2번(로그인) 호출
	class OutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			exmLb.setText(exm);
			setLayout(null);
			win.change("tenPanel");
		}
	}
	
	// 게임 번호 누를 시 
	class EventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
//			e.getActionCommand() : 버튼 객체의 숫자!!!
			gameNum = e.getActionCommand();
			if (e.getActionCommand().equals("1")) {
				System.out.println(e.getActionCommand());
				// 패널 이동하면서 값 챙기기, gameLevel, gameNum
			}
			
		}
		
	}
	

	
	
}

