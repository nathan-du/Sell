package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			sql = "SELECT orderid, name, address, phone, resid, food, price from foodorder where resid = '"+resid+"';";
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
}
