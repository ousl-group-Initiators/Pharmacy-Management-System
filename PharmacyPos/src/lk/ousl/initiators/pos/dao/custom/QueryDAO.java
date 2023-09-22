package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    public void getOrderDetailsFromOrderID(String id) throws SQLException, ClassNotFoundException;
}
