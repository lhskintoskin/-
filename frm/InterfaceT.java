package com.jsu.wwt.frm;


import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.jsu.wwt.dao.*;
import com.jsu.wwt.dbc.Connnection01;
import com.jsu.wwt.vo.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
/**
 * 
 * @author С��
 *
 */
class Interface extends JFrame {
	public static CommentT COM;
	public static Vector rowData;
	public static Vector columnNames;
	/**
	 * �û��������淽��
	 */
	public void interf(User user) {
		JFrame jf = new JFrame("�����û�������");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Commentt COM;
		// Artical ART;
		Connnection01 sq = new Connnection01();// �����������ݿ⺯��

		// ���� ������壬ʹ�� �߽粼��
		JPanel panel = new JPanel(new BorderLayout());

		// ���� һ��������ʵ��
		JToolBar toolBar = new JToolBar("������");

		// ���� ��������ť
		JButton previousBtn = new JButton("�������");
		JButton pauseBtn = new JButton("��Ҫ����");
		JButton myinfoBtn = new JButton("��������");

		// ��� ��ť �� ������
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(myinfoBtn);

		columnNames = new Vector();
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("������");
		columnNames.add("����");
		rowData = new Vector();
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int a = 0;
		try {
			Connection ct =sq.getConnection();
			ps = ct.prepareStatement("select username,topic,postcontent,mylike from ���ӱ�");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector hang = new Vector();
				for (int i = 1; i < 5; i++)
					hang.add(rs.getString(i));
				// ���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		
		DefaultTableModel tableModel=new DefaultTableModel(rowData, columnNames) {
			public Class getColumnClass(int column) {//��ȡ�е�����
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		JTable jt = new JTable(tableModel);
		//��������
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);//����������
		jt.setRowSorter(sorter);//���ñ���������
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();//��������ļ��ϣ�
		//���õ�һ������ʽ����1������3��Ϊ�����ֶΣ�ָ��Ϊ3�ڸ��ֶ�mylike����3������Ϊ����
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);//�������������������
		// ��ʼ�� jsp�������
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);

		// ��� ������Ӱ�ť �ĵ�����������������������Ϣ���뵽 �ı�����
		previousBtn.addActionListener(new ActionListener(){//�������
             
			@Override
			public void actionPerformed(ActionEvent e) {
				 try (FileWriter bw=new FileWriter("C:\\Users\\Administrator\\Desktop\\io.txt");) {
						  int rowcount = jt.getSelectedRow();
						 // System.out.println(rowcount);
				if (rowcount >= 0) {
					String a= jt.getValueAt(jt.getSelectedRow(), 0).toString();   //aΪ��һ����������
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();   //bΪ�ڶ�����������
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();   //cΪ��������������
					//System.out.println(a);
						    bw.write(a+ "\r\n");// ���ı��ļ�����������
						    bw.write(b+ "\r\n");
						    bw.write(c);
						   
						  }
						} catch (Exception e1) {
						  e1.printStackTrace();
						}
				 //��������ҳ��
				 Surf s=new Surf();
				  s.surf(user);
			}
		});
		//������������
		pauseBtn.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * �����½������淽��
				 */
				BuildT build = new BuildT();
				build.build(user);   
			}
		});
		myinfoBtn.addActionListener(new ActionListener() {     //�������Ľ���
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * �����û��������Ľ��淽��
				 */
				Personal per = new Personal();
				per.person(user);       //���ø������Ľ��淽��
			}
		});
		// ��� ������ �� ������� �� ����
		panel.add(toolBar, BorderLayout.PAGE_START);
		// ��� �ı����� �� ������� �� �м�
		// panel.add(textArea, BorderLayout.CENTER);
		panel.add(jsp, BorderLayout.CENTER);
		jf.setContentPane(panel);
		jf.setVisible(true);
	}

}
