package com.jsu.wwt.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connnection01{
	//����SQLServer���ݿ���������
	private static final String DBRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//����SQLServer���ݿ����ӵ�ַ��testdb�ɸĳ��Լ������ݿ�����
	private static final String DBURL="jdbc:sqlserver://localhost:1433;DatabaseName=note";
	private static final String DBUSER="sa"; //SQLServer���ݿ������û���
	private static final String PASSWORD="lh1990420"; //SQLServer���ݿ���������
	private Connection conn=null; //�������Ӷ���
	public Connnection01(){//���췽���������ݿ�
		try {
			Class.forName(DBRIVER);
			this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
			//System.out.println("sucess");
		} catch (Exception e) {e.printStackTrace();}
	}
	public Connection getConnection() {//�������ݿ����Ӷ���
		return this.conn;
	}
	public void close() {//�ر���������
		if(this.conn!=null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
public  static void main(String[] args) {
	new Connnection01();
}
}

