package com.GraduationDesign1;

import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

@SuppressWarnings("serial")
public class LoginDataBase extends JFrame implements ActionListener{
	
			private static JPanel jp;
			private static JButton jb1,jb2;
			private static JLabel jlb1,jlb2,jlb3;
			private static JTextField jtf1,jtf2;
			private static JPasswordField jpf;
			private static String driver="";
			private static String url="";
			private static String user="";
			private static String password="";
			
			static
			{
				driver="com.mysql.jdbc.Driver";
				url="jdbc:mysql://localhost:3306/[DatabaseName]";
				user="[user]";
				password="[password]";
			}

			public static String getDriver() {
				return driver;
			}

			public static String getUrl() {
				return url;
			}

			public static String getUser() {
				return user;
			}

			public static String getPassword() {
				return password;
			}
			
			public LoginDataBase()
			{
				jp=new JPanel();
				jb1=new JButton("µÇÂ¼");
				jb2=new JButton("ÍË³ö");
				jb1.addActionListener(this);
				jb2.addActionListener(this);
				jtf1=new JTextField("mysql");
				jtf2=new JTextField("root");
				jpf=new JPasswordField("2008");
				jlb1=new JLabel("Êý¾Ý¿âÃû:");
				jlb2=new JLabel("ÓÃ»§Ãû:");
				jlb3=new JLabel("ÃÜÂë:");
								
				jp.setLayout(null);
				jp.add(jlb1).setBounds(30, 50, 200, 30);
				jp.add(jlb2).setBounds(40, 120, 200, 30);
				jp.add(jlb3).setBounds(50, 190, 200, 30);
				jp.add(jtf1).setBounds(100, 50, 200, 30);
				jp.add(jtf2).setBounds(100, 120, 200, 30);
				jp.add(jpf).setBounds(100, 190, 200, 30);
				jp.add(jb1).setBounds(110, 250, 80, 30);
				jp.add(jb2).setBounds(210, 250, 80, 30);
				
				this.add(jp);
				
				this.setTitle("SQLServerµÇÂ¼");
				this.setSize(400,360);
				this.setVisible(true);
				this.setResizable(false);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("µÇÂ¼"))
				{
					url=url.replace("[DatabaseName]", jtf1.getText().trim());
					user=user.replace("[user]", jtf2.getText().trim());
					password=password.replace("[password]", jpf.getText().trim());
					System.out.println(user+" "+password+" "+url);
					try {
						SqlHelper.setCt(DriverManager.getConnection(url,user,password));
						JOptionPane.showMessageDialog(null, "µÇÂ¼³É¹¦£¡", "Welcome", JOptionPane.DEFAULT_OPTION);
						new StartPanel();
						this.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü£¬Çë¼ì²é£¡", "ERROR", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
						e1.printStackTrace();
					}
				}
				else if(e.getActionCommand().equals("ÍË³ö"))
				{
					System.exit(0);
				}
			}
			
			public static void main(String[] args)
			{
				new LoginDataBase();
			}
}
