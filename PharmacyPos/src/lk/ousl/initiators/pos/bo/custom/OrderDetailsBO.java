package lk.ousl.initiators.pos.bo.custom;

import lk.ousl.initiators.pos.bo.SuperBO;
import lk.ousl.initiators.pos.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    ArrayList<OrderDetailsDTO> getAllOrder()throws SQLException, ClassNotFoundException;
}
