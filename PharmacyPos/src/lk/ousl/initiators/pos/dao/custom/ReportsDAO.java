package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportsDAO extends SuperDAO {
    public double getDailySales() throws SQLException;
    public double getTotalSales() throws SQLException;
    public double getMonthlySales() throws SQLException;
}
