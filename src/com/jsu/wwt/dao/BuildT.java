package com.jsu.wwt.dao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jsu.wwt.dbc.Connnection01;
import com.jsu.wwt.vo.*;

public class BuildT  {
	/**
	 * 用户发帖
	 */
	public void build(User user) {
		
		JFrame sp = new JFrame("新建帖");
		sp.setSize(500, 500);
		sp.setLocation(400, 200);
		
		JPanel jpa = new JPanel();//内容面板
		
		JLabel topic = new JLabel("帖标题：");//标签
		topic.setBounds(10, 30, 200, 45);
		JTextField ttopic = new JTextField(30);
		ttopic.setBounds(10, 80, 410, 50);
		
		JLabel content = new JLabel("帖内容：");
		content.setBounds(10, 140, 200, 45);
		JTextField tcontent = new JTextField(200);
		tcontent.setBounds(10, 190, 410, 150);
		
		JButton publish = new JButton("发表");// 按钮
		publish.setBounds(360, 370, 80, 45);
	
		jpa.add(publish);
		jpa.add(topic);
		jpa.add(content);
		jpa.add(ttopic);
		jpa.add(tcontent);
		
		sp.add(jpa);
		jpa.setLayout(null);
		sp.setVisible(true);
		

		publish.addActionListener(new ActionListener() {
			int ERROR_MESSAGE;
			public void actionPerformed(ActionEvent e) {
				String s1 = ttopic.getText().toString();//读取输入的帖子标题
				String s2 = tcontent.getText().toString();//读取输入的帖子内容
//				int a=0;//
//				while(a<20){//
//				int b=new Random().nextInt(10000);//
//				String s= Integer.toString(b);//
				Connnection01 dbcs=new Connnection01();
				PreparedStatement ps1 = null;
				try {
					Connection ct = dbcs.getConnection();;
				if (s1.length() == 0 || s2.length() == 0) {
					JOptionPane.showMessageDialog(null, "未填写完整", "错误", ERROR_MESSAGE);
					}else {
					String sql3 = "INSERT INTO 帖子表 (postcontent,topic,username,mylike) VALUES (?,?,?,?)";
					ps1 = ct.prepareStatement(sql3);// 预编译SQL，减少sql执行
			ps1.setString(1, s2);//赋值语句，s给第一个？赋值s1
				ps1.setString(2, s1);
				ps1.setString(3,user.getUserName());//没有Usernme???
				ps1.setInt(4, 0);//点赞数初始化为0
//					ps1.setString(1, "加油加油");//10000个数据
//					ps1.setString(2, s);//
//					ps1.setString(3, "小明");//
//					ps1.setInt(4, 0);//
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "帖子发表成功");
					//a=a+1;//
					}
				}
				catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}
			}//}//
		});
		sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
