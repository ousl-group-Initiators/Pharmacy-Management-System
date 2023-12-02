package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.LoginBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.LoginDAO;
import lk.ousl.initiators.pos.entity.Login;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    private final LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public Login ifUserExists(String userName, String password) throws SQLException, ClassNotFoundException {
        return loginDAO.userSearch(userName, password);
    }
}
