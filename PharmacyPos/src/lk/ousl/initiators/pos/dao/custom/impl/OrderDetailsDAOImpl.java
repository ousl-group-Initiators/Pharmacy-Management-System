package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.OrderDetailsDAO;
import lk.ousl.initiators.pos.entity.OrderDetails;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order_Details` VALUES (?,?,?,?,?,?,?)",
                orderDetails.getInvoice_number(),
                orderDetails.getDrug_id(),
                orderDetails.getDescription(),
                orderDetails.getUnitPrice(),
                orderDetails.getQty(),
                orderDetails.getDiscount(),
                orderDetails.getTotal());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> allOrderDetails = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT o.invoice_number, d.drug_id, d.description, d.unitPrice, d.qty, d.discount, d.total" +
                "FROM `Order` o INNER JOIN `Order_Details` d ON o.invoice_number = d.invoice_number WHERE o.invoice_number = ?");
        while (rst.next()) {
            allOrderDetails.add(new OrderDetails(
                    rst.getString("drug_id"),
                    rst.getString("description"),
                    rst.getDouble("unitPrice"),
                    rst.getInt("qty"),
                    rst.getDouble("discount"),
                    rst.getDouble("total")
            ));
        }
        return allOrderDetails;
    }
}
