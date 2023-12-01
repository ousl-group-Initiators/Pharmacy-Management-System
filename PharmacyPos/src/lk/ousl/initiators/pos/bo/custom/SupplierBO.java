package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;

import lk.ousl.initiators.pos.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
public interface SupplierBO extends SuperBO {

    boolean saveSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

//     SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> searchSupplier(String searchText) throws SQLException, ClassNotFoundException;

}
