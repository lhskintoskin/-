package com.jsu.wwt.vo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsu.wwt.dao.*;
import com.jsu.wwt.dbc.Connnection01;

public class User {
	static Connnection01 sq=new Connnection01();
	private  String uNumber;
	private String password;
	private  String userName;
	
	public User(String uNumber, String password, String userName) {
		super();
		this.uNumber = uNumber;
		this.password = password;
		this.userName = userName;
	}
	public String getuNumber() {
		return uNumber;
	}
	public void setuNumber(String uNumber) {
		this.uNumber = uNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "User [uNumber=" + uNumber + ", password=" + password + ", userName=" + userName +  "]";
	}
	/**
	 * 
	 * @param uNumber
	 * @param password
	 * @param userName
	 */
	
	
	public static void getUserDate() throws SQLException, ClassNotFoundException {
		
		Connection c = sq.getConnection();
		PreparedStatement stmt = null;
			
			String sql1 = "SELECT * FROM 贴吧用户表  where Unumber=? and password=?";//匹配
			stmt = c.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			rs.getString(3);
			rs.getString(2);
			rs.getString(1);
			}
			//DAO存储数据库数据，之后直接调用
	}