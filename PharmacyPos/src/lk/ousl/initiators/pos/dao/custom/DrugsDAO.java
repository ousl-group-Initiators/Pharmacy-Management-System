package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.CrudDAO;
import lk.ousl.initiators.pos.entity.Drugs;
import lk.ousl.initiators.pos.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DrugsDAO extends CrudDAO<Drugs,String> {
    boolean ifDrugsExist(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Drugs> searchDrugs (String searchText) throws SQLException, ClassNotFoundException;
    boolean updated(Drugs drugs) throws SQLException, ClassNotFoundException;
}
