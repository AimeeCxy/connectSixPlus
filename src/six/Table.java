package six;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Table extends JFrame implements ActionListener{

    JMenuBar menubar;
	JMenu game,psAndCp,help;//人机菜单
	JMenuItem psAndPs,exit,psFs,cpFs,rule;//人人菜单项
	int qizi[][]=new int[19][19];//棋盘
	int image_x[][]=new int[19][19];//记录已落子的图片的x坐标
	int image_y[][]=new int[19][19];//记录已落子的图片的y坐标
	String color="black";//棋子颜色,黑棋先下
	Graphics g;//初始化在Play类构造函数中
	
	
	Table(){
		super("六子棋");
		menubar=new JMenuBar();
		game=new JMenu("游戏");
		help=new JMenu("帮助");
		rule=new JMenuItem("游戏规则");
		psAndPs=new JMenuItem("人人对战");
		psAndCp=new JMenu("人机对战");
		exit=new JMenuItem("退出");
		psAndPs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke('X'));
		rule.addActionListener(this);
		game.add(psAndPs);
		game.add(psAndCp);
		psAndCp.add(psFs=new JMenuItem("玩家先走"));
		psAndCp.add(cpFs=new JMenuItem("电脑先走"));
		game.add(exit);
		help.add(rule);
		psFs.setAccelerator(KeyStroke.getKeyStroke('P'));
		cpFs.setAccelerator(KeyStroke.getKeyStroke('C'));
		menubar.add(game);
		menubar.add(help);
		exit.addActionListener(this);
		setJMenuBar(menubar);
		Icon bug = new ImageIcon( "chessboard.jpg" );
		JLabel lab=new JLabel(bug);
		Container container = getContentPane();
		container.setLayout( new FlowLayout() );
		container.add( lab );
		setSize(514,600);
		setLocation(420,80);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initate();
	}
	
	void initate()//初始化棋盘
	{
		Container container = getContentPane();
		Icon bug = new ImageIcon( "chessboard.jpg" );
		JLabel lab=new JLabel(bug);
		container.setLayout( new FlowLayout() );
		container.add( lab );
		setSize(514,600);
		setLocation(420,80);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i=0;i<19;i++)
			for(int j=0;j<19;j++)
				qizi[i][j]=-1;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==exit)
		{
			System.exit(0);
		}
		if(e.getSource()==rule){
			JOptionPane.showMessageDialog(null,"   玩家可选择人机对战和人人对\n战两种模式，最先在棋盘横向、\n竖向、斜向形成连续的相同色六\n个棋子的一方为胜。","游戏规则", JOptionPane.INFORMATION_MESSAGE, null);
		}
	}
	
	
}
