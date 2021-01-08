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
 * @author 小白
 *
 */
class Interface extends JFrame {
	public static CommentT COM;
	public static Vector rowData;
	public static Vector columnNames;
	/**
	 * 用户操作界面方法
	 */
	public void interf(User user) {
		JFrame jf = new JFrame("贴吧用户主界面");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Commentt COM;
		// Artical ART;
		Connnection01 sq = new Connnection01();// 调用连接数据库函数

		// 创建 内容面板，使用 边界布局
		JPanel panel = new JPanel(new BorderLayout());

		// 创建 一个工具栏实例
		JToolBar toolBar = new JToolBar("工具栏");

		// 创建 工具栏按钮
		JButton previousBtn = new JButton("浏览帖子");
		JButton pauseBtn = new JButton("我要发帖");
		JButton myinfoBtn = new JButton("个人中心");

		// 添加 按钮 到 工具栏
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(myinfoBtn);

		columnNames = new Vector();
		columnNames.add("帖主");
		columnNames.add("帖标题");
		columnNames.add("帖内容");
		columnNames.add("点赞");
		rowData = new Vector();
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int a = 0;
		try {
			Connection ct =sq.getConnection();
			ps = ct.prepareStatement("select username,topic,postcontent,mylike from 帖子表");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// rowData可以存放多行
				Vector hang = new Vector();
				for (int i = 1; i < 5; i++)
					hang.add(rs.getString(i));
				// 加入到rowData
				rowData.add(hang);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		
		DefaultTableModel tableModel=new DefaultTableModel(rowData, columnNames) {
			public Class getColumnClass(int column) {//获取列的类型
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
		//点赞排序
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);//设置排序器
		jt.setRowSorter(sorter);//设置表格的排序器
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();//设置排序的集合，
		//设置第一种排序方式：第1个参数3，为排序字段，指明为3第个字段mylike，第3个参数为升序
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);//设置排序器的排序规则
		// 初始化 jsp滚动面板
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);

		// 添加 浏览帖子按钮 的点击动作监听器，并把相关信息输入到 文本区域
		previousBtn.addActionListener(new ActionListener(){//浏览帖子
             
			@Override
			public void actionPerformed(ActionEvent e) {
				 try (FileWriter bw=new FileWriter("C:\\Users\\Administrator\\Desktop\\io.txt");) {
						  int rowcount = jt.getSelectedRow();
						 // System.out.println(rowcount);
				if (rowcount >= 0) {
					String a= jt.getValueAt(jt.getSelectedRow(), 0).toString();   //a为第一个表格的数据
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();   //b为第二个表格的数据
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();   //c为第三个表格的数据
					//System.out.println(a);
						    bw.write(a+ "\r\n");// 向文本文件中增加数据
						    bw.write(b+ "\r\n");
						    bw.write(c);
						   
						  }
						} catch (Exception e1) {
						  e1.printStackTrace();
						}
				 //调用帖子页面
				 Surf s=new Surf();
				  s.surf(user);
			}
		});
		//建立新帖界面
		pauseBtn.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * 调用新建帖界面方法
				 */
				BuildT build = new BuildT();
				build.build(user);   
			}
		});
		myinfoBtn.addActionListener(new ActionListener() {     //个人中心界面
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * 调用用户个人中心界面方法
				 */
				Personal per = new Personal();
				per.person(user);       //调用个人中心界面方法
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
