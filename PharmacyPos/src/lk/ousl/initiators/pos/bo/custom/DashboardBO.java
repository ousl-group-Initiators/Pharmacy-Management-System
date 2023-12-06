package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {
    int getEmployeeCount() throws SQLException, ClassNotFoundException;
    int getSuppliersCount() throws SQLException, ClassNotFoundException;
    int getDrugsCount() throws SQLException, ClassNotFoundException;
}
