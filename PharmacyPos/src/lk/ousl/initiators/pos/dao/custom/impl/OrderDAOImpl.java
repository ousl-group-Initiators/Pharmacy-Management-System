package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.OrderDAO;
import lk.ousl.initiators.pos.entity.Employee;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Orders orders) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES (?,?,?,?,?)",
                orders.getInvoice_number(),
                orders.getCashier_name(),
                orders.getDate(),
                orders.getTime(),
                orders.getTotal()
        );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Orders search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE invoice_number=?", id);
        rst.next();
        return new Orders(
                rst.getString("invoice_number"),
                rst.getString("cashier_name"),
                Date.valueOf(rst.getString("date")),
                Time.valueOf(rst.getString("time")),
                rst.getDouble("total")
        );
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> allOrders = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order`");
        while (rst.next()) {
            allOrders.add(new Orders(
                    rst.getString("invoice_number"),
                    rst.getString("cashier_name"),
                    rst.getDate("date"),
                    rst.getTime("time"),
                    rst.getDouble("total")
            ));
        }
        return allOrders;
    }

    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT invoice_number FROM `Order` WHERE invoice_number=?",oid).next();
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT invoice_number FROM `Order` ORDER BY invoice_number DESC LIMIT 1;");
        return rst.next() ? String.format("INV%05d", (Integer.parseInt(rst.getString("invoice_number").replace("INV", "")) + 1)) : "INV00001";
    }

    @Override
    public ArrayList<Orders> searchOrders(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `Order` WHERE invoice_number LIKE ?";
        ResultSet set = CrudUtil.executeQuery(sql,searchText);

        ArrayList<Orders> orderList = new ArrayList<>();

        while (set.next()){
            Orders orders = new Orders(
                    set.getString("invoice_number"),
                    set.getString("cashier_name"),
                    set.getDate("date"),
                    set.getTime("time"),
                    set.getDouble("total")
            );
            orderList.add(orders);
        }
        return orderList;
    }
}
