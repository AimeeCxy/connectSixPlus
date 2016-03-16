package six;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;

public class PF implements MouseListener,MouseMotionListener{
	Table tab;
	JLabel la=new JLabel();
	int x,y,a0,b0,a1,b1;//a0记录数组坐标,a1记录实际棋盘坐标
	boolean flag;
	int turn=0;//标记玩家先走
	
	PF(Table table)
	{
		tab=table;
		tab.g=tab.getContentPane().getGraphics();
		table.getContentPane().setLayout(new BorderLayout());
		table.getContentPane().add(la,BorderLayout.SOUTH);
		table.addMouseListener(this);
		table.addMouseMotionListener(this);
		tab.g.drawImage(Toolkit.getDefaultToolkit().getImage("white.jpg"),8,60,21,20,tab.getContentPane());
		tab.g.drawImage(Toolkit.getDefaultToolkit().getImage("black.jpg"),8,60,21,20,tab.getContentPane());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		la.setText("鼠标移动的坐标["+e.getX()+","+e.getY()+"]");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		flag=true;//true表示鼠标点击有效点
		x=e.getX();
		y=e.getY();
		System.out.println("x="+x+" "+"y="+y);
		if(turn==1){//电脑落子
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			bestStep(tab.g);
		}
		if(turn==0)//玩家落子
			setLocation();
	}

	private void bestStep(Graphics g) {
		int value[][]=new int[19][19];
		for(int i=0;i<19;i++){
			for(int j=0;j<19;j++){
				if(tab.qizi[i][j]!=0&&tab.qizi[i][j]!=1)//当该点无棋子时，估值
					value[i][j] = setValue(i,j);
			}
		}
		int maxi = 0;
		int maxj = 0;
		for(int i=0;i<19;i++){
			for(int j=0;j<19;j++){
				if(value[i][j]>value[maxi][maxj]){
					maxi = i;
					maxj = j;
				}
			}
		}
		g.drawImage(Toolkit.getDefaultToolkit().getImage("white.jpg"),38+maxi*24-11,97+maxj*24-62,21,20,tab.getContentPane());
		tab.qizi[maxi][maxj]=0;//该位置标记为白棋
		if(isVictor(maxi,maxj,0))
		{
			JOptionPane.showMessageDialog(null,"很遗憾，电脑赢了");
		}
		else{
			turn=0;//电脑走完轮到玩家走
		}
	}

	private int setValue(int x, int y) {
		int value=0;
		int FirstSame=0;
		int SecondSame=0;
		int ThirdSame=0;
		for(int i=0;i<19;i++){
			for(int j=0;j<19;j++){
				for(int k=x-1;k>=0&&tab.qizi[k][y]==1;k--)
				{
					FirstSame++;
				}
				value = value + FirstSame;
				if(x-FirstSame-1>=0){
					if(tab.qizi[x-FirstSame-1][y]==0)//如果另一端是一个白棋，则value降一分
						value = value - 1;
				}
				FirstSame = 0;
				for(int k=x+1;k<=18&&tab.qizi[k][y]==1;k++)
				{
					FirstSame++;
				}
				value = value + FirstSame;
				if(x+FirstSame+1<=18){
					if(tab.qizi[x+FirstSame+1][y]==0)
						value = value - 1;
				}
				for(int k=y-1;k>=0&&tab.qizi[x][k]==1;k--)
				{
					SecondSame++;
				}
				value = value + SecondSame*2;
				if(y-SecondSame-1>=0){
					if(tab.qizi[x][y-SecondSame-1]==0)
						value = value - 2;
				}
				SecondSame = 0;
				for(int k=y+1;k<=18&&tab.qizi[x][k]==1;k++)
				{
					SecondSame++;
				}
				value = value + SecondSame*2;
				if(y+SecondSame+1<=18){
					if(tab.qizi[x][y+SecondSame+1]==0)
						value = value - 2;
				}
				for(int k=x-1,h=y-1;k>=0&&h>=0&&tab.qizi[k][h]==1;k--,h--)
				{
					ThirdSame++;
				}
				value = value + ThirdSame*3;
				if(x-ThirdSame-1>=0&&y-ThirdSame-1>=0){
					if(tab.qizi[x-ThirdSame-1][y-ThirdSame-1]==0)
						value = value - 3;
				}
				ThirdSame = 0;
				for(int k=x+1,h=y+1;k<=18&&h<=18&&tab.qizi[k][h]==1;k++,h++)
				{
					ThirdSame++;
				}
				value = value + ThirdSame*3;
				if(x+ThirdSame+1<=18&&y+ThirdSame+1<=18){
					if(tab.qizi[x+ThirdSame+1][y+ThirdSame+1]==0)
						value = value - 3;
				}
				ThirdSame = 0;
				for(int k=x+1,h=y-1;k<=18&&h>=0&&tab.qizi[k][h]==1;k++,h--)
				{
					ThirdSame++;
				}
				value = value + ThirdSame*3;
				if(x+ThirdSame+1<=18&&y-ThirdSame-1>=0){
					if(tab.qizi[x+ThirdSame+1][y-ThirdSame-1]==0)
						value = value - 3;
				}
				ThirdSame = 0;
				for(int k=x-1,h=y+1;k>=0&&h<=18&&tab.qizi[k][h]==1;k--,h++)
				{
					ThirdSame++;
				}
				value = value + ThirdSame*3;
				if(x-ThirdSame-1>=0&&y+ThirdSame+1<=18){
					if(tab.qizi[x-ThirdSame-1][y+ThirdSame+1]==0)
						value = value - 3;
				}
			}
		}
		return value;
	}

	private void setLocation() {
		if(32<=x&&x<=472&&91<=y&&y<=531)
		{
			a0=Math.abs(x-38)/24;
			if(Math.abs(x-38)%24>14)
			{	
				a0=a0+1;
				a1=38+a0*24;
			}
			else if(Math.abs(x-38)%24<10)
					a1=38+a0*24;
			else flag=false;//横坐标与棋盘上点距离在10-14之间的点无效
		   b0=Math.abs(y-97)/24;
	       if(Math.abs(y-97)%24>14)
		   {	
	    	   b0=b0+1;
	    	   b1=97+b0*24;
		   }
			else if(Math.abs(y-97)%24<10)
				b1=97+b0*24;
			else flag=false;
	       if(flag)
	    	   paint(tab.g);
		}
	}

	private void paint(Graphics g) {
		if(tab.qizi[a0][b0]!=0&&tab.qizi[a0][b0]!=1)//不能在同一点落子
		{
			g.drawImage(Toolkit.getDefaultToolkit().getImage("black.jpg"),a1-11,b1-62,21,20,tab.getContentPane());
			tab.qizi[a0][b0]=1;//标记该位置为黑棋
			if(isVictor(a0,b0,1))
			{
				JOptionPane.showMessageDialog(null,"恭喜你赢了");
			}
			else
				turn = 1;
		}
	}

	private boolean isVictor(int x, int y, int color) {
		int countQizi=1;
		//横向判断
		for(int i=x-1;i>=0&&tab.qizi[i][y]==color;i--)//计算该棋子的左边连续同色棋子数
		{
				countQizi++;
				if(countQizi==6)
					return true;
		}
		for(int i=x+1;i<=18&&tab.qizi[i][y]==color;i++)//右边
		{
				countQizi++;
				if(countQizi==6)
					return true;
		}
		//纵向判断
		
		for(int i=y-1;i>=0&&tab.qizi[x][i]==color;i--)
		{
			countQizi++;
			if(countQizi==6)
				return true;
		}
		for(int i=y+1;i>=18&&tab.qizi[x][i]==color;i++)
		{
			countQizi++;
			if(countQizi==6)
				return true;
		}
		//对角判断
		countQizi=1;
		for(int i=x-1,j=y-1;i>=0&&j>=0&&tab.qizi[i][j]==color;i--,j--)
		{
			countQizi++;
			if(countQizi==6)
				return true;
		}
		for(int i=x+1,j=y+1;i<=18&&j<=18&&tab.qizi[i][j]==color;i++,j++)
		{
			countQizi++;
			if(countQizi==6)
				return true;
		}
		//
		countQizi=1;
		for(int i=x+1,j=y-1;i<=18&&j>=0&&tab.qizi[i][j]==color;i++,j--)
		{
			countQizi++;
			if(countQizi==6)
				return true;
		}
		for(int i=x-1,j=y+1;i>=0&&j<=18&&tab.qizi[i][j]==color;i--,j++)
		{
			countQizi++;
			if(countQizi==6)
				return true;
		}
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
