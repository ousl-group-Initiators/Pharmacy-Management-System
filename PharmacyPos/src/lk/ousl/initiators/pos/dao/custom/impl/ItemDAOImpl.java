package lk.ousl.initiators.pos.dao.custom.impl;

import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.dao.custom.ItemDAO;
import lk.ousl.initiators.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException{
        return CrudUtil.executeUpdate("INSERT INTO Item(item_id, item_name, item_code, manufacture_date, expire_date, item_qty, unit_price, unit_discount, item_description, supplier_id) VALUES (?,?,?,?,?,?,?,?,?,?)",
                item.getItem_id(),
                item.getItem_name(),
                item.getItem_code(),
                item.getManufacture_date(),
                item.getExpire_date(),
                item.getItem_qty(),
                item.getUnit_price(),
                item.getUnit_discount(),
                item.getItem_description(),
                item.getSupplier_id()
        );
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE item_id=?",id);
    }
    @Override
    public boolean update(Item item) throws SQLException,ClassNotFoundException{
        return CrudUtil.executeUpdate("UPDATE Item SET item_name=?, item_code=?, manufacture_date=?, expire_date=?, item_qty=?, unit_price=?, unit_discount=?, item_description=?, supplier_id=? WHERE item_id=?",
                item.getItem_name(),
                item.getItem_code(),
                item.getManufacture_date(),
                item.getExpire_date(),
                item.getItem_qty(),
                item.getUnit_price(),
                item.getUnit_discount(),
                item.getItem_description(),
                item.getSupplier_id(),
                item.getItem_id()
        );
    }
    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE item_id LIKE? || item_name LIKE? || item_code LIKE?", id);
        rst.next();
        return new Item(id,
                rst.getString("item_name"),
                rst.getString("item_code"),
                rst.getDate("manufacture_date"),
                rst.getDate("expire_date"),
                rst.getInt("item_qty"),
                rst.getDouble("unit_price"),
                rst.getDouble("unit_discount"),
                rst.getString("item_description"),
                rst.getString("supplier_id")

        );
    }
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(
                    rst.getString("item_id"),
                    rst.getString("item_name"),
                    rst.getString("item_code"),
                    rst.getDate("manufacture_date"),
                    rst.getDate("expire_date"),
                    rst.getInt("item_qty"),
                    rst.getDouble("unit_price"),
                    rst.getDouble("unit_discount"),
                    rst.getString("supplier_id"),
                    rst.getString("item_description")
            ));
        }
        return allItems;
    }
    @Override
    public boolean ifItemExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT item_id FROM Item WHERE item_id=?",id).next();
    }

    @Override
    public ArrayList<Item> searchItem(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Item WHERE item_id LIKE ? || item_name LIKE? || item_code LIKE ?";
        ResultSet set = CrudUtil.executeQuery(sql,searchText,searchText,searchText);

        ArrayList<Item> itemList = new ArrayList<>();

        while (set.next()){
            Item item = new Item(
                    set.getString("item_id"),
                    set.getString("item_name"),
                    set.getString("item_code"),
                    set.getDate("manufacture_date"),
                    set.getDate("expire_date"),
                    set.getInt("item_qty"),
                    set.getDouble("unit_price"),
                    set.getDouble("unit_discount"),
                    set.getString("supplier_id"),
                    set.getString("item_description")
            );
            itemList.add(item);
        }
        return itemList;
    }
}