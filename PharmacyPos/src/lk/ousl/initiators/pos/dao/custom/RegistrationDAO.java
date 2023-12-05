package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.SuperDAO;
import lk.ousl.initiators.pos.entity.Registration;

import java.sql.SQLException;

public interface RegistrationDAO extends SuperDAO {
    boolean save(Registration registration) throws SQLException, ClassNotFoundException;
}
