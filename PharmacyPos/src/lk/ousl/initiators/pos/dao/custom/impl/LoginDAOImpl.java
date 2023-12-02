package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.LoginDAO;
import lk.ousl.initiators.pos.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public Login userSearch(String userName, String password) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM login WHERE user_name= ? AND password= ?", userName, password);
        rst.next();
        return new Login(userName, password);
    }
}
