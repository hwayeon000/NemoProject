package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class NemoTest extends JFrame {

	int x_max = 25;
	int y_max = 25;
	int count = 0;

	
	int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	NemoTest() {
		setTitle("네모네모 로직~");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyPanel t = new MyPanel();
		setContentPane(t);

		// t.requestFocus(); 이거하면 안되는데
		t.setFocusable(true); // 이건 된다.
		setSize(400, 300);
		setVisible(true);

	}

	public static void main(String[] args) {
	
		new NemoTest();

	}

	class MyPanel extends JPanel {
		int click_x = -1, click_y = -1;

		public MyPanel() {
			addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					// System.out.println("a");
					if ((click_x >= 0 && click_x <= 9) && (click_y <= 9 && click_y >= 0)) {
						map[click_x][click_y] = 0;
						switch (e.getKeyCode()) {

						case KeyEvent.VK_UP:
							if (--click_y < 0)
								click_y = 0;
							map[click_x][click_y] = 1;
							break;

						case KeyEvent.VK_DOWN:
							if (++click_y > 9)
								click_y = 9;
							map[click_x][click_y] = 1;
							break;

						case KeyEvent.VK_RIGHT:
							if (++click_x > 9)
								click_x = 9;
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

			addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getX() <= 275 && e.getY() <= 275) {
						int x = e.getX() / x_max;
						int y = e.getY() / y_max;
						map[x][y] = 1;
						click_x = x;
						click_y = y;
					}
					repaint();
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("Arial", Font.ITALIC, 30));
			g.drawString("count", 280, 30);
			for (int i = 0; i <= 10; i++) {
				g.drawLine(0, 25 * i, 250, 25 * i);
				g.drawLine(25 * i, 0, 25 * i, 250);
			}

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++) {
					if (map[i][j] == 1) {
						count++;
//						g.fillOval(i * 25, j * 25, 25, 25);
						// 사각형 칠하기
						g.setColor(Color.RED);
						// i, j 인덱스..
						g.fillRect(i * 25, j * 25, 25, 25);
					} else if (map[i][j] == 3) {
						count++;
//						g.fillOval(i * 25, j * 25, 25, 25);
						// 사각형 칠하기
						g.setColor(Color.yellow);
						// i, j 인덱스..
						g.fillRect(i * 25, j * 25, 25, 25);
					}
				}

			g.setFont(new Font("Arial", Font.ITALIC, 15));
			g.drawString(count + "", 310, 50);
			count = 0;

		}

	}

}
