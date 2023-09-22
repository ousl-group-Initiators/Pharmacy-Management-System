package lk.ousl.initiators.pos.dao.custom.impl;


import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.EmployeeDAO;
import lk.ousl.initiators.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Employee(emp_id, first_name, last_name, date_of_birth, age, telephone_number, address, job_role, description) VALUES (?,?,?,?,?,?,?,?,?)",
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

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Employee WHERE emp_id=?", id);
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Employee SET first_name=?, last_name=?, date_of_birth=?, age=?, telephone_number=?, address=?, job_role=?, description=? WHERE emp_id=?",
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getDate_of_birth(),
                employee.getAge(),
                employee.getTelephone_number(),
                employee.getAddress(),
                employee.getJob_role(),
                employee.getDescription(),
                employee.getEmp_id()
        );
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee WHERE emp_id=?", id);
        rst.next();
        return new Employee(id,
                rst.getString("first_name"),
                rst.getString("last_name"),
                rst.getDate("date_of_birth"),
                rst.getInt("age"),
                rst.getInt("telephone_number"),
                rst.getString("address"),
                rst.getString("job_role"),
                rst.getString("description")
        );
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee");
        while (rst.next()) {
            allEmployees.add(new Employee(
                    rst.getString("emp_id"),
                    rst.getString("first_name"),
                    rst.getString("last_name"),
                    rst.getDate("date_of_birth"),
                    rst.getInt("age"),
                    rst.getInt("telephone_number"),
                    rst.getString("address"),
                    rst.getString("job_role"),
                    rst.getString("description")
            ));
        }
        return allEmployees;
    }
}
