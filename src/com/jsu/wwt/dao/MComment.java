package com.jsu.wwt.dao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.jsu.wwt.dbc.Connnection01;
/**
 * 
 * @author С��
 *
 */
public class MComment {     //����Ա����û����۽���
	static Vector rowData;
	static Vector columnNames;
	/**
	 * ����Ա������۽���
	 */
	public void mComment() {
		JFrame jf = new JFrame("�����ܱ�");
        jf.setSize(500, 500);
        jf.setLocationRelativeTo(null);
        //jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp=new JPanel();
        Connnection01 sq = new Connnection01(); 
        
        // ���� ������壬ʹ�� �߽粼��
        JPanel panel = new JPanel(new BorderLayout());
        
        // ���� һ��������ʵ��
        JToolBar toolBar = new JToolBar("������");//���϶���repaint����)

        // ���� ��������ť    
        JButton delBtn = new JButton("ɾ������");

        // ��� ��ť �� ������
        toolBar.add(delBtn);
        
        columnNames=new Vector();
		columnNames.add("�����û�");
		columnNames.add("��������");
		rowData = new Vector();
		Connection ct=null;
		PreparedStatement ps=null;
		try {
			ct=sq.getConnection();					
			ps=ct.prepareStatement("select username,comcontent from �������۱�");					
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//rowData���Դ�Ŷ���
				Vector hang=new Vector();
				for(int i=1;i<3;i++)
					hang.add(rs.getString(i));	
				//���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		final JTextArea textArea = new JTextArea();
		JTable jt = new JTable(rowData,columnNames);
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		//��ʼ�� jsp
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);
		
		delBtn.addActionListener(new ActionListener() {     //ɾ������
            @Override
            public void actionPerformed(ActionEvent e) {
            	int rowcount = jt.getSelectedRow();    //�������Ա������ı������
				if(rowcount >= 0){
					String a = jt.getValueAt(jt.getSelectedRow(), 1).toString();//��ȡ��������
					Connection ct=null;
					PreparedStatement ps=null;
					try {
						ct=sq.getConnection();					
						String sql4 = "delete from �������۱� WHERE comcontent=?";
						ps= ct.prepareStatement(sql4);
						ps.setString(1, a);
						ps.executeUpdate();    //ɾ������
					} catch (Exception e1) {
						e1.printStackTrace();
						System.exit(0);
					}
					tableModel.removeRow(rowcount);    //ɾ�����۱���Ӧ��
				}
            }
        });

		 // ��� ������ �� ������� �� ����
        panel.add(toolBar, BorderLayout.PAGE_START);
        // ��� �ı����� �� ������� �� �м�
        //panel.add(textArea, BorderLayout.CENTER);
        panel.add(jsp, BorderLayout.CENTER);
        jf.setContentPane(panel);
        jf.setVisible(true);
	}

}
