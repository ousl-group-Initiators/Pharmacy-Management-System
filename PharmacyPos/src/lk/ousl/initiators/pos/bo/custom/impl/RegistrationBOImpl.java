package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.RegistrationBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.EmployeeDAO;
import lk.ousl.initiators.pos.dao.custom.RegistrationDAO;
import lk.ousl.initiators.pos.dto.RegistrationDTO;
import lk.ousl.initiators.pos.entity.Employee;
import lk.ousl.initiators.pos.entity.Registration;

import java.sql.SQLException;

public class RegistrationBOImpl implements RegistrationBO {

    private final RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);

    @Override
    public boolean saveUser(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException {
        return registrationDAO.save(new Registration(
                registrationDTO.getUserName(),
                registrationDTO.getPassword(),
                registrationDTO.getConfirm_password(),
                registrationDTO.getJob_role()
        ));
    }
}
