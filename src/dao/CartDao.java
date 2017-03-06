package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnection.DBConnection;
import model.Cart;
import model.Food;
import model.Res;

public class CartDao {
	public boolean insertFood(String id, String username, String name, String price, String resid)
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
			String sql = "INSERT into cart(id, username, count, name, price, resid) values ('"+id+"', '"+username+"', '1', '"+name+"', '"+price+"', '"+resid+"');";
			System.out.println(sql);
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
	
	public boolean insertPromo(String username, double count, String name, double price)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "INSERT into cart(username, count, name, price) values ('"+username+"','"+count+"', '"+name+"', '"+price+"');";
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
		String sql = null;
		try
		{
			conn = DBConnection.getConn();
			if(count > 0) sql = "update cart set count =" + count +" where username='"+username+"' and id = '"+id+"';";
			else sql = "delete from cart where username='"+username+"' and id = '"+id+"';";
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
	
	public boolean updateFood(String cartId, double count) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;
		try
		{
			conn = DBConnection.getConn();
			if(count > 0) sql = "update cart set count =" + count +" where cartId = '"+cartId+"';";
			else sql = "delete from cart where cartId = '"+cartId+"';";
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
	
	public ArrayList<Cart> seletAll(String username) {
		ArrayList<Cart> cartList = new ArrayList<>();
		Cart cart = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sql = "SELECT * from cart where username = '"+username+"';";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				cart = new Cart();
				cart.setCartid(rs.getString(1));
				cart.setId(rs.getString(2));
				cart.setUsername(rs.getString(3));
				cart.setCount(rs.getInt(4));
				cart.setName(rs.getString(5));
				cart.setPrice(rs.getDouble(6));
				cartList.add(cart);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cartList;
	}
	
	public int getNumber(String username) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int s = 0;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select count(*)  from cart where username = '"+username+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				s = rs.getInt(1);
				return s;
			}
			DBConnection.close(conn, psmt);
			return s;
		}
		catch (SQLException sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return s;
	}
	
	public boolean judgeunique(String username, String resid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select resid  from cart where username = '"+username+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				if(rs.getString(1) != null && !resid.equals(rs.getString(1))) return false;
			}
			DBConnection.close(conn, psmt);
		}
		catch (SQLException sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return true;
	}
	
	public double getSum(String username) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		double sum = 0;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select count, price  from cart where username = '"+username+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				double count = rs.getDouble(1);				
				double price = rs.getDouble(2);
				sum += count * price;
			}
			DBConnection.close(conn, psmt);
			return sum;
		}
		catch (SQLException sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return sum;
	}
	
	public double getSum(String username, String scope) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		double sum = 0;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select count, price  from cart where username = '"+username+"' and resid = '"+scope+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				double count = rs.getDouble(1);				
				double price = rs.getDouble(2);
				sum += count * price;
			}
			DBConnection.close(conn, psmt);
			return sum;
		}
		catch (SQLException sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return sum;
	}
	
	public String getFood(String username) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select name from cart where username = '"+username+"' and id is not null;";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			int i = 1;
			while(rs.next()){
				sb.append(i + ".");
				sb.append(rs.getString(1));
				sb.append(" ");
				i++;
			}
			DBConnection.close(conn, psmt);
		}
		catch (SQLException sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return sb.toString();
	}
	
	public int getResid(String username) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int resid = 0;
		try
		{
			conn = DBConnection.getConn();
			String sqlcheck = "select resid from cart where username = '"+username+"';";
			psmt = conn.prepareStatement(sqlcheck);
			rs = psmt.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
			}
			DBConnection.close(conn, psmt);
		}
		catch (SQLException sqlex)
		{
			System.err.println("Œﬁ∑®¡¨Ω”µΩ ˝æ›ø‚");
			sqlex.printStackTrace();
			System.exit(1);
		}
		return resid;
	}
	
	public boolean delete(String username) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try
		{
			
			conn = DBConnection.getConn();
			String sql = "DELETE from cart where username='"+username+"';";
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
