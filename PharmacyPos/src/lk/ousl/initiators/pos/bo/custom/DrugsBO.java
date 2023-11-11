package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DrugsBO extends SuperBO {
    boolean saveDrugs(DrugsDTO drugs) throws SQLException, ClassNotFoundException;

    boolean updateDrugs(DrugsDTO drugs) throws SQLException, ClassNotFoundException;

    boolean deleteDrugs(String id) throws SQLException, ClassNotFoundException;

//     DrugsDTO searchDrugs(String id) throws SQLException, ClassNotFoundException;

    ArrayList<DrugsDTO> getAllDrugs() throws SQLException, ClassNotFoundException;

    boolean ifDrugsExist(String id) throws SQLException, ClassNotFoundException;

    ArrayList<DrugsDTO> searchEmployee(String searchText) throws SQLException, ClassNotFoundException;
}
