package main;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {

	@SuppressWarnings("unused")
	private JPanelChange win;
	public MainPanel mainPanel = null;
	public LoginPanel loginPanel = null;
	public TenPanel tenPanel = null;
	public FivePanel fivePanel = null;

	public static void main(String[] args) {

		JPanelChange win = new JPanelChange();

		win.setTitle("네모네모 로직 o((>ω< ))o");
		// main panel
		win.mainPanel = new MainPanel(win);
		// login panel
		win.loginPanel = new LoginPanel(win);
		// 10 logic
		win.tenPanel = new TenPanel(win);
		// 5 logic
		win.fivePanel = new FivePanel(win);

		win.setFocusable(true);
		win.add(win.loginPanel);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 프레임을 화면 가운데에 배치
		win.setLocationRelativeTo(null);
		// win.setSize(400,300);
		win.setSize(600, 500);
		win.setVisible(true);
	}
}



