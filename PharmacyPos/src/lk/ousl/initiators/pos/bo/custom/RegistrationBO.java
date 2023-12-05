package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.dto.RegistrationDTO;

import java.sql.SQLException;

public interface RegistrationBO extends SuperBO {
    boolean saveUser(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException;
}
