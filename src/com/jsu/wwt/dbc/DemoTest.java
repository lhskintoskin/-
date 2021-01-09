package com.jsu.wwt.dbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

//import tool.JDBCUtil;

/**
 * 使用junit执行单元测试
 */
public class DemoTest {
  Connnection01 JDBCUtil=new Connnection01();
	//当测试的方法逐渐增多时可以在大纲视图中，找到该方法名，并右击该方法名运行测试。
  @Test
  public void Build() {//发帖
	  
  }
  @Test
  public void CommentT() {//评论
	  
  }
  @Test
  public void surf() {//浏览帖
	  
  }
  @Test
  public void MComment() {//删评
	  
  }
  @Test
  public void personal() {//编辑个人信息
	  
  }
  @Test
  public void ManagerT() {//删帖
	  
  }
}
  
//	@Test
//	public void testQuery() {   //查询操作
//		
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		try {
//			//1.获取连接对象（这里使用到的JDBCUtil类就是我们上面写的工具类）
//			connection = JDBCUtil.getConnection();
//			//2.根据连接对象，得到statement
//			statement = connection.createStatement();
//			//3.执行sql语句，返回ResultSet
//			String sql = "select * from 贴吧用户表";
//			resultSet = statement.executeQuery(sql);
//			//4.遍历结果
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
//	public void testInsert() { //添加操作
//		Connection connection = null;
//		Statement statement = null;
//		try {
//			//1.获取连接对象
//			connection = JDBCUtil.getConnection();
//			//2.根据连接对象，得到statement
//			statement = connection.createStatement();
//			//3.执行添加语句
//			String sql = "insert into 贴吧用户表 values('xiaoxin', '张三' , '恭喜用户注册成功',123456,'XXX',1)";
//			//这里返回的结果为影响的行数，如果大于零，则表示操作成功
//			int result = statement.executeUpdate(sql);
//			if (result > 0) {
//				System.out.println("添加成功");
//			} else {
//				System.out.println("添加失败");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} //finally {
//			JDBCUtil.release(connection, statement);
//		}
	//}
	
//	@Test
//	public void testDelete() {  //删除操作
//		Connection connection = null;
//		Statement statement = null;
//		try {
//			//1.获取连接对象
//			connection = JDBCUtil.getConnection();
//			//2.根据连接对象，得到statement
//			statement = connection.createStatement();
//			//3.执行删除语句
//			String sql = "delete from 贴吧用户表 where username='小明'";
//			//这里返回的结果为影响的行数，如果大于零，则表示操作成功
//			int result = statement.executeUpdate(sql);
//			if (result > 0) {
//				System.out.println("删除成功");
//			} else {
//				System.out.println("删除失败");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} //finally {
////			JDBCUtil.release(connection, statement);
////		}
//	}
//	
//	@Test
//	public void testUpdate() {  //修改操作
//		Connection connection = null;
//		Statement statement = null;
//		try {
//			//1.获取连接对象
//			connection = JDBCUtil.getConnection();
//			//2.根据连接对象，得到statement
//			statement = connection.createStatement();
//			//3.执行修改语句
//			String sql = "update 贴吧用户表 set password = 99999 where username = 'xiaoxin'";
//			//这里返回的结果为影响的行数，如果大于零，则表示操作成功
//			int result = statement.executeUpdate(sql);
//			if (result > 0) {
//				System.out.println("修改成功");
//			} else {
//				System.out.println("修改失败");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}// finally {
////			JDBCUtil.release(connection, statement);
////		}
//	}
//}
