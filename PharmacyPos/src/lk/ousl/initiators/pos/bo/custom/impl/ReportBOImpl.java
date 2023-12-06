package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.ReportBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.RegistrationDAO;
import lk.ousl.initiators.pos.dao.custom.ReportsDAO;

import java.sql.SQLException;

public class ReportBOImpl implements ReportBO {

    private final ReportsDAO reportsDAO = (ReportsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REPORT);

    @Override
    public double getDailySales() throws SQLException {
        return reportsDAO.getDailySales();
    }

    @Override
    public double getTotalSales() throws SQLException {
        return reportsDAO.getDailySales();
    }

    @Override
    public double getMonthlySales() throws SQLException {
        return reportsDAO.getDailySales();
    }
}
