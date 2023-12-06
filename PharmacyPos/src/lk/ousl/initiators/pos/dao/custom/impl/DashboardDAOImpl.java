package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.DashboardDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAOImpl implements DashboardDAO {

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*) AS totalEmployees FROM Employee;");
            if (resultSet.next()) {
                return resultSet.getInt("totalEmployees");
            } else {
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing SQL query", e);
        }
    }

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*) AS totalSupplier FROM Supplier;");
            if (resultSet.next()) {
                return resultSet.getInt("totalSupplier");
            } else {
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing SQL query", e);
        }
    }

    @Override
    public int getDrugsCount() throws SQLException, ClassNotFoundException {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*) AS totalDrugs FROM Drugs;");
            if (resultSet.next()) {
                return resultSet.getInt("totalDrugs");
            } else {
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing SQL query", e);
        }
    }
}
