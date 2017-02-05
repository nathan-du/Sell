package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.DBConnection;

public class PromoDao {
	public boolean insertPromo(String name, String scope, String limit, String minus)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select count(*)  from promo where name = '"+name+"';";
			String sql = "INSERT into promo(name, scope, lmt, minus, valid) values ('"+name+"', '"+scope+"', '"+limit+"', '"+minus+"', '1');";
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
			System.err.println("崩的嘞");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return false;
	}
}
