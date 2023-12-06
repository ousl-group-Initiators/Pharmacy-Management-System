package lk.ousl.initiators.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.OrderBO;
import lk.ousl.initiators.pos.bo.custom.OrderDetailsBO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.CartDTO;
import lk.ousl.initiators.pos.dto.OrderDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsController {
    public AnchorPane OrderDetailsContext;
    public TableView<OrderDetailsDTO> tblOrderDetails;
    public TableColumn<OrderDetailsDTO, String> colItemCode;
    public TableColumn<OrderDetailsDTO, String> colDescription;
    public TableColumn<OrderDetailsDTO, Double> colUnitPrice;
    public TableColumn<OrderDetailsDTO, Integer> colQty;
    public TableColumn<OrderDetailsDTO, Double> colDiscount;
    public TableColumn<OrderDetailsDTO, Double> colTotal;

    private final OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ORDERDETAILS);

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadOrders();

    }

    public void loadItemDetails(String oderId){

//        try{
//            String sql = "SELECT o.invoice_number, d.invoice_number, d.drug_id,d.description d.unitprice, d.qty"+
//                    " FROM `Order` o INNER JOIN  `Order Details` d ON o.invoice_number=d.invoice_number AND o.invoice_number=?";
//            PreparedStatement statement = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
//            statement.setString(1,oderId);
//            ResultSet set = statement.executeQuery();
//
//            ObservableList<CartDTO> obsItemList = FXCollections.observableArrayList();
//            while (set.next()){
//                double tempUnitPrice = set.getDouble(3);
//                int tempQtyOnHand = set.getInt(4);
//                double tempDiscount = set.getDouble(5);
//                double tempTotal = tempQtyOnHand * tempUnitPrice;
//
//                CartDTO itemDetailsTm = new CartDTO(set.getString(1),set.getString(2),tempUnitPrice,tempQtyOnHand,tempDiscount,tempTotal);
//                obsItemList.add(itemDetailsTm);
//            }
//            tblOrderDetails.setItems(obsItemList);
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    private void loadOrders() {
        ObservableList<OrderDetailsDTO> orderDetailsDTOS = FXCollections.observableArrayList();
        try {
            // Assuming orderBO.getAllOrders() returns an ArrayList<OrderDTO>
            ArrayList<OrderDetailsDTO> allOrderDetails = orderDetailsBO.getAllOrder();

            for (OrderDetailsDTO orderDetails : allOrderDetails) {
                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                        orderDetails.getDrug_id(),
                        orderDetails.getDescription(),
                        orderDetails.getUnitPrice(),
                        orderDetails.getQty(),
                        orderDetails.getDiscount(),
                        orderDetails.getTotal()
                );

                // Add the current orderDTO to the observable list
                orderDetailsDTOS.add(orderDetailsDTO);
            }
        }  catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        // Set the items in tblOrderDetails with the observable list
        tblOrderDetails.setItems(orderDetailsDTOS);
    }
}
