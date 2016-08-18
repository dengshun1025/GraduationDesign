package com.GraduationDesign1;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;

public class SqlDesign {

    private static ResultSet rs=null;
    private static Vector<String> column1=null,column2=null;
    private static String keycolumn="";
    private static String keyvaule="";
    private static boolean change =true;

    public static boolean isChange() {
		return change;
	}

	public static String Sql()
    //public static void main(String[] args)
    {
		change=true;
        String columns = "";
        String SUM = "";
        //查询表的列名
        String sql = "select COLUMN_NAME from information_schema.columns where table_name='[table_name]';";
        sql=sql.replace("[table_name]", Methods.getTable_name());
        column1 = new Vector<String>();
        column2 = new Vector<String>();
        if(Methods.isTable())
        {
            try {
                rs = SqlHelper.executeQuery(sql, null);
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    column1.add(rs.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < column1.size(); i++) {
                //查询需要转换的列
                sql = "select value from transform where keyword = '[key]';";
                sql = sql.replace("[key]", column1.get(i));
                try {
                    rs = SqlHelper.executeQuery(sql, null);
                    while (rs.next()) {
                        System.out.println(sql);
                        keycolumn = column1.get(i);
                        keyvaule = rs.getString(1).trim();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(!keycolumn.equals(""))
            {
            sql = "select distinct [keycolumn] from [table];";
            sql = sql.replace("[keycolumn]", keycolumn);
            sql = sql.replace("[table]", Methods.getTable_name());
            System.out.println(sql);
            try {
                rs = SqlHelper.executeQuery(sql, null);
                while (rs.next()) {
                    column2.add(rs.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "SELECT [columns] " + "[SUM] " + "FROM [table_name] " + "GROUP BY [columns]";
            for (int i = 0; i < column1.size(); i++) {
                if (!column1.get(i).equals(keycolumn) && !column1.get(i).equals(keyvaule))
                    columns += column1.get(i) + ',';
            }
            for (int i = 0; i < column2.size(); i++) {
                SUM += "SUM(CASE " + keycolumn.trim() + " WHEN "
                        + "'" + column2.get(i).trim() + "'" + " THEN " + keyvaule.trim()
                        + " ELSE 0 END) AS " + "'" + column2.get(i).trim() + "'" + ",";
            }
            SUM = SUM.substring(0, SUM.length() - 1);
            sql = sql.replace("[columns]", columns);
            sql = sql.replace("[SUM]", SUM);
            sql = sql.replace("[table_name]", Methods.getTable_name());
            sql = sql.substring(0, sql.length() - 1)+";";
            System.out.println(sql);
            }
            else
            {
            	change=false;
            	JOptionPane.showMessageDialog(null, "无需转换", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        keycolumn="";
        keyvaule="";
//        for (int i=0;i<column.size();i++)
//            System.out.println(column.get(i));
        return sql;
    }

}
