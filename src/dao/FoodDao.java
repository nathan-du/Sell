package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnection.DBConnection;
import model.Food;

public class FoodDao {
	public ArrayList<Food> selectAllFood(String id) {
		ArrayList<Food> foodList = new ArrayList<>();
		Food food = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "SELECT id, price, name, detail from food where resid = '"+id+"';";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				food = new Food();
				food.setId(rs.getString(1));
				food.setPrice(rs.getDouble(2));
				food.setName(rs.getString(3));
				food.setDetail(rs.getString(4));
				foodList.add(food);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return foodList;
	}
	
	public boolean delete(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try
		{
			
			conn = DBConnection.getConn();
			String sql = "DELETE from food where id='"+id+"';";
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
	
	public boolean insertFood(String name, String resid, String price, String detail)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "INSERT into food(name, resid, price, detail) values ('"+name+"', '"+resid+"', '"+price+"', '"+detail+"');";
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
	
	public ArrayList<Food> selectByName(String name) {
		ArrayList<Food> foodList = new ArrayList<>();
		Food food = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "SELECT id, price, name, detail, resid from food where name like '%" + name + "%';";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				food = new Food();
				food.setId(rs.getString(1));
				food.setPrice(rs.getDouble(2));
				food.setName(rs.getString(3));
				food.setDetail(rs.getString(4));
				food.setResid(rs.getString(5));
				foodList.add(food);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return foodList;
	} 
}
