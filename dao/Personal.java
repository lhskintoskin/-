package com.jsu.wwt.dao;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jsu.wwt.dbc.Connnection01;
import com.jsu.wwt.vo.*;

//普通用户

public class Personal extends JFrame{
//	static Vector rowData;
//	static Vector columnNames;
	public void person(User user) {
		JFrame sp = new JFrame("个人中心");
		sp.setSize(500, 500);
		sp.setLocation(750, 300);
		JLabel username = new JLabel("我的姓名：" +user.getUserName());
		JLabel Unumber = new JLabel("我的账号：" + user.getuNumber());
		JButton update = new JButton("编辑");
		JPanel jpa = new JPanel();
		sp.add(jpa);
		jpa.setLayout(null);
		update.setBounds(340, 340, 100, 55);// 编辑按钮
		jpa.add(update);
		jpa.add(username);
		jpa.add(Unumber);
		username.setBounds(120, 20, 180, 45);
		Unumber.setBounds(120, 90, 180, 45);
		sp.setVisible(true);
		// 编辑个人信息
		update.addActionListener(new ActionListener() {//编辑按钮监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("编辑个人信息");
				jf.setSize(350, 250);
				jf.setLocation(750, 200);
				JPanel jp = new JPanel();//内容面板
				jf.add(jp);
				jp.setLayout(null);
				JLabel acc = new JLabel("输入新帐号");
				JLabel pass = new JLabel("输入新密码");
				jp.add(pass);
				jp.add(acc);
				acc.setBounds(80, 10, 80, 25);
				pass.setBounds(80, 70, 80, 25);
				JTextField text1 = new JTextField();// 帐号输入
				JPasswordField text2 = new JPasswordField();// 密码输入
				text1.setBounds(80, 40, 165, 25);
				text2.setBounds(80, 100, 165, 25);
				jp.add(text1);
				jp.add(text2);
				JButton regButton = new JButton("修改");
				regButton.setBounds(125, 150, 80, 40);
				jp.add(regButton);
				jf.setVisible(true);
				Connnection01 sql = new Connnection01();
				regButton.addActionListener(new ActionListener() {//修改按钮监听器
					private int ERROR_MESSAGE;

					public void actionPerformed(ActionEvent e) {
						String s1 = text1.getText().toString();// 账号获取
						String s2 = new String(text2.getPassword());// 密码获取
						
						PreparedStatement stmt1 = null;
						PreparedStatement stmt2 = null;
						PreparedStatement stmt3 = null;
						PreparedStatement stmt4 = null;
						int i;// 注册限制
						try {
							Connection c = sql.getConnection();
							if (s1.length() == 0 || s2.length() == 0) {
								JOptionPane.showMessageDialog(null, "还有信息未填", "错误", ERROR_MESSAGE);
							}
							else {
								//所修改的用户信息是否重复
								String sql2 = "SELECT * FROM 贴吧用户表 where Unumber=?";
								stmt2 = c.prepareStatement(sql2);// 预编译SQL，减少sql执行
								stmt2.setString(1, s1);
								ResultSet rs2 = stmt2.executeQuery();// 执行查询语句
								if (!rs2.next()) {
									String sql4 = "UPDATE 贴吧用户表 SET Unumber=?,password=? WHERE username=?";
									stmt4 = c.prepareStatement(sql4);
									stmt4.setString(1, s1);
									stmt4.setString(2, s2);
									stmt4.setString(3,user.getUserName());
									stmt4.executeUpdate();
									JOptionPane.showMessageDialog(null, "修改成功");
								} else {
									JOptionPane.showMessageDialog(null, "账号已经存在", "错误", ERROR_MESSAGE);
								}
							}
						} catch (Exception e1) {
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
							System.exit(0);
						}
					}
				});
			}
		});
	}

}
