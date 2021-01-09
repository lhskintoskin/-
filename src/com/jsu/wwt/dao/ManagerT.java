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
 * @author 小白
 *
 */
public class ManagerT {        // 管理员操作界面
	static Vector rowData;
	static Vector columnNames;
	//String a=null;
	//String b=null;
	//String c=null;
	/**
	 * 管理员操作界面方法
	 */
	public void manager(Manager man) {
		JFrame jf = new JFrame("帖子总表");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel jp = new JPanel();
	     Connnection01 sq = new Connnection01();   //调用数据库方法

		// 创建 内容面板，使用 边界布局
		JPanel panel = new JPanel(new BorderLayout());

		// 创建 一个工具栏实例
		JToolBar toolBar = new JToolBar("工具栏");

		// 创建 工具栏按钮
		JButton previousBtn = new JButton("浏览帖子");
		JButton pauseBtn = new JButton("删除帖子");
		JButton myinfoBtn = new JButton("个人中心");
		// 添加 按钮 到 工具栏
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(myinfoBtn);

		columnNames = new Vector();
		columnNames.add("帖主");
		columnNames.add("帖标题");
		columnNames.add("帖内容");
		rowData = new Vector();
		
		myinfoBtn.addActionListener(new ActionListener() {     //个人中心界面
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * 调用用户个人中心界面方法
				 */
				Mpersonal per = new Mpersonal();
				per.Mperson(man);       //调用个人中心界面方法
			}
		});
		
		Connection ct = null;
		PreparedStatement ps = null;
		try {
			ct=sq.getConnection();
			ps = ct.prepareStatement("select username,topic,postcontent from 帖子表");
			ResultSet rs = ps.executeQuery();    //在管理员界面输出帖子表待审查
			while (rs.next()) {
				// rowData可以存放多行
				Vector hang = new Vector();
				for (int i = 1; i < 4; i++)
					hang.add(rs.getString(i));
				// 加入到rowData
				rowData.add(hang);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		final JTextArea textArea = new JTextArea();
		JTable jt = new JTable(rowData, columnNames);
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		// 初始化 jsp
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);

		previousBtn.addActionListener(new ActionListener() {// 浏览帖子
			@Override
			/**
			 * 管理员浏览帖子并删除评论
			 */
			public void actionPerformed(ActionEvent e) {

				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     //a为表格第一个数据，b为表格第二个数据，c为表格第三个数据
					// System.out.println(a.toString());
					JFrame sp = new JFrame("浏览帖子");
					sp.setSize(500, 500);
					sp.setLocation(400, 200);
					JLabel laba = new JLabel(b);
					JLabel labb = new JLabel("帖主:  " + a);
					JTextArea jta = new JTextArea(c);
					jta.setLineWrap(true);
					laba.setFont(new Font("黑体", Font.BOLD, 22));
					JPanel jpa = new JPanel();
					JButton comment = new JButton("评论");

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
					comment.addActionListener(new ActionListener() {      // 评论界面
						@Override
						public void actionPerformed(ActionEvent e) {
							/**
							 * 调用管理员审核评论界面方法
							 */
							MComment mcom = new MComment();
							mcom.mComment();//调用管理员审核评论界面
							
						}
					});
				}
			}
		});
		pauseBtn.addActionListener(new ActionListener() {      //删除帖子功能
			@Override
			/**
			 * 管理员删帖
			 */
			public void actionPerformed(ActionEvent e) {
				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {//a为表格第一个数据，b为表格第二个数据，c为表格第三个数据
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     
					Connection ct = null;
					PreparedStatement ps = null;
					try {
						ct=sq.getConnection();
						String sql4 = "delete from 帖子表 WHERE username=?";
						ps = ct.prepareStatement(sql4);
						ps.setString(1, a);     //删除选定帖子
						ps.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						System.exit(0);
					}
					tableModel.removeRow(rowcount);   //从界面表格中移除帖子
				}
			}
		});

		// 添加 工具栏 到 内容面板 的 顶部
		panel.add(toolBar, BorderLayout.PAGE_START);
		// 添加 文本区域 到 内容面板 的 中间
		// panel.add(textArea, BorderLayout.CENTER);
		panel.add(jsp, BorderLayout.CENTER);
		jf.setContentPane(panel);
		jf.setVisible(true);
	}
}

    