package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.CrudDAO;

import lk.ousl.initiators.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
public interface SupplierDAO extends CrudDAO<Supplier,String>{
    boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Supplier> searchSupplier (String searchText) throws SQLException, ClassNotFoundException;
}
