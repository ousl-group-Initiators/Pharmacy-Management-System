package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.OrderDetailsBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.OrderDAO;
import lk.ousl.initiators.pos.dao.custom.OrderDetailsDAO;
import lk.ousl.initiators.pos.dao.custom.RegistrationDAO;
import lk.ousl.initiators.pos.dto.OrderDTO;
import lk.ousl.initiators.pos.dto.OrderDetailsDTO;
import lk.ousl.initiators.pos.entity.OrderDetails;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> allOrderDetails = new ArrayList<>();
        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();
        for (OrderDetails orderDetails : all) {
            allOrderDetails.add(new OrderDetailsDTO(
                    orderDetails.getDrug_id(),
                    orderDetails.getDescription(),
                    orderDetails.getUnitPrice(),
                    orderDetails.getQty(),
                    orderDetails.getDiscount(),
                    orderDetails.getTotal()));
        }
        return allOrderDetails;
    }
}
