package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.OrderBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.DrugsDAO;
import lk.ousl.initiators.pos.dao.custom.OrderDAO;
import lk.ousl.initiators.pos.dao.custom.OrderDetailsDAO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.EmployeeDTO;
import lk.ousl.initiators.pos.dto.OrderDTO;
import lk.ousl.initiators.pos.dto.OrderDetailsDTO;
import lk.ousl.initiators.pos.entity.Drugs;
import lk.ousl.initiators.pos.entity.Employee;
import lk.ousl.initiators.pos.entity.OrderDetails;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    private final DrugsDAO drugsDAO = (DrugsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DRUGS);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        //Transaction
        connection = DBConnection.getDbConnection().getConnection();
        boolean orderAvailable = orderDAO.ifOrderExist(dto.getInvoice_number());
        if (orderAvailable){
            return false;
        }

        connection.setAutoCommit(false);
        Orders order = new Orders(
                dto.getInvoice_number(),
                dto.getCashier_name(),
                dto.getDate(),
                dto.getTime(),
                dto.getTotal()
        );

        boolean orderAdded = orderDAO.save(order);
        if (!orderAdded){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailsDTO orderDetailsDTO : dto.getOrderDetails()){
            OrderDetails orderDetails = new OrderDetails(
                    orderDetailsDTO.getInvoice_number(),
                    orderDetailsDTO.getDrug_id(),
                    orderDetailsDTO.getDescription(),
                    orderDetailsDTO.getUnitPrice(),
                    orderDetailsDTO.getQty(),
                    orderDetailsDTO.getDiscount(),
                    orderDetailsDTO.getTotal()
            );
            boolean orderDetailsAdded = orderDetailsDAO.save(orderDetails);
            if (!orderDetailsAdded){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Drugs search = drugsDAO.search(orderDetailsDTO.getDrug_id());
            search.setDrug_quantity(search.getDrug_quantity() - orderDetailsDTO.getQty());
            boolean update = drugsDAO.updated(search);
            if (!update){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId();
    }

    @Override
    public ArrayList<DrugsDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<DrugsDTO> allDrugs = new ArrayList<>();
        ArrayList<Drugs> all = drugsDAO.getAll();
        for (Drugs item : all){
            allDrugs.add(new DrugsDTO(
                    item.getDrug_id(),
                    item.getDrug_name(),
                    item.getBatch_number(),
                    item.getMFD(),
                    item.getEXD(),
                    item.getDrug_quantity(),
                    item.getUnit_price(),
                    item.getUnit_discount(),
                    item.getSupply_id(),
                    item.getDescription()
            ));
        }
        return allDrugs;
    }

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> allOrders = new ArrayList<>();
        ArrayList<Orders> all = orderDAO.getAll();
        for (Orders orders : all) {
            allOrders.add(new OrderDTO(
                    orders.getInvoice_number(),
                    orders.getCashier_name(),
                    orders.getDate(),
                    orders.getTime(),
                    orders.getTotal()));
        }
        return allOrders;
    }

    @Override
    public DrugsDTO searchDrugs(String code) throws SQLException, ClassNotFoundException {
        Drugs drugs = drugsDAO.search(code);
        return new DrugsDTO(
                drugs.getDrug_id(),
                drugs.getDrug_name(),
                drugs.getDrug_quantity(),
                drugs.getUnit_price(),
                drugs.getUnit_discount()
        );
    }

    @Override
    public boolean ifDrugsExist(String code) throws SQLException, ClassNotFoundException {
        return drugsDAO.ifDrugsExist(code);
    }

    @Override
    public ArrayList<OrderDTO> searchOrder(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Orders> entityCustomerList = orderDAO.searchOrders(searchText);
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();

        for(Orders orders: entityCustomerList){
            OrderDTO orderDTO= new OrderDTO(
                    orders.getInvoice_number(),
                    orders.getCashier_name(),
                    orders.getDate(),
                    orders.getTime(),
                    orders.getTotal()
            );
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

}
