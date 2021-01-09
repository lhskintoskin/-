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
	private JPanel jPanel=new JPanel();//������� ;

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
	 * ��¼����
	 */
	public void BigMenu() {
		JFrame frame = new JFrame("���ɵ�¼����");

		JLabel accout = new JLabel("�ʺ�");
		JTextField text1 = new JTextField(16);
		JLabel password = new JLabel("����");
		JPasswordField text2 = new JPasswordField(16);//�����
		JButton loginButton = new JButton("��¼");
		JButton regButton = new JButton("ע��");
		JLabel txt = new JLabel("��ѡ���¼���:");
		// txt.setFont(new Font("����",Font.BOLD,18));

		frame.setSize(370, 280);
		frame.setLocation(750, 300);
		// frame.setResizable(false);
		// jPanel.setLayout(null);
//		ImageIcon imageIcon=new ImageIcon("C:\\Users\\23641\\Pictures\\Saved Pictures\\Image.jpg");//����ͼƬ
//		JLabel JlabelImage=new JLabel(imageIcon);
		
		jPanel.setLayout(null);
		frame.add(jPanel);

		// �ʺ�����
		jPanel.add(accout);
		accout.setBounds(70, 40, 80, 25);
		text1.setBounds(120, 40, 165, 25);
		jPanel.add(text1);

		
		// ��������
		jPanel.add(password);
		password.setBounds(70, 75, 80, 25);
		text2.setBounds(120, 75, 165, 25);
		jPanel.add(text2);

		// ��¼��ť
		loginButton.setBounds(80, 120, 80, 25);
		jPanel.add(loginButton);

		// ע�ᰴť
		regButton.setBounds(200, 120, 80, 25);
		jPanel.add(regButton);

		txt.setBounds(85, 170, 100, 25);
		jPanel.add(txt);

//		comBox.addItem("--��ѡ��--"); // �������б������һ��
//		comBox.addItem("�����û�");
//		comBox.addItem("����Ա");
	
		 String[] listData = new String[]{"--��ѡ��--","�����û�", "����Ա"};
		 final JComboBox<String> comBox = new JComboBox<String>(listData);
		 jPanel.add(comBox);
		 comBox.setBounds(190, 170, 80, 25);
		comBox.addItemListener(new MyItemListener());
		
		 // �����Ŀѡ��״̬�ı�ļ�����
		 comBox.addItemListener(new ItemListener() { 

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					 a=comBox.getSelectedIndex();
				}
				
			}
			 
		 });
		frame.setVisible(true);

		loginButton.addActionListener(new ActionListener() {//��¼��ť������
			private int ERROR_MESSAGE;//����һ��ָ�����������Ϣ���ַ�����ָ�룻����޴���ָʾ���򷵻� NULL��
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(a==0) 
				{
					JOptionPane.showMessageDialog(null, "��ѡ����ݣ�", "����", ERROR_MESSAGE);
				}
				if (a == 1) {//������ͨ�û���¼����
					String s1 = text1.getText().toString();//toString???
					String s2 = new String(text2.getPassword());
					try {
						Connection c = sq.getConnection();//�������ݿ�
						String sql1 = "SELECT * FROM �����û��� where Unumber=? and password=?";
						PreparedStatement stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						
						//�ж��û��������Ϣ
						if (s1.length() != 0 && s2.length() != 0) {
							if (rs.next()) {
								 User user = new User(rs.getString(3), rs.getString(2), rs.getString(1));								 
								
								JOptionPane.showMessageDialog(null, "��½�ɹ�");
								//��������������
								Interface dff = new Interface();
								dff.interf(user);//��user
	
							} else {
								JOptionPane.showMessageDialog(null, "�ʺŻ���������", "����", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {
							JOptionPane.showMessageDialog(null, "�������ʺŻ�����", "����", ERROR_MESSAGE);
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
				}
				if (a == 2) {//���ɹ���Ա��¼
					String s1 = text1.getText().toString();
					String s2 = new String(text2.getPassword());
					try {
						Connection c = sq.getConnection();//�������ݿ�
						String sql1 = "SELECT * FROM ���ɹ���Ա�� where Mnumber=? and password=?";
						PreparedStatement stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						if (s1.length() != 0 && s2.length() != 0) {
							if (rs.next()) {
								//String mNumber, String managername, String password
								Manager man = new Manager(rs.getString(1),rs.getString(2),rs.getString(3));
								JOptionPane.showMessageDialog(null, "��½�ɹ�");
								ManagerT mana = new ManagerT();//����Ա��������(����ɾ������)
								mana.manager(man);
							} else {
								JOptionPane.showMessageDialog(null, "�ʺŻ���������", "����", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {
							JOptionPane.showMessageDialog(null, "�������ʺŻ�����", "����", ERROR_MESSAGE);
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

		regButton.addActionListener(new ActionListener() {//ע�ᰴť������
			private int ERROR_MESSAGE;

			public void actionPerformed(ActionEvent e) {
				if (a == 1) {
					A3 re = new A3();//����ע��
					re.reg();
				} else {
					JOptionPane.showMessageDialog(null, "ֻ�������û�����ע�ᣡ", "����", ERROR_MESSAGE);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class MyItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			String str = e.getItem().toString();//��ȡ����������
			showInfo.setBounds(5, 0, 150, 25);
			jPanel.add(showInfo);
			if (a == 1 || a == 2) {
				showInfo.setText("�𾴵�" + str + "�����¼");
			} else {
				showInfo.setText("��ѡ����ݣ�");
			}
		}
	}

}

public class A1 {

	public static void main(String[] args) {
		new BigMenu().BigMenu();

	}

}
