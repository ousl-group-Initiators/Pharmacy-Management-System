package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
     boolean addEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

     boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

     boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

     EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException;

     ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
}
