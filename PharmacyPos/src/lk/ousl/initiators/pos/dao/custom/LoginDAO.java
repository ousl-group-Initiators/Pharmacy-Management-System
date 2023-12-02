package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.CrudDAO;
import lk.ousl.initiators.pos.dao.SuperDAO;
import lk.ousl.initiators.pos.entity.Login;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO{
    Login userSearch(String userName, String password) throws SQLException, ClassNotFoundException;
}
