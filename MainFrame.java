package wuziqiV4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mainframe extends JFrame {
	/**
	 * @param argss
	 */
	// 实现黑白子交替下棋与重绘，并且不能再同一个地方落子。
	static int[][] chess1 = new int[15][15];// 棋盘交点储存坐标
	static ArrayList<Point> chess = new ArrayList<Point>();// 设置队列储存棋子坐标POint为一个类
	// 在此处定义的目的在于：通过构造方法传递多个参数。
	static JPanel jp;
	static Mainframe mf = new Mainframe();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		jp = new JPanel();// new 一个次画布，

		jp.setPreferredSize(new Dimension(350, 0));// 设置大小
		jp.setBackground(Color.DARK_GRAY);// 背景颜色
		// 默认居中布局；
		mf.add(jp, BorderLayout.EAST);// 布局
		mf.setSize(1200, 900);
		mf.setDefaultCloseOperation(3);// 关闭按钮
		mf.setLocationRelativeTo(null);// 居中
		mf.getContentPane().setBackground(Color.LIGHT_GRAY);// 设置背景颜色
		DrawListener dl = new DrawListener(chess, chess1);// 加监听，并用构造方法传参

		mf.addMouseListener(dl);
		// TODO Auto-generated method stub
		String[] Bt = { "开始新游戏", "悔棋", "认输" };// 遍历数组并加上按钮，此处为熟悉for循环的麻烦操作
		for (int i = 0; i < Bt.length; i++) {
			JButton bu = new JButton(Bt[i]);// 引入按钮包并实例化按钮对象,数组的应用注意与数组队列li.get（index）相区别
			bu.setPreferredSize(new Dimension(300, 80));// 设置大小
			bu.addActionListener(dl);
			jp.add(bu);// 将按钮组件添加到页面上；

		}
		String[] c = { "人机对战", "人人对战" };// 给按钮加上文字
       
		JComboBox<String> cb = new JComboBox<String>(c);
		jp.add(cb);
		cb.addActionListener(dl);
		mf.setVisible(true);
		Graphics g = mf.getGraphics();
		dl.setG(g,c);
		dl.setM(mf);

	}//
		// 添加三个按钮

	public void Button() {

	}

	// 重绘
	public void paint(Graphics g) {
		super.paint(g);
		chessqupan(g);

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (chess1[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillOval((i) * 50 + 100 - 25, (j) * 50 + 100 - 25, 50, 50);
				}
				if (chess1[i][j] == 2) {

					g.setColor(Color.WHITE);
					g.fillOval((i) * 50 + 100 - 25, (j) * 50 + 100 - 25, 50, 50);

				}
			}

		}
	}

	// 画棋盘
	public void chessqupan(Graphics g) {

		for (int i = 0; i < 15; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(100, 100 + 50 * i, 800, 100 + 50 * i);
		}
		for (int j = 0; j < 15; j++) {
			g.setColor(Color.BLACK);
			g.drawLine(100 + 50 * j, 100, 100 + 50 * j, 800);
		}
	}

}
