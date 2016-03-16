package six;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;

public class CF implements MouseListener,MouseMotionListener{
	Table tab;
	JLabel la=new JLabel();
	int x,y,a0,b0,a1,b1;//a0记录数组坐标,a1记录实际棋盘坐标
	boolean flag = true;
	int turn=1;//标记电脑先走
	
	CF(Table table)
	{
		tab=table;
		tab.g=tab.getContentPane().getGraphics();
		table.getContentPane().setLayout(new BorderLayout());
		table.getContentPane().add(la,BorderLayout.SOUTH);
		table.addMouseListener(this);
		table.addMouseMotionListener(this);
		tab.g.drawImage(Toolkit.getDefaultToolkit().getImage("white.jpg"),38+5*24-11,97+5*24-62,21,20,tab.getContentPane());
		tab.g.drawImage(Toolkit.getDefaultToolkit().getImage("black.jpg"),38+5*24-11,97+5*24-62,21,20,tab.getContentPane());
		
		x = 185;
		y = 240;
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
       if(flag){
    	   tab.g.drawImage(Toolkit.getDefaultToolkit().getImage("black.jpg"),a1-11,b1-62,21,20,tab.getContentPane());
    	   tab.qizi[a0][b0]=1;
       }
       
       turn = 0;//电脑走完第一步，轮到玩家
       PF p = new PF(tab);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
