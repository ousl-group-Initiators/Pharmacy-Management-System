package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.SuperDAO;

import java.sql.SQLException;

public interface DashboardDAO extends SuperDAO {
    public int getEmployeeCount() throws SQLException, ClassNotFoundException;
    public int getSupplierCount() throws SQLException, ClassNotFoundException;
    public int getDrugsCount() throws SQLException, ClassNotFoundException;
}
