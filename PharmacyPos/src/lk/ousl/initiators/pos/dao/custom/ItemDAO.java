package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.CrudDAO;
import lk.ousl.initiators.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item, String> {
    boolean ifItemExist(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> searchItem (String searchText) throws SQLException, ClassNotFoundException;
}

