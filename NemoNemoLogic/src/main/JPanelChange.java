package main;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class JPanelChange extends JFrame  {
	// 패널변경
	public TenPanel tenPanel = null;
	public FivePanel fivePanel = null;
	public MainPanel mainPanel = null;
	public LoginPanel loginPanel = null;

	// 패널 1번 2번 변경 후 재설정
	public void change(String panelName) {
		if (panelName.equals("MainPanel")) {
			getContentPane().removeAll();
			getContentPane().add(mainPanel);
			mainPanel.revalidate();
			mainPanel.repaint();
		} else if (panelName.equals("TenPanel")) {
			revalidate();
			getContentPane().removeAll();
			getContentPane().add(tenPanel);
			tenPanel.revalidate();
			tenPanel.repaint();
		} else if (panelName.equals("FivePanel")) {
			revalidate();
			getContentPane().removeAll();
			getContentPane().add(fivePanel);
			fivePanel.revalidate();
			fivePanel.repaint();
		} else if (panelName.equals("LoginPanel"))  {
			revalidate();
			getContentPane().removeAll();
			getContentPane().add(loginPanel);
			loginPanel.revalidate();
			loginPanel.repaint();
		}
	}
}