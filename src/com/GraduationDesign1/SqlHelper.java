package com.GraduationDesign1;

/*
 *   	Êý¾Ý¿â²Ù×÷
 *   	µËË´
 *   	2012-3-9
 */

import java.sql.*;

public class SqlHelper {

	private static String driver=null;
	private static String url=null;
	private static String user=null;
	private static String password=null;
	private static Connection ct=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	
	public static Connection getCt() {
		return ct;
	}
	public static void setCt(Connection ct) {
		SqlHelper.ct = ct;
	}
	public static PreparedStatement getPs() {
		return ps;
	}
	public static ResultSet getRs() {
		return rs;
	}
	
	static {
			driver=LoginDataBase.getDriver();
			url=LoginDataBase.getUrl();
			user=LoginDataBase.getUser();
			password=LoginDataBase.getPassword();
			//System.out.println(user+" "+password);
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} 
		
	public static void close(ResultSet rs,Statement sm,Connection ct)
	{
			try {
				if(rs!=null)
				    rs.close();
				if(sm!=null)
					sm.close();
				if(ct!=null)
					ct.close();
				rs=null;
				sm=null;
				ct=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void executeUpdate(String sql,String para[])
	{
		try {
			ct=DriverManager.getConnection(url,user,password);
			ps=ct.prepareStatement(sql);
			if(para!=null)
			{
				for(int i=0;i<para.length;i++)
				{
					ps.setString(i+1, para[i]);
				}
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			//close(rs, ps, ct);
		}
	}
	
	public static ResultSet executeQuery(String sql,String para[])
	{
		try {
			ct=DriverManager.getConnection(url,user,password);
			ps=ct.prepareStatement(sql);
			if(para!=null)
			{
				for(int i=0;i<para.length;i++)
				{
					ps.setString(i+1,para[i]);
				}
			}
			//System.out.println(sql);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			//close(rs, ps, ct);
		}
		return rs;
	}
}
