package com.jsu.wwt.dao;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jsu.wwt.dbc.Connnection01;
import com.jsu.wwt.vo.User;

public class CommentT {
	/**
	 * �û���������
	 */
	public void comment(User user) {
		
		JFrame sp = new JFrame("������");
		sp.setSize(500, 500);
		sp.setLocation(400,200);
		JTextField text=new JTextField(30);
		JLabel myc=new JLabel("�������۰ɣ�");
		JTextArea jta=new JTextArea();
		jta.setLineWrap(true);//�Զ����У��������ֱȿؼ��Ŀ�Ȼ���ʱ���Զ�����  
		JPanel jpa=new JPanel();
		JButton publish=new JButton("����");
		sp.add(jpa);
		jpa.setLayout(null);
		publish.setBounds(360, 370, 80, 45);//��ť
		jpa.add(publish);
		jpa.add(text);
		jpa.add(myc);//����
		jpa.add(jta);
		text.setBounds(10, 370, 320, 45);//���ۿ�
		myc.setBounds(10, 340, 80, 25);//����ǰ��
		jta.setBounds(10, 10, 430, 320);//���������
		sp.setVisible(true);
		
		Connnection01 sql=new Connnection01();
		PreparedStatement ps1=null;
		try {
			Connection ct1=sql.getConnection();				
			ps1=ct1.prepareStatement("select username,comcontent from �������۱�");					
			ResultSet rs=ps1.executeQuery();
			while (rs.next()) {
				String username=rs.getString(1);
				String comcontent=rs.getString(2);
				jta.append(username+":  ");
				jta.append(comcontent+"  \n");
				jpa.repaint();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		
		publish.addActionListener(new ActionListener() {
	            private int ERROR_MESSAGE;
	            public void actionPerformed(ActionEvent e) {
	            	String s1 = text.getText().toString();//���ۻ�ȡ
					PreparedStatement ps1=null;
					try {
						Connection ct=sql.getConnection();
						if (s1.length() == 0) {
						JOptionPane.showMessageDialog(null, "δ��д����", "����", ERROR_MESSAGE);
						}else {
						String sql3 = "INSERT INTO �������۱� (comcontent,username) VALUES (?,?)";
						ps1 = ct.prepareStatement(sql3);//Ԥ����SQL������sqlִ��
						ps1.setString(1, s1);//��ֵ��䣬����һ������ֵs1
						ps1.setString(2, user.getUserName());
						ps1.executeUpdate();
						JOptionPane.showMessageDialog(null, "���۷���ɹ���");
						//WNotice.writeNotice();
						jpa.repaint();
						}
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
	            }
	        });
		
		//sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
