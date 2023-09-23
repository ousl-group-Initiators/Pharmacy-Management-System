package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.EmployeeBO;
import lk.ousl.initiators.pos.dao.CrudDAO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.EmployeeDAO;
import lk.ousl.initiators.pos.dto.EmployeeDTO;
import lk.ousl.initiators.pos.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    private final EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                dto.getEmp_id(),
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getDate_of_birth(),
                dto.getAge(),
                dto.getTelephone_number(),
                dto.getAddress(),
                dto.getJob_role(),
                dto.getDescription()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                dto.getEmp_id(),
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getDate_of_birth(),
                dto.getAge(),
                dto.getTelephone_number(),
                dto.getAddress(),
                dto.getJob_role(),
                dto.getDescription()
        ));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        if (employee == null) {
            return null;
        } else {
            return new EmployeeDTO(
                    employee.getEmp_id(),
                    employee.getFirst_name(),
                    employee.getLast_name(),
                    employee.getDate_of_birth(),
                    employee.getAge(),
                    employee.getTelephone_number(),
                    employee.getAddress(),
                    employee.getJob_role(),
                    employee.getDescription()
            );
        }
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees = new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee employee : all) {
            allEmployees.add(new EmployeeDTO(
                    employee.getEmp_id(),
                    employee.getFirst_name(),
                    employee.getLast_name(),
                    employee.getDate_of_birth(),
                    employee.getAge(),
                    employee.getTelephone_number(),
                    employee.getAddress(),
                    employee.getJob_role(),
                    employee.getDescription()
            ));
        }
        return allEmployees;
    }

    @Override
    public boolean employeeExist(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.ifExist(id);
    }

}
