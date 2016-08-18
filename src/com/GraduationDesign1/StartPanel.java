package com.GraduationDesign1;

/*
 *   	开始界面
 *   	邓舜
 *   	2012-3-9
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class StartPanel extends JFrame implements ActionListener
{
	 static JPanel jp1,jp2, jp3,jp4,jp5;
	 static JButton jb1,jb2,jb3;
	 static JTextField jtf1;
	 static JTable jtb1,jtb2;
	 static JScrollPane jsp1,jsp2;
	 CardLayout card=new CardLayout();
	
	public StartPanel()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		
		jb1=new JButton("查询");
		jb2=new JButton("转换");
		jb3=new JButton("退出");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		
		jtf1=new JTextField("Score",20);
		
		jp1.add(jtf1);
		jp1.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		
		jp5.setLayout(card);
		
		jp5.add(jp3,"0");
		jp5.add(jp4,"1");
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp2,BorderLayout.SOUTH);
		this.add(jp5,BorderLayout.CENTER);
		
		this.setTitle("数据库行列转换");
		this.setSize(600,550);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getActionCommand().equals("查询"))
		{
			Methods.selectTable();
			jp3.updateUI();
			card.show(jp5, "0");
		}
		else if(arg0.getActionCommand().equals("转换"))
		{
			Methods.changeTable();
			jp4.updateUI();
			if(SqlDesign.isChange())
			{
			card.show(jp5, "1");
			}
		}
		else if(arg0.getActionCommand().equals("退出"))
		{
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		new StartPanel();
	}
}
