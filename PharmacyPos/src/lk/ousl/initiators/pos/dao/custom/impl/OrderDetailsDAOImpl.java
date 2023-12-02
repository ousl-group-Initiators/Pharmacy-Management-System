package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.custom.OrderDetailsDAO;
import lk.ousl.initiators.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
