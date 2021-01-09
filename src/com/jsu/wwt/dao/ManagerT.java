package com.jsu.wwt.dao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.jsu.wwt.dbc.Connnection01;
import com.jsu.wwt.vo.*;
/**
 * 
 * @author С��
 *
 */
public class ManagerT {        // ����Ա��������
	static Vector rowData;
	static Vector columnNames;
	//String a=null;
	//String b=null;
	//String c=null;
	/**
	 * ����Ա�������淽��
	 */
	public void manager(Manager man) {
		JFrame jf = new JFrame("�����ܱ�");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel jp = new JPanel();
	     Connnection01 sq = new Connnection01();   //�������ݿⷽ��

		// ���� ������壬ʹ�� �߽粼��
		JPanel panel = new JPanel(new BorderLayout());

		// ���� һ��������ʵ��
		JToolBar toolBar = new JToolBar("������");

		// ���� ��������ť
		JButton previousBtn = new JButton("�������");
		JButton pauseBtn = new JButton("ɾ������");
		JButton myinfoBtn = new JButton("��������");
		// ��� ��ť �� ������
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(myinfoBtn);

		columnNames = new Vector();
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("������");
		rowData = new Vector();
		
		myinfoBtn.addActionListener(new ActionListener() {     //�������Ľ���
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * �����û��������Ľ��淽��
				 */
				Mpersonal per = new Mpersonal();
				per.Mperson(man);       //���ø������Ľ��淽��
			}
		});
		
		Connection ct = null;
		PreparedStatement ps = null;
		try {
			ct=sq.getConnection();
			ps = ct.prepareStatement("select username,topic,postcontent from ���ӱ�");
			ResultSet rs = ps.executeQuery();    //�ڹ���Ա����������ӱ�����
			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector hang = new Vector();
				for (int i = 1; i < 4; i++)
					hang.add(rs.getString(i));
				// ���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		final JTextArea textArea = new JTextArea();
		JTable jt = new JTable(rowData, columnNames);
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		// ��ʼ�� jsp
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);

		previousBtn.addActionListener(new ActionListener() {// �������
			@Override
			/**
			 * ����Ա������Ӳ�ɾ������
			 */
			public void actionPerformed(ActionEvent e) {

				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     //aΪ����һ�����ݣ�bΪ���ڶ������ݣ�cΪ������������
					// System.out.println(a.toString());
					JFrame sp = new JFrame("�������");
					sp.setSize(500, 500);
					sp.setLocation(400, 200);
					JLabel laba = new JLabel(b);
					JLabel labb = new JLabel("����:  " + a);
					JTextArea jta = new JTextArea(c);
					jta.setLineWrap(true);
					laba.setFont(new Font("����", Font.BOLD, 22));
					JPanel jpa = new JPanel();
					JButton comment = new JButton("����");

					comment.setBounds(340, 360, 80, 25);
					jpa.add(comment);

					sp.add(jpa);
					jpa.setLayout(null);
					jpa.add(laba);
					jpa.add(labb);
					jpa.add(jta);
					laba.setBounds(130, 10, 200, 20);
					labb.setBounds(30, 25, 100, 20);
					jta.setBounds(30, 60, 410, 250);
					sp.setVisible(true);
					comment.addActionListener(new ActionListener() {      // ���۽���
						@Override
						public void actionPerformed(ActionEvent e) {
							/**
							 * ���ù���Ա������۽��淽��
							 */
							MComment mcom = new MComment();
							mcom.mComment();//���ù���Ա������۽���
							
						}
					});
				}
			}
		});
		pauseBtn.addActionListener(new ActionListener() {      //ɾ�����ӹ���
			@Override
			/**
			 * ����Աɾ��
			 */
			public void actionPerformed(ActionEvent e) {
				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {//aΪ����һ�����ݣ�bΪ���ڶ������ݣ�cΪ������������
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     
					Connection ct = null;
					PreparedStatement ps = null;
					try {
						ct=sq.getConnection();
						String sql4 = "delete from ���ӱ� WHERE username=?";
						ps = ct.prepareStatement(sql4);
						ps.setString(1, a);     //ɾ��ѡ������
						ps.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						System.exit(0);
					}
					tableModel.removeRow(rowcount);   //�ӽ��������Ƴ�����
				}
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

    