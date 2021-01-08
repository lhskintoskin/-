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

//��ͨ�û�

public class Personal extends JFrame{
//	static Vector rowData;
//	static Vector columnNames;
	public void person(User user) {
		JFrame sp = new JFrame("��������");
		sp.setSize(500, 500);
		sp.setLocation(750, 300);
		JLabel username = new JLabel("�ҵ�������" +user.getUserName());
		JLabel Unumber = new JLabel("�ҵ��˺ţ�" + user.getuNumber());
		JButton update = new JButton("�༭");
		JPanel jpa = new JPanel();
		sp.add(jpa);
		jpa.setLayout(null);
		update.setBounds(340, 340, 100, 55);// �༭��ť
		jpa.add(update);
		jpa.add(username);
		jpa.add(Unumber);
		username.setBounds(120, 20, 180, 45);
		Unumber.setBounds(120, 90, 180, 45);
		sp.setVisible(true);
		// �༭������Ϣ
		update.addActionListener(new ActionListener() {//�༭��ť������
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("�༭������Ϣ");
				jf.setSize(350, 250);
				jf.setLocation(750, 200);
				JPanel jp = new JPanel();//�������
				jf.add(jp);
				jp.setLayout(null);
				JLabel acc = new JLabel("�������ʺ�");
				JLabel pass = new JLabel("����������");
				jp.add(pass);
				jp.add(acc);
				acc.setBounds(80, 10, 80, 25);
				pass.setBounds(80, 70, 80, 25);
				JTextField text1 = new JTextField();// �ʺ�����
				JPasswordField text2 = new JPasswordField();// ��������
				text1.setBounds(80, 40, 165, 25);
				text2.setBounds(80, 100, 165, 25);
				jp.add(text1);
				jp.add(text2);
				JButton regButton = new JButton("�޸�");
				regButton.setBounds(125, 150, 80, 40);
				jp.add(regButton);
				jf.setVisible(true);
				Connnection01 sql = new Connnection01();
				regButton.addActionListener(new ActionListener() {//�޸İ�ť������
					private int ERROR_MESSAGE;

					public void actionPerformed(ActionEvent e) {
						String s1 = text1.getText().toString();// �˺Ż�ȡ
						String s2 = new String(text2.getPassword());// �����ȡ
						
						PreparedStatement stmt1 = null;
						PreparedStatement stmt2 = null;
						PreparedStatement stmt3 = null;
						PreparedStatement stmt4 = null;
						int i;// ע������
						try {
							Connection c = sql.getConnection();
							if (s1.length() == 0 || s2.length() == 0) {
								JOptionPane.showMessageDialog(null, "������Ϣδ��", "����", ERROR_MESSAGE);
							}
							else {
								//���޸ĵ��û���Ϣ�Ƿ��ظ�
								String sql2 = "SELECT * FROM �����û��� where Unumber=?";
								stmt2 = c.prepareStatement(sql2);// Ԥ����SQL������sqlִ��
								stmt2.setString(1, s1);
								ResultSet rs2 = stmt2.executeQuery();// ִ�в�ѯ���
								if (!rs2.next()) {
									String sql4 = "UPDATE �����û��� SET Unumber=?,password=? WHERE username=?";
									stmt4 = c.prepareStatement(sql4);
									stmt4.setString(1, s1);
									stmt4.setString(2, s2);
									stmt4.setString(3,user.getUserName());
									stmt4.executeUpdate();
									JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
								} else {
									JOptionPane.showMessageDialog(null, "�˺��Ѿ�����", "����", ERROR_MESSAGE);
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
