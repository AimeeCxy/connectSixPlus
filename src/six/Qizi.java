package six;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Qizi implements MouseListener,MouseMotionListener{

	Table tab;
	JLabel la=new JLabel();
	int x,y,a0,b0,a1,b1;//
	boolean flag;
	
	Qizi(Table table)
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
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
		flag=true;//表示该位置无棋子，可以下棋
		x=e.getX();
		y=e.getY();
		System.out.println("x="+x+" "+"y="+y);
		setLocation();
	}

	public void setLocation()
	{
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
			else flag=false;//不在范围，不能落子
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
	public void paint(Graphics g){         //
	//	System.out.println(tab.qizi[a0][b0]);
	//	System.out.println("a0="+a0+" "+"b0="+b0);
	//	System.out.println("a1="+a1+" "+"b1="+b1);
		if(tab.color=="white"&&tab.qizi[a0][b0]!=0&&tab.qizi[a0][b0]!=1)//
		{
			g.drawImage(Toolkit.getDefaultToolkit().getImage("white.jpg"),a1-11,b1-62,21,20,tab.getContentPane());
			//System.out.println("white");
			tab.qizi[a0][b0]=0;
			tab.image_x[a0][b0]=a1-11;
			tab.image_y[a0][b0]=b1-62;
			if(isVictor(a0,b0,0))
			{
				JOptionPane.showMessageDialog(null,"白棋选手，恭喜你赢了");
			}
			tab.color="black";//下一颗棋子的颜色相反//a1-11,b1-62
		}
		else if(tab.qizi[a0][b0]!=0&&tab.qizi[a0][b0]!=1)
		{
		    g.drawImage(Toolkit.getDefaultToolkit().getImage("black.jpg"),a1-11,b1-62,21,20,tab.getContentPane());
		    tab.qizi[a0][b0]=1;
		    if(isVictor(a0,b0,1))
			{
				JOptionPane.showMessageDialog(null,"黑棋选手，恭喜你赢了");
			}
		    tab.color="white";
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		la.setText("鼠标移动的坐标["+e.getX()+","+e.getY()+"]");
		
	}
	
	boolean isVictor(int x,int y,int color)//
	{
		int countQizi=1;
		//
		for(int i=x-1;i>=0&&tab.qizi[i][y]==color;i--)//
		{
				countQizi++;
				if(countQizi==6)
					return true;
		}
		for(int i=x+1;i<=18&&tab.qizi[i][y]==color;i++)//�ұ�
		{
				countQizi++;
				if(countQizi==6)
					return true;
		}
		//
		
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
		//
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

}
