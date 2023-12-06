package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.DashboardBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.DashboardDAO;
import lk.ousl.initiators.pos.dao.custom.DrugsDAO;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {

    private final DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DASHBOARD);

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getEmployeeCount();
    }

    @Override
    public int getSuppliersCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getEmployeeCount();
    }

    @Override
    public int getDrugsCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getEmployeeCount();
    }
}
