package dao;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import dbconnection.DBConnection;
import model.Res;

public class ResDao {
	public ArrayList<Res> selectAllRes() {
		ArrayList<Res> resList = new ArrayList<>();
		Res res = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "SELECT resid, resname, legalname, phone from res;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				res = new Res();
				res.setResId(rs.getString(1));
				res.setResName(rs.getString(2));
				res.setLegalName(rs.getString(3));
				res.setPhone(rs.getString(4));
				resList.add(res);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resList;
	}
	
	public boolean insertRes(String username, String legalname, String resname, String phone, String address, String scope) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select count(*)  from user where username = '"+username+"';";
			String sql = "INSERT into res(username, legalname, resname, phone, address, scope) values ('"+username+"', '"+legalname+"', '"+resname+"', '"+phone+"', '"+address+"', '"+scope+"');";
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
			System.err.println("错了啊我去");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	public boolean delete(String resid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try
		{
			
			conn = DBConnection.getConn();
			String username = getUsername(resid);
			String sql = "DELETE from res where resid='"+resid+"';";
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			sql = "DELETE from user where username='"+username+"';";
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			DBConnection.close(conn, psmt);
			return true;
		}
		catch (Exception sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}

		return false;

	}
	
	public String getUsername(String resid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String username = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "SELECT username from res where resid = '"+resid+"';";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				username = rs.getString(1);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return username;
	}
	
	public String getResid(String username) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String resid = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "SELECT resid from res where username = '"+username+"';";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				resid = rs.getString(1);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resid;
	}
	
	public ArrayList<Res> selectAllResForUser() {
		ArrayList<Res> resList = new ArrayList<>();
		Res res = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "SELECT resid, resname, phone, address, scope from res;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				res = new Res();
				res.setResId(rs.getString(1));
				res.setResName(rs.getString(2));
				res.setPhone(rs.getString(3));
				res.setAddress(rs.getString(4));
				res.setScope(rs.getString(5));
				resList.add(res);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resList;
	}
}
