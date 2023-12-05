package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.DrugsDAO;
import lk.ousl.initiators.pos.dao.custom.EmployeeDAO;
import lk.ousl.initiators.pos.entity.Drugs;
import lk.ousl.initiators.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrugsDAOImpl implements DrugsDAO {

    @Override
    public boolean save(Drugs drugs) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Drugs(drug_id, drug_name, batch_number,MFD ,EXD, drug_quantity, unit_price, unit_discount, Supply_id, description) VALUES (?,?,?,?,?,?,?,?,?,?)",
                drugs.getDrug_id(),
                drugs.getDrug_name(),
                drugs.getBatch_number(),
                drugs.getMFD(),
                drugs.getEXD(),
                drugs.getDrug_quantity(),
                drugs.getUnit_price(),
                drugs.getUnit_discount(),
                drugs.getSupply_id(),
                drugs.getDescription()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Drugs WHERE drug_id=?", id);
    }

    @Override
    public boolean update(Drugs drugs) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Drugs SET drug_name=?, batch_number=?, MFD=?, EXD=?, drug_quantity=?, unit_price=?, unit_discount=?, supply_id=?, description=? WHERE drug_id=?",
                drugs.getDrug_name(),
                drugs.getBatch_number(),
                drugs.getMFD(),
                drugs.getEXD(),
                drugs.getDrug_quantity(),
                drugs.getUnit_price(),
                drugs.getUnit_discount(),
                drugs.getSupply_id(),
                drugs.getDescription(),
                drugs.getDrug_id()
        );
    }

    @Override
    public Drugs search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Drugs WHERE drug_id = ?", id);
        rst.next();
        return new Drugs(id,
                rst.getString("drug_name"),
                rst.getInt("drug_quantity"),
                rst.getDouble("unit_price"),
                rst.getDouble("unit_discount")
        );
    }

    @Override
    public ArrayList<Drugs> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Drugs> allDrugs = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Drugs");
        while (rst.next()) {
            allDrugs.add(new Drugs(
                    rst.getString("drug_id"),
                    rst.getString("drug_name"),
                    rst.getString("batch_number"),
                    rst.getDate("MFD"),
                    rst.getDate("EXD"),
                    rst.getInt("drug_quantity"),
                    rst.getDouble("unit_price"),
                    rst.getDouble("unit_discount"),
                    rst.getString("Supply_id"),
                    rst.getString("description")
            ));
        }
        return allDrugs;
    }

    @Override
    public boolean ifDrugsExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT drug_id FROM Drugs WHERE drug_id=?",id).next();
    }

    @Override
    public ArrayList<Drugs> searchDrugs(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Drugs WHERE drug_id LIKE ? || drug_name LIKE? || batch_number LIKE? || drug_quantity LIKE ?";
        ResultSet set = CrudUtil.executeQuery(sql,searchText,searchText,searchText,searchText);

        ArrayList<Drugs> drugsList = new ArrayList<>();

        while (set.next()){
            Drugs drugs = new Drugs(
                    set.getString("drug_id"),
                    set.getString("drug_name"),
                    set.getString("batch_number"),
                    set.getDate("MFD"),
                    set.getDate("EXD"),
                    set.getInt("drug_quantity"),
                    set.getDouble("unit_price"),
                    set.getDouble("unit_discount"),
                    set.getString("Supply_id"),
                    set.getString("description")
            );
            drugsList.add(drugs);
        }
        return drugsList;
    }

    @Override
    public boolean updated(Drugs drugs) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Drugs SET drug_name=?, drug_quantity=?, unit_price=?, unit_discount=? WHERE drug_id=?",
                drugs.getDrug_name(),
                drugs.getDrug_quantity(),
                drugs.getUnit_price(),
                drugs.getUnit_discount(),
                drugs.getDrug_id()
        );
    }
}
