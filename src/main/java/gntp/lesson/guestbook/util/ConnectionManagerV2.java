package gntp.lesson.guestbook.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManagerV2 {
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:/comp/env");
			DataSource ds = (DataSource)ctx.lookup("jdbc/BookDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static Connection getConnectionOld() {
		Connection con = null;
		String jdbcURL = "jdbc:mysql://localhost:3306/gntp";
		String driver = "com.mysql.cj.jdbc.Driver";
		String id = "root";
		String pwd = "1234";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(jdbcURL,id,pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
		return con;
	}
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection con) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
