package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
