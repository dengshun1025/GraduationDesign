package com.GraduationDesign1;

/*
 *   	数据处理
 *   	邓舜
 *   	2012-3-9
 */

import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class Methods 
{
	private static boolean  columnname1=true,columnname2=true;		//是否第一次创建列名
	private static boolean  first1=true,first2=true;                //是否第一次创建JTable
	private static Vector<Object> column,line1,data1,column1,line2,data2,column2;;
	private static ResultSet rs1=null;
	private static String table_name="";		//表名
	private static String sql="";				//sql语句

	public static String getTable_name() {
		return table_name;
	}

	public static void selectTable()
	{
		table_name=StartPanel.jtf1.getText().trim();
		//StartPanel.jtf1.setText("");
		sql="select * from [table_name]";
		sql=sql.replace("[table_name]", table_name);
		
		column1=new Vector<Object>();
		data1=new Vector<Object>();
		if(!first1)
		{
			columnname1=true;
		}
		try {
			rs1=SqlHelper.executeQuery(sql,null);
			ResultSetMetaData rsdata=rs1.getMetaData();
			while(rs1.next())
			{
				line1=new Vector<Object>();
				for(int i = 1 ; i<= rsdata.getColumnCount() ; i++)
				{
					//rsdata.getColumnCount();  //获得所有列的数目及实际列数
					if(columnname1)
					{
						column1.add( rsdata.getColumnName(i));  	//获得指定列的列名
						System.out.println(rsdata.getColumnName(i));
					}
					line1.add(rs1.getString(i));
					System.out.println(rs1.getString(i));
				}
				data1.add(line1);
				columnname1=false;
				//System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(!isTable())
				JOptionPane.showMessageDialog(null, "无此表,请检查", "ERROR", JOptionPane.ERROR_MESSAGE);
				SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());
		}
		if(first1)
		{
		StartPanel.jtb1=new JTable(data1, column1);
		StartPanel.jsp1=new JScrollPane(StartPanel.jtb1);
		StartPanel.jp3.add(StartPanel.jsp1);
		first1=false;
		}
		else
		{
			javax.swing.table.DefaultTableModel   model1   =   
					new   javax.swing.table.DefaultTableModel(data1,column1); 
			StartPanel.jtb1.setModel(model1); 
			StartPanel.jtb1.updateUI(); 
			System.out.println(1);
		}
	}
	
	public static void changeTable()
	{
		sql=SqlDesign.Sql();
//		sql="SELECT sid, sname, "
//				+ "SUM(CASE subject WHEN '语文' THEN score ELSE 0 END) AS 'Chinese', "
//				+ "SUM(CASE subject WHEN '数学' THEN score ELSE 0 END) AS 'Math', "
//				+ "SUM(CASE subject WHEN '英语' THEN score ELSE 0 END) AS 'English' "
//				+ "FROM [table_name] "
//				+ "GROUP BY sid,sname;";
		if(table_name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请先选择一张表", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else if(SqlDesign.isChange())
		{
		sql=sql.replace("[table_name]", table_name);
		
		column2=new Vector<Object>() ;
		data2=new Vector<Object>();
		if(!first2)
		{
			columnname2=true;
		}
		try {
			rs1=SqlHelper.executeQuery(sql,null);
			ResultSetMetaData rsdata=rs1.getMetaData();
			while(rs1.next())
			{
				line2=new Vector<Object>();
				for(int i = 1 ; i<= rsdata.getColumnCount() ; i++)
				{ 
					if(columnname2)
					{
						column2.add( rsdata.getColumnName(i) );  	//获得指定列的列名 
						//System.out.println(rsdata.getColumnName(i));
					}	
					line2.add(rs1.getString(i));
					//System.out.println(rs.getString(i));
				}
				data2.add(line2);
				columnname2=false;
				//System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());
		}
		if(first2)
		{
		StartPanel.jtb2=new JTable(data2, column2);
		StartPanel.jsp2=new JScrollPane(StartPanel.jtb2);
		StartPanel.jp4.add(StartPanel.jsp2);
		first2=false;
		//System.out.println(table_name);
		}
		else
		{
			javax.swing.table.DefaultTableModel   model2   =   
					new   javax.swing.table.DefaultTableModel(data2,column2); 
			StartPanel.jtb2.setModel(model2); 
			StartPanel.jtb2.updateUI(); 
			System.out.println(1);
		}
		}
	}

	public static boolean isTable()
	{
		sql = "SELECT table_name FROM information_schema.tables Where table_schema='mysql';";
		column=new Vector<Object>() ;
		try {
			rs1 = SqlHelper.executeQuery(sql, null);
			while (rs1.next()) {
				column.add(rs1.getString(1));
			}
			for (int i = 0; i < column.size(); i++) {
				if (table_name.trim().equals(column.get(i)))
					return true;
//				System.out.println(table_name+column.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
