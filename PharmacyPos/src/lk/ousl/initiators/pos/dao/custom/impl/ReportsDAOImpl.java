package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.ReportsDAO;
import lk.ousl.initiators.pos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsDAOImpl implements ReportsDAO {
    @Override
    public double getDailySales() throws SQLException {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT SUM(total) AS totalDailySales FROM `Order` WHERE DATE(date) = CURDATE();");
            if (resultSet.next()) {
                return resultSet.getDouble("totalDailySales");
            } else {
                return 0.0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing SQL query", e);
        }
    }

    @Override
    public double getTotalSales() throws SQLException {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT SUM(total) AS totalSales FROM `Order`;");
            if (resultSet.next()) {
                return resultSet.getDouble("totalSales");
            } else {
                return 0.0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing SQL query", e);
        }
    }

    @Override
    public double getMonthlySales() throws SQLException {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT SUM(total) AS totalMonthlySales FROM `Order` WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE());");
            if (resultSet.next()) {
                return resultSet.getDouble("totalMonthlySales");
            } else {
                return 0.0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing SQL query", e);
        }
    }
}
