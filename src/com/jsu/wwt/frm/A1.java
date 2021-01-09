package com.jsu.wwt.frm;

import javax.swing.*;


import com.jsu.wwt.dao.*;
import com.jsu.wwt.dbc.Connnection01;
import com.jsu.wwt.vo.*;

import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.sql.*;
class BigMenu {

	 Connnection01 sq=new Connnection01();

	private JFrame frame;
	private JPanel jPanel=new JPanel();//内容面板 ;

	private JLabel accout;
	private JLabel password;
	private JLabel showInfo = new JLabel();		
	private JTextField text1;
	private JPasswordField text2;
	private JButton loginButton;
	private JButton regButton;
	//private JComboBox comBox = new JComboBox();
	private JLabel txt;
	private LayoutManager gridLayout;
	int a;
	
	/**
	 * 登录界面
	 */
	public void BigMenu() {
		JFrame frame = new JFrame("贴吧登录界面");

		JLabel accout = new JLabel("帐号");
		JTextField text1 = new JTextField(16);
		JLabel password = new JLabel("密码");
		JPasswordField text2 = new JPasswordField(16);//密码框
		JButton loginButton = new JButton("登录");
		JButton regButton = new JButton("注册");
		JLabel txt = new JLabel("请选择登录身份:");
		// txt.setFont(new Font("楷体",Font.BOLD,18));

		frame.setSize(370, 280);
		frame.setLocation(750, 300);
		// frame.setResizable(false);
		// jPanel.setLayout(null);
//		ImageIcon imageIcon=new ImageIcon("C:\\Users\\23641\\Pictures\\Saved Pictures\\Image.jpg");//插入图片
//		JLabel JlabelImage=new JLabel(imageIcon);
		
		jPanel.setLayout(null);
		frame.add(jPanel);

		// 帐号输入
		jPanel.add(accout);
		accout.setBounds(70, 40, 80, 25);
		text1.setBounds(120, 40, 165, 25);
		jPanel.add(text1);

		
		// 密码输入
		jPanel.add(password);
		password.setBounds(70, 75, 80, 25);
		text2.setBounds(120, 75, 165, 25);
		jPanel.add(text2);

		// 登录按钮
		loginButton.setBounds(80, 120, 80, 25);
		jPanel.add(loginButton);

		// 注册按钮
		regButton.setBounds(200, 120, 80, 25);
		jPanel.add(regButton);

		txt.setBounds(85, 170, 100, 25);
		jPanel.add(txt);

//		comBox.addItem("--请选择--"); // 向下拉列表中添加一项
//		comBox.addItem("贴吧用户");
//		comBox.addItem("管理员");
	
		 String[] listData = new String[]{"--请选择--","贴吧用户", "管理员"};
		 final JComboBox<String> comBox = new JComboBox<String>(listData);
		 jPanel.add(comBox);
		 comBox.setBounds(190, 170, 80, 25);
		comBox.addItemListener(new MyItemListener());
		
		 // 添加条目选中状态改变的监听器
		 comBox.addItemListener(new ItemListener() { 

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					 a=comBox.getSelectedIndex();
				}
				
			}
			 
		 });
		frame.setVisible(true);

		loginButton.addActionListener(new ActionListener() {//登录按钮监听器
			private int ERROR_MESSAGE;//返回一个指向包含错误消息的字符串的指针；如果无错误指示，则返回 NULL。
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(a==0) 
				{
					JOptionPane.showMessageDialog(null, "请选择身份！", "错误", ERROR_MESSAGE);
				}
				if (a == 1) {//贴吧普通用户登录操作
					String s1 = text1.getText().toString();//toString???
					String s2 = new String(text2.getPassword());
					try {
						Connection c = sq.getConnection();//连接数据库
						String sql1 = "SELECT * FROM 贴吧用户表 where Unumber=? and password=?";
						PreparedStatement stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						
						//判断用户输入的信息
						if (s1.length() != 0 && s2.length() != 0) {
							if (rs.next()) {
								 User user = new User(rs.getString(3), rs.getString(2), rs.getString(1));								 
								
								JOptionPane.showMessageDialog(null, "登陆成功");
								//进入贴吧主界面
								Interface dff = new Interface();
								dff.interf(user);//传user
	
							} else {
								JOptionPane.showMessageDialog(null, "帐号或密码有误", "错误", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {
							JOptionPane.showMessageDialog(null, "请输入帐号或密码", "错误", ERROR_MESSAGE);
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
				}
				if (a == 2) {//贴吧管理员登录
					String s1 = text1.getText().toString();
					String s2 = new String(text2.getPassword());
					try {
						Connection c = sq.getConnection();//连接数据库
						String sql1 = "SELECT * FROM 贴吧管理员表 where Mnumber=? and password=?";
						PreparedStatement stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						if (s1.length() != 0 && s2.length() != 0) {
							if (rs.next()) {
								//String mNumber, String managername, String password
								Manager man = new Manager(rs.getString(1),rs.getString(2),rs.getString(3));
								JOptionPane.showMessageDialog(null, "登陆成功");
								ManagerT mana = new ManagerT();//管理员操作界面(可以删除帖子)
								mana.manager(man);
							} else {
								JOptionPane.showMessageDialog(null, "帐号或密码有误", "错误", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {
							JOptionPane.showMessageDialog(null, "请输入帐号或密码", "错误", ERROR_MESSAGE);
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
				}
			}
		});

		regButton.addActionListener(new ActionListener() {//注册按钮监听器
			private int ERROR_MESSAGE;

			public void actionPerformed(ActionEvent e) {
				if (a == 1) {
					A3 re = new A3();//进入注册
					re.reg();
				} else {
					JOptionPane.showMessageDialog(null, "只有贴吧用户才能注册！", "错误", ERROR_MESSAGE);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class MyItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			String str = e.getItem().toString();//获取下拉框内容
			showInfo.setBounds(5, 0, 150, 25);
			jPanel.add(showInfo);
			if (a == 1 || a == 2) {
				showInfo.setText("尊敬的" + str + "，请登录");
			} else {
				showInfo.setText("请选择身份！");
			}
		}
	}

}

public class A1 {

	public static void main(String[] args) {
		new BigMenu().BigMenu();

	}

}
