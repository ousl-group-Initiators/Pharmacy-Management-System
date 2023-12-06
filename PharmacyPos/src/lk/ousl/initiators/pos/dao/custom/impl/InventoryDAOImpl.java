package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.InventoryDAO;
import lk.ousl.initiators.pos.entity.Drugs;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {
    @Override
    public ArrayList<Drugs> searchInventory(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `Drugs` WHERE drug_id LIKE ? || drug_name ? || batch_number ?";
        ResultSet rst = CrudUtil.executeQuery(sql,searchText, searchText, searchText);

        ArrayList<Drugs> orderList = new ArrayList<>();

        while (rst.next()){
            Drugs drugs = new Drugs(
                    rst.getString("drug_id"),
                    rst.getString("drug_name"),
                    rst.getString("batch_number"),
                    rst.getInt("drug_quantity"),
                    rst.getDouble("unit_price"),
                    rst.getDouble("unit_discount")
            );
            orderList.add(drugs);
        }
        return orderList;
    }

    @Override
    public ArrayList<Drugs> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Drugs> allDrugs = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Drugs`");
        while (rst.next()) {
            allDrugs.add(new Drugs(
                    rst.getString("drug_id"),
                    rst.getString("drug_name"),
                    rst.getString("batch_number"),
                    rst.getInt("drug_quantity"),
                    rst.getDouble("unit_price"),
                    rst.getDouble("unit_discount")
            ));
        }
        return allDrugs;
    }
}
