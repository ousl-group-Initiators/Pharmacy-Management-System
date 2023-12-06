package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.CrudDAO;
import lk.ousl.initiators.pos.entity.Employee;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Orders, String> {
    boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    public ArrayList<Orders> searchOrders (String searchText) throws SQLException, ClassNotFoundException;
}
