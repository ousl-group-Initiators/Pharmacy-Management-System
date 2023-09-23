package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.CrudDAO;
import lk.ousl.initiators.pos.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee,String> {

    boolean ifEmployeeExist(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Employee> searchEmployee (String searchText) throws SQLException, ClassNotFoundException;
}
