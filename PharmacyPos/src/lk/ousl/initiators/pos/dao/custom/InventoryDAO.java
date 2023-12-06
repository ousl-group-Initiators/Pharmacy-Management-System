package lk.ousl.initiators.pos.dao.custom;

import lk.ousl.initiators.pos.dao.SuperDAO;
import lk.ousl.initiators.pos.entity.Drugs;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryDAO extends SuperDAO {
    public ArrayList<Drugs> searchInventory (String searchText) throws SQLException, ClassNotFoundException;
    ArrayList<Drugs> getAll() throws SQLException, ClassNotFoundException;
}
