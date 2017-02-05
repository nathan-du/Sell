package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.DBConnection;

public class UserDao {
	public String toLogin(String username, String password)
	{
		String authority = null;
		try
		{
			Connection conn = null;
			conn = DBConnection.getConn();
			String getPassword = "SELECT *from user where username = ?;";
			PreparedStatement psmt = conn.prepareStatement(getPassword);
			psmt.setString(1, username);
			ResultSet rs = psmt.executeQuery();
			try {
				
				while(rs.next()){
					
					String s = rs.getString(3);
					
					if(s.equals(password))
					{
						authority = rs.getString("authority");
						DBConnection.close(conn, psmt);
						return authority;
					}
				}
				DBConnection.close(conn, psmt);
			} catch (SQLException e) {
				System.out.println("");
			}
		}
		catch (SQLException sqlex)
		{
			System.err.println("错了");
			sqlex.printStackTrace();
			System.exit(1);
		}
		authority = "none";
		return authority;
	}
	
	public boolean insertUser(String username, String password, String authority)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select count(*)  from user where username = '"+username+"';";
			String sql = "INSERT into user(username, password, authority) values ('"+username+"', '"+password+"', '"+authority+"');";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				int s = rs.getInt(1);
				if(s>0)
				{
					return false;
				}
			}
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			DBConnection.close(conn, psmt);
			return true;
		}
		catch (SQLException sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return false;
	}
}
