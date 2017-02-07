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
	
	public String getScope(String promo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String s = null;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select scope from promo where name = '"+promo+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				s = rs.getString(1);	
			}
			DBConnection.close(conn, psmt);
			return s;
		}
		catch (SQLException sqlex)
		{
			System.err.println("崩的嘞");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return s;
	}
	
	public double getLmt(String promo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		double s = Integer.MAX_VALUE;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select lmt from promo where name = '"+promo+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				s = rs.getDouble(1);	
			}
			DBConnection.close(conn, psmt);
			return s;
		}
		catch (SQLException sqlex)
		{
			System.err.println("崩的嘞");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return s;
	}
	
	public double getMinus(String promo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		double s = Integer.MAX_VALUE;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select minus from promo where name = '"+promo+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				s = rs.getDouble(1);	
			}
			DBConnection.close(conn, psmt);
			return s;
		}
		catch (SQLException sqlex)
		{
			System.err.println("崩的嘞");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return s;
	}
}
