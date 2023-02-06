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
public class Main extends JFrame {

	private JPanelChange win;
	public MainPanel mainPanel = null;
	public LoginPanel loginPanel = null;
	public TenPanel tenPanel = null;
	public FivePanel fivePanel = null;

	public Main() {

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



