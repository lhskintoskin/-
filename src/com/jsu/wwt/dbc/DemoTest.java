package com.jsu.wwt.dbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

//import tool.JDBCUtil;

/**
 * ʹ��junitִ�е�Ԫ����
 */
public class DemoTest {
  Connnection01 JDBCUtil=new Connnection01();
	//�����Եķ���������ʱ�����ڴ����ͼ�У��ҵ��÷����������һ��÷��������в��ԡ�
  @Test
  public void Build() {//����
	  
  }
  @Test
  public void CommentT() {//����
	  
  }
  @Test
  public void surf() {//�����
	  
  }
  @Test
  public void MComment() {//ɾ��
	  
  }
  @Test
  public void personal() {//�༭������Ϣ
	  
  }
  @Test
  public void ManagerT() {//ɾ��
	  
  }
}
  
//	@Test
//	public void testQuery() {   //��ѯ����
//		
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		try {
//			//1.��ȡ���Ӷ�������ʹ�õ���JDBCUtil�������������д�Ĺ����ࣩ
//			connection = JDBCUtil.getConnection();
//			//2.�������Ӷ��󣬵õ�statement
//			statement = connection.createStatement();
//			//3.ִ��sql��䣬����ResultSet
//			String sql = "select * from �����û���";
//			resultSet = statement.executeQuery(sql);
//			//4.�������
//			while(resultSet.next()) {
//				String id = resultSet.getString("Unumber");
//				String username = resultSet.getString("username");
//				System.out.println("username = "+username+"  Unumber = "+id);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void testInsert() { //��Ӳ���
//		Connection connection = null;
//		Statement statement = null;
//		try {
//			//1.��ȡ���Ӷ���
//			connection = JDBCUtil.getConnection();
//			//2.�������Ӷ��󣬵õ�statement
//			statement = connection.createStatement();
//			//3.ִ��������
//			String sql = "insert into �����û��� values('xiaoxin', '����' , '��ϲ�û�ע��ɹ�',123456,'XXX',1)";
//			//���ﷵ�صĽ��ΪӰ�����������������㣬���ʾ�����ɹ�
//			int result = statement.executeUpdate(sql);
//			if (result > 0) {
//				System.out.println("��ӳɹ�");
//			} else {
//				System.out.println("���ʧ��");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} //finally {
//			JDBCUtil.release(connection, statement);
//		}
	//}
	
//	@Test
//	public void testDelete() {  //ɾ������
//		Connection connection = null;
//		Statement statement = null;
//		try {
//			//1.��ȡ���Ӷ���
//			connection = JDBCUtil.getConnection();
//			//2.�������Ӷ��󣬵õ�statement
//			statement = connection.createStatement();
//			//3.ִ��ɾ�����
//			String sql = "delete from �����û��� where username='С��'";
//			//���ﷵ�صĽ��ΪӰ�����������������㣬���ʾ�����ɹ�
//			int result = statement.executeUpdate(sql);
//			if (result > 0) {
//				System.out.println("ɾ���ɹ�");
//			} else {
//				System.out.println("ɾ��ʧ��");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} //finally {
////			JDBCUtil.release(connection, statement);
////		}
//	}
//	
//	@Test
//	public void testUpdate() {  //�޸Ĳ���
//		Connection connection = null;
//		Statement statement = null;
//		try {
//			//1.��ȡ���Ӷ���
//			connection = JDBCUtil.getConnection();
//			//2.�������Ӷ��󣬵õ�statement
//			statement = connection.createStatement();
//			//3.ִ���޸����
//			String sql = "update �����û��� set password = 99999 where username = 'xiaoxin'";
//			//���ﷵ�صĽ��ΪӰ�����������������㣬���ʾ�����ɹ�
//			int result = statement.executeUpdate(sql);
//			if (result > 0) {
//				System.out.println("�޸ĳɹ�");
//			} else {
//				System.out.println("�޸�ʧ��");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}// finally {
////			JDBCUtil.release(connection, statement);
////		}
//	}
//}
