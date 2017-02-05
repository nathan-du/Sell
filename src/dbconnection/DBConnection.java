package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static final String user = "root";
	static final String password = "";
	static final String url = "jdbc:mysql://localhost:3306/Waimai"; 
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws SQLException
	{
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void close(Connection conn, Statement psmt, ResultSet rs)
	{
		try
		{
			if(rs != null && !rs.isClosed())
				rs.close();
			if(psmt != null)
				psmt.close();
			if(conn != null)
				conn.close();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement psmt)
	{
		close(conn, psmt, null);
	}
}

