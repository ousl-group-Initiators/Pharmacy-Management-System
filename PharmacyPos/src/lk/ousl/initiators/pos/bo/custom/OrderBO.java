package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    String generateNewOrderId()throws SQLException, ClassNotFoundException;

    ArrayList<DrugsDTO> getAllItems()throws SQLException, ClassNotFoundException;

//    ArrayList<ItemDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    DrugsDTO searchDrugs(String code)throws SQLException, ClassNotFoundException;

    boolean ifDrugsExist(String code) throws SQLException, ClassNotFoundException;

//    boolean ifItemExist(String id) throws SQLException, ClassNotFoundException;

//    ItemDTO searchCustomer(String s)throws SQLException, ClassNotFoundException;
}
