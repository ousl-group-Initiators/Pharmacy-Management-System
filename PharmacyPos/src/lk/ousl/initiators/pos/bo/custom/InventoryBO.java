package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {
    ArrayList<DrugsDTO> getAllItems()throws SQLException, ClassNotFoundException;
    ArrayList<DrugsDTO> searchOrder(String searchText) throws SQLException, ClassNotFoundException;
}
