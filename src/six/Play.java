package six;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;

public class Play implements MouseListener{
	
	
    Table table=new Table();
    
	Play(){
		table.g=table.getContentPane().getGraphics();
		table.psAndPs.addMouseListener(this);//人人对战
		table.psFs.addMouseListener(this);//玩家先走
		table.cpFs.addMouseListener(this);//电脑先走
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) //
	{
		
		if(e.getSource()==table.psAndPs)
		{   
			clearChessBoard();
			personAndPersonGame();
		   
		}
		else if(e.getSource()==table.psFs)
		{
			clearChessBoard();
			personFirstThanComputerGame();
		}
		else if(e.getSource()==table.cpFs)
			clearChessBoard();
			computerFirstThanPersonGame();
	}

	void clearChessBoard()//清屏
	{
		for(int i=0;i<18;i++)
			for(int j=0;j<18;j++)
				if(table.qizi[i][j]==0||table.qizi[i][j]==1)
				{
					table.qizi[i][j]=-1;
				}
		table.g.clearRect(0, 0, 514,600);
		table.initate();
	}


	void personAndPersonGame(){//人人对战
		Qizi qizi=new Qizi(table);
	}
	void personFirstThanComputerGame(){//人机对战-玩家先行
		PF pf=new PF(table);
	}
	void computerFirstThanPersonGame(){//人机对战-电脑先行
		CF cf=new CF(table);
	}
	public static void main(String[] args)
	{
		Play play=new Play();
	}
	
}
