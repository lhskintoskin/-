package com.jsu.wwt.dao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.jsu.wwt.dbc.Connnection01;
import com.jsu.wwt.vo.User;

public class Surf extends JFrame {//浏览帖子
	public static Vector rowData;
	public static Vector columnNames;
	/**
	 * 浏览帖子界面
	 */
	public void surf(User user) {
		//读
		String a=null,b = null,c=null;
		BufferedReader fr = null;
		try {
			fr = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\io.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line=null;
		int i=0;
		try {
			while((line=fr.readLine())!=null){
				
				if(i==0) {
					 a=line;
					i++;
			}else if(i==1) {
				 b=line;
				i++;
			}else {
				 c=line;
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	JFrame sp = new JFrame("浏览帖子");
sp.setSize(500, 500);
sp.setLocation(400, 200);
JPanel jpa = new JPanel();
JLabel laba = new JLabel(b);
JLabel labb = new JLabel("帖主:  " + a);
JTextArea jta = new JTextArea(c);
jta.setLineWrap(true);
laba.setFont(new Font("黑体", Font.BOLD, 22));
JButton comment = new JButton("评论");
JButton mylike = new JButton("点赞");
mylike.setBounds(60, 360, 80, 25);
jpa.add(mylike);
comment.setBounds(340, 360, 80, 25);
jpa.add(comment);
jpa.setLayout(null);
jpa.add(laba);
jpa.add(labb);
jpa.add(jta);
laba.setBounds(130, 10, 200, 20);
labb.setBounds(30, 25, 100, 20);
sp.add(jpa);
laba.setBounds(130, 10, 200, 20);
labb.setBounds(30, 25, 100, 20);
jta.setBounds(30, 60, 410, 250);
sp.setVisible(true);
//帖子评论
comment.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		new CommentT().comment(user);
		
	}
	
});
/**
 * 用户点赞帖子
 */
mylike.addActionListener(new ActionListener() {     //点赞帖子  功能
	Connnection01 con=new Connnection01();
	public void actionPerformed(ActionEvent e) {
		Connection ct=con.getConnection();
		String s=new getT().getUser();//获取username
		String sql3="select mylike from 帖子表 where username=? ";
		String sql4 = "UPDATE 帖子表  SET mylike=? where username=?";
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		try {
			ps1=ct.prepareStatement(sql3);
			ps1.setString(1, s);
			ResultSet rs = ps1.executeQuery();
			while(rs.next()) {
				ps2=ct.prepareStatement(sql4);
				int i=rs.getInt(1)+1;//点赞数mylike更新加一
				ps2.setInt(1,i );
				ps2.setString(2, s);
				ps2.executeUpdate();
				
			}
			JOptionPane.showMessageDialog(null,"点赞成功");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
});
	}

}
