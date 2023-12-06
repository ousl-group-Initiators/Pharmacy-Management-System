package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;

import java.sql.SQLException;

public interface ReportBO extends SuperBO {
    double getDailySales() throws SQLException;
    double getTotalSales() throws SQLException;
    double getMonthlySales() throws SQLException;
}
