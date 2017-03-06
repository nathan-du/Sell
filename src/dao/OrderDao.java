package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnection.DBConnection;
import model.Order;

public class OrderDao {
	public ArrayList<Order> selectOrder(String resid) {
		ArrayList<Order> orderList = new ArrayList<>();
		Order order = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;
		try
		{
			conn = DBConnection.getConn();
			sql = "SELECT orderid, name, address, phone, resid, food, price, status, point, rate from foodorder where resid = '"+resid+"';";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				order = new Order();
				order.setOrderid(rs.getString(1));
				order.setName(rs.getString(2));
				order.setAddress(rs.getString(3));
				order.setPhone(rs.getString(4));
				order.setResid(rs.getString(5));
				order.setFood(rs.getString(6));
				order.setPrice(rs.getDouble(7));
				order.setStatus(rs.getInt(8));
				order.setPoint(rs.getInt(9));
				order.setRate(rs.getString(10));
				orderList.add(order);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}
	
	public ArrayList<Order> selectOrderByUN(String username) {
		ArrayList<Order> orderList = new ArrayList<>();
		Order order = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;
		try
		{
			conn = DBConnection.getConn();
			sql = "SELECT orderid, name, address, phone, resid, food, price, status from foodorder where username = '"+username+"';";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				order = new Order();
				order.setOrderid(rs.getString(1));
				order.setName(rs.getString(2));
				order.setAddress(rs.getString(3));
				order.setPhone(rs.getString(4));
				order.setResid(rs.getString(5));
				order.setFood(rs.getString(6));
				order.setPrice(rs.getDouble(7));
				order.setStatus(rs.getInt(8));
				orderList.add(order);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}
	
	public ArrayList<Order> selectOrderByStatus(String resid) {
		ArrayList<Order> orderList = new ArrayList<>();
		Order order = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;
		try
		{
			conn = DBConnection.getConn();
			sql = "SELECT orderid, name, address, phone, resid, food, price, status from foodorder where resid = '"+resid+"' and (status ='0' or status='1');";
			System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				order = new Order();
				order.setOrderid(rs.getString(1));
				order.setName(rs.getString(2));
				order.setAddress(rs.getString(3));
				order.setPhone(rs.getString(4));
				order.setResid(rs.getString(5));
				order.setFood(rs.getString(6));
				order.setPrice(rs.getDouble(7));
				order.setStatus(rs.getInt(8));
				orderList.add(order);
			}
			DBConnection.close(conn, psmt, rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}
	
	public boolean checkOut(String username, String name, String phone, String address, String detail) {
		CartDao cartDao = new CartDao();
		double price = cartDao.getSum(username);
		String food = cartDao.getFood(username);
		int status = 0;
		int resid = cartDao.getResid(username);
		Connection conn = null;
		PreparedStatement psmt = null;
		boolean flag = false;
		try
		{
			conn = DBConnection.getConn();
			String sql = "INSERT into foodorder(username, status, name, address, phone, resid, food, price, detail) values ('"+username+"','"+status+"', '"+name+"', '"+address+"', '"+phone+"', '"+resid+"', '"+food+"', '"+price+"', '"+detail+"');";
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			DBConnection.close(conn, psmt);
			flag = true;
		}
		catch (SQLException sqlex)
		{
			System.err.println("崩的嘞");
			sqlex.printStackTrace();
			System.exit(1);
		}
		if(flag) {
			cartDao.delete(username);
		}
		return flag;
	}
	
	public boolean changeStatus(String orderId, int status) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;
		try
		{
			conn = DBConnection.getConn();
			sql = "update foodorder set status =" + status +" where orderid = '"+orderId+"';";
			System.out.println(sql);
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
	
	public boolean rate(String orderId, String point, String rate) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;
		try
		{
			conn = DBConnection.getConn();
			sql = "update foodorder set point ='"+ point +"', rate = '"+ rate +"', status ='3' where orderid = '"+orderId+"';";
			System.out.println(sql);
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
