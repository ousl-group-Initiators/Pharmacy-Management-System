package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.RegistrationDAO;
import lk.ousl.initiators.pos.entity.Registration;

import java.sql.SQLException;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration registration) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Registration(user_name,password,confirm_password,job_role) VALUES (?,?,?,?)",
                registration.getUserName(),
                registration.getPassword(),
                registration.getConfirm_password(),
                registration.getJob_role()
        );
    }
}
