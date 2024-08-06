package com.test.sku.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO 
{
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private Connection getConn() 
   {
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
         return conn;
      }catch(Exception e) {
         e.printStackTrace();
      }
      return null;
   }
   
   public boolean login(User user) {
		String sql = "SELECT * FROM users WHERE userid=? AND userpwd=?";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPwd());			
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				return true;
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally{
			closeAll();
		}
		return false;
	}
   
   public List<User> getList(){
	   conn = getConn();
	      try {
	         pstmt = conn.prepareStatement("SELECT * FROM users");
	         rs = pstmt.executeQuery();
	         List<User> list = new ArrayList<>();
	         while(rs.next())
	         {
	            String uid = rs.getString("USERID");
	            String pwd = rs.getString("USERPWD");
	            
	            User user = new User();
	            user.setUid(uid);
	            user.setPwd(pwd);

	            list.add(user);
	         }
	         return list;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }finally {
	         closeAll();
	      }
	      return null;
   }
   
   public User getUserDetail(String uid){
	   User user = null;
	   conn = getConn();
	      try {
	         pstmt = conn.prepareStatement("SELECT userid, userpwd FROM users WHERE userid = ?");
	         pstmt.setString(1, uid);
	         rs = pstmt.executeQuery();
	         while(rs.next())
	         {
	        	String userId  = rs.getString("USERID");
	            String pwd = rs.getString("USERPWD");
	            
	            user = new User();
	            user.setUid(userId);
	            user.setPwd(pwd);
	         }
	         return user;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }finally {
	         closeAll();
	      }
	      return null;
   }
   
   private void closeAll() {
      try {
         if(rs!=null) rs.close();
         if(pstmt!=null) pstmt.close();
         if(conn!=null) conn.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
	
}
