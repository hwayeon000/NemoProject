package test;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame1 extends JFrame {

	public Frame1() {
		// 프레임 크기 설정
		setSize(800, 500);
		setLocation(100, 100);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		setTitle("네모네모로직 ( •̀ ω •́ )✧");

		// FlowLayout 객체 전달하기
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// 버튼을 만들어서
		JButton btn1 = new JButton("□");
		JButton btn2 = new JButton("버튼2");
		JButton btn3 = new JButton("버튼3");

		// 버튼 생성
        JButton btnPaint = new JButton("■");
        JButton btnDel = new JButton("X");
        
		// ★ 버튼 위치와 크기 설정
        //setBounds(가로위치, 세로위치, 가로길이, 세로길이)
        btnPaint.setBounds(530, 450, 40, 40);
        btnDel.setBounds(750, 450, 40, 40);

		// ★ 프레임에다가 버튼 추가
		getContentPane().add(btnPaint);
		getContentPane().add(btnDel);

		// 프레임에 추가하기
		add(btn1);
		add(btn2);
		add(btn3);
		add(btnPaint);
		add(btnDel);

		setVisible(true);
	}

}
