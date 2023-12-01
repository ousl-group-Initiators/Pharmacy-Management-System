package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.SupplierDAO;
import lk.ousl.initiators.pos.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Supplier(supplier_id, first_name, last_name, date_of_birth, age, telephone_number, address, email, description) VALUES (?,?,?,?,?,?,?,?,?)",
                supplier.getSupplier_id(),
                supplier.getFirst_name(),
                supplier.getLast_name(),
                supplier.getDate_of_birth(),
                supplier.getAge(),
                supplier.getTelephone_number(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getDescription()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Supplier WHERE supplier_id=?", id);
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Supplier SET first_name=?, last_name=?, date_of_birth=?, age=?, telephone_number=?, address=?, email=?, description=? WHERE supplier_id=?",
                supplier.getFirst_name(),
                supplier.getLast_name(),
                supplier.getDate_of_birth(),
                supplier.getAge(),
                supplier.getTelephone_number(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getDescription(),
                supplier.getSupplier_id()
        );
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Supplier WHERE supplier_id LIKE? || name LIKE? || address LIKE?", id);
        rst.next();
        return new Supplier(id,
                rst.getString("first_name"),
                rst.getString("last_name"),
                rst.getDate("date_of_birth"),
                rst.getInt("age"),
                rst.getInt("telephone_number"),
                rst.getString("address"),
                rst.getString("email"),
                rst.getString("description")
        );
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSuppliers = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Supplier");
        while (rst.next()) {
            allSuppliers.add(new Supplier(
                    rst.getString("supplier_id"),
                    rst.getString("first_name"),
                    rst.getString("last_name"),
                    rst.getDate("date_of_birth"),
                    rst.getInt("age"),
                    rst.getInt("telephone_number"),
                    rst.getString("address"),
                    rst.getString("email"),
                    rst.getString("description")
            ));
        }

        return allSuppliers;
    }

    @Override
    public boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT supplier_id FROM Supplier WHERE supplier_id=?", id).next();
    }

    @Override
    public ArrayList<Supplier> searchSupplier(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Supplier WHERE supplier_id LIKE ? || first_name LIKE? || last_name LIKE? || address LIKE ?";
        ResultSet set = CrudUtil.executeQuery(sql, searchText, searchText, searchText, searchText);

        ArrayList<Supplier> supplierList = new ArrayList<>();

        while (set.next()) {
            Supplier supplier = new Supplier(
                    set.getString("supplier_id"),
                    set.getString("first_name"),
                    set.getString("last_name"),
                    set.getDate("date_of_birth"),
                    set.getInt("age"),
                    set.getInt("telephone_number"),
                    set.getString("address"),
                    set.getString("email"),
                    set.getString("description")
            );
            supplierList.add(supplier);
        }
        return supplierList;
    }
}
