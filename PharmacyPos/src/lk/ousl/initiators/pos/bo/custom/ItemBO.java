package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.dao.custom.ItemDAO;
import lk.ousl.initiators.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO item) throws SQLException,ClassNotFoundException;
    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
 //   ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    boolean ifItemExist(String id) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> searchItem(String searchText) throws SQLException, ClassNotFoundException;
}
