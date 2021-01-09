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

public class Surf extends JFrame {//�������
	public static Vector rowData;
	public static Vector columnNames;
	/**
	 * ������ӽ���
	 */
	public void surf(User user) {
		//��
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
		
	JFrame sp = new JFrame("�������");
sp.setSize(500, 500);
sp.setLocation(400, 200);
JPanel jpa = new JPanel();
JLabel laba = new JLabel(b);
JLabel labb = new JLabel("����:  " + a);
JTextArea jta = new JTextArea(c);
jta.setLineWrap(true);
laba.setFont(new Font("����", Font.BOLD, 22));
JButton comment = new JButton("����");
JButton mylike = new JButton("����");
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
//��������
comment.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		new CommentT().comment(user);
		
	}
	
});
/**
 * �û���������
 */
mylike.addActionListener(new ActionListener() {     //��������  ����
	Connnection01 con=new Connnection01();
	public void actionPerformed(ActionEvent e) {
		Connection ct=con.getConnection();
		String s=new getT().getUser();//��ȡusername
		String sql3="select mylike from ���ӱ� where username=? ";
		String sql4 = "UPDATE ���ӱ�  SET mylike=? where username=?";
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		try {
			ps1=ct.prepareStatement(sql3);
			ps1.setString(1, s);
			ResultSet rs = ps1.executeQuery();
			while(rs.next()) {
				ps2=ct.prepareStatement(sql4);
				int i=rs.getInt(1)+1;//������mylike���¼�һ
				ps2.setInt(1,i );
				ps2.setString(2, s);
				ps2.executeUpdate();
				
			}
			JOptionPane.showMessageDialog(null,"���޳ɹ�");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
});
	}

}
