

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DrawListener implements MouseListener, ActionListener {
	// 判断输赢；

	/**
	 * @param args
	 */
	Graphics g;
	private ArrayList chess;
	private int[][] chess1;
	private Mainframe mf;

	public DrawListener(ArrayList chess, int chess1[][]) {

		this.chess = chess;
		this.chess1 = chess1;

	}
	public void setM(Mainframe mf) {
		this.mf = mf;
		
		// TODO Auto-generated method stub

	}
	String[] c   ;
	public void setG(Graphics g,String[] c) {
		this.g = g;
		this.c=c;
	}//setG方法传参

	public void actionPerformed(ActionEvent e1) {
		// TODO Auto-generated method stub
		String s = e1.getActionCommand();// 得到点击按钮的东西
		
		// System.out.println(s);//检测相关代码
		if (s == "开始新游戏") {
			v1=1;
					
			System.out.println("v1dezhishi      "+v1+"          v2"+v2);	
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (chess1[i][j] == 1 || chess1[i][j] == 2) {
						chess1[i][j] = 0;
					}//清空数据

				}
				mf.paint(g);
			}
		}
		if (s == "悔棋") {
		Point p=new Point();
			int k=chess.size()-1;
			 p=(Point) chess.get(k);//point 自带的方法，取出最后一个棋子的相关数据
			 System.out.println(p);
			 chess1[p.x][p.y]=0;//将二维数组重新命名，重绘的时候不会画出棋子
			chess.remove(k);//写的方法不同改动不同
			mf.paint(g);

		}
		if (s.equals("认输")) {
			if(chess.size() % 2 == 0){//利用队列长度判断
				JOptionPane.showConfirmDialog(mf, "黑棋认输");
			
			}
			else{
				JOptionPane.showConfirmDialog(mf, "白棋认输");
				
			}

		}
		if(c.equals("人人对战")){
		v2=1;
		System.out.println("v1dezhishi        "+v1+"          v2"+v2);	
		}
		if (c.equals("人机对战")){
			v2=2;
		}
	}

	int xx, yy,v1,v2=2;

	public void mouseClicked(MouseEvent e) {
		if(v1==1&&v2==1){
			
		int x = e.getX();
		int y = e.getY();
		int a = (x - 100) / 50, b = (y - 100) / 50, c = x % 50, d = y % 50;

		// TODO Auto-generated method stub
		if (c <= 25) {
			xx = a;

		} else {
			xx = a + 1;

		}
		if (d <= 25) {
			yy = b;
		} else {
			yy = b + 1;
		}
		Point p = new Point();// 每点击一次，存下一个点。
		p.setLocation(xx, yy);
		// System.out.println("" + xx + "");检测
		// System.out.println("" + yy + "");
		chess.add(p);
		if(chess.size()>2){
		System.out.println("该数组的长度为"+chess.size()+chess.get(2));}
		if (chess1[xx][yy] != 2 && chess1[xx][yy] != 1) {
			if (chess.size() % 2 == 0) {
				g.setColor(Color.white);
				// System.out.println("xx的值是"+xx+"yy的值是"+yy+chess1);
				chess1[xx][yy] = 2;

			}
			if (chess.size() % 2 == 1) {
				g.setColor(Color.BLACK);

				chess1[xx][yy] = 1;
			}
			g.fillOval((p.x) * 50 + 100 - 25, (p.y) * 50 + 100 - 25, 50, 50);
			this.TOrF();

		} else {
			// System.out.println("请重新下棋");
			JOptionPane.showConfirmDialog(null, "请重新下棋");
			int k = chess.size() - 1;
			chess.remove(k);

		}}
		if(v1==1&&v2==2){
			System.out.println("v1dezhishi"+v1+"v2"+v2);	
			int x = e.getX();
			int y = e.getY();
			int a = (x - 100) / 50, b = (y - 100) / 50, c = x % 50, d = y % 50;

			// TODO Auto-generated method stub
			if (c <= 25) {
				xx = a;

			} else {
				xx = a + 1;

			}
			if (d <= 25) {
				yy = b;
			} else {
				yy = b + 1;
			}
			g.setColor(Color.BLACK);

			chess1[xx][yy] = 1;
		
		g.fillOval(xx* 50 + 100 - 25, yy * 50 + 100 - 25, 50, 50);
		this.TOrF1();
		}
		

		// System.out.println(""+chess.size()+"");//检查是否存入

	}
	private void TOrF1() {
		// TODO Auto-generated method stub

		int count2 = 0;
		int count1 = 0;
		int count3 = 0;
		int f = 0;
		int g = 0;

		for (int i = xx + 1; i < 15; i++) {
			if (chess1[i][yy] == chess1[xx][yy]) {

				count2++;

			} else {
				break;
			}
		}

		for (int j = xx; j < 15; j--) {
			if (chess1[j][yy] == chess1[xx][yy]) {

				count2++;

			} else {
				break;
			}
		}
		for (int i1 = yy + 1; i1 < 15; i1++) {
			if (chess1[xx][i1] == chess1[xx][yy]) {

				count3++;

			} else {
				break;
			}
		}
		for (int i2 = yy; i2 < 15; i2--) {
			if (chess1[xx][i2] == chess1[xx][yy]) {

				count3++;

			} else {
				break;
			}
		}

		for (int a = xx + 1, b = yy + 1; xx < 15 && yy < 15; a++, b++) {
			if (chess1[a][b] == chess1[xx][yy]) {
				f++;
			} else {
				break;
			}
		}
		for (int a = xx, b = yy; xx < 15 && yy < 15; a--, b--) {
			if (chess1[a][b] == chess1[xx][yy]) {
				f++;
			} else {
				break;
			}

		}
		for (int c = xx - 1, d = yy + 1; xx < 15 && yy < 15; c--, d++) {
			if (chess1[c][d] == chess1[xx][yy]) {
				g++;
			} else {
				break;
			}
		}
		for (int c = xx, d = yy; xx < 15 && yy < 15; c++, d--) {
			if (chess1[c][d] == chess1[xx][yy]) {
				g++;
			} else {
				break;
			}

		}
	}

	private void TOrF() {
		// TODO Auto-generated method stub

		int count2 = 0;
		int count1 = 0;
		int count3 = 0;
		int f = 0;
		int g = 0;

		for (int i = xx + 1; i < 15; i++) {
			if (chess1[i][yy] == chess1[xx][yy]) {

				count2++;

			} else {
				break;
			}
		}

		for (int j = xx; j < 15; j--) {
			if (chess1[j][yy] == chess1[xx][yy]) {

				count2++;

			} else {
				break;
			}
		}
		for (int i1 = yy + 1; i1 < 15; i1++) {
			if (chess1[xx][i1] == chess1[xx][yy]) {

				count3++;

			} else {
				break;
			}
		}
		for (int i2 = yy; i2 < 15; i2--) {
			if (chess1[xx][i2] == chess1[xx][yy]) {

				count3++;

			} else {
				break;
			}
		}

		for (int a = xx + 1, b = yy + 1; xx < 15 && yy < 15; a++, b++) {
			if (chess1[a][b] == chess1[xx][yy]) {
				f++;
			} else {
				break;
			}
		}
		for (int a = xx, b = yy; xx < 15 && yy < 15; a--, b--) {
			if (chess1[a][b] == chess1[xx][yy]) {
				f++;
			} else {
				break;
			}

		}
		for (int c = xx - 1, d = yy + 1; xx < 15 && yy < 15; c--, d++) {
			if (chess1[c][d] == chess1[xx][yy]) {
				g++;
			} else {
				break;
			}
		}
		for (int c = xx, d = yy; xx < 15 && yy < 15; c++, d--) {
			if (chess1[c][d] == chess1[xx][yy]) {
				g++;
			} else {
				break;
			}

		}
		if (g > 4 || count2 > 4 || count3 > 4 || f > 4) {
			if (chess1[xx][yy] == 1)
				JOptionPane.showConfirmDialog(null, "黑方赢");// 界面完善操作。
			else {
				JOptionPane.showConfirmDialog(null, "白方赢");
			}

		}

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	

}
