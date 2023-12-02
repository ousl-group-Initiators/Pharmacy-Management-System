package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.entity.Login;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    Login ifUserExists(String userName, String password) throws SQLException, ClassNotFoundException;
}
