package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.DBConnection;
import model.Food;

public class CartDao {
	public boolean insertFood(String id, String username, String name, String price)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		double count = 0;
		try
		{
			conn = DBConnection.getConn();
			String getcount = "SELECT count from cart where id = '"+id+"' and username = '"+username+"'";
			psmt = conn.prepareStatement(getcount);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				count = rs.getDouble(1);
				return updateFood(id, username, count + 1);
			}
			String sql = "INSERT into cart(id, username, count, name, price) values ('"+name+"', '"+username+"', '1', '"+name+"', '"+price+"');";
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			DBConnection.close(conn, psmt);
			return true;
		}
		catch (SQLException sqlex)
		{
			System.err.println("崩的嘞");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	public boolean updateFood(String id, String username, double count) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "update cart set count =" + count +" where username='"+username+"' and id = '"+id+"';";
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
