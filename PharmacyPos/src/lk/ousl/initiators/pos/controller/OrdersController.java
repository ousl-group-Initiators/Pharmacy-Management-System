package lk.ousl.initiators.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.OrderBO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.EmployeeDTO;
import lk.ousl.initiators.pos.dto.OrderDTO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class OrdersController {
    public AnchorPane OrdersContext;
    public TextField txtSearch;
    public TableView<OrderDTO> tblOrders;
    public TableColumn<OrderDTO, String> colInvoiceNumber;
    public TableColumn<OrderDTO, String> colCashierName;
    public TableColumn<OrderDTO, Date> colDate;
    public TableColumn<OrderDTO, Time> colTime;
    public TableColumn<OrderDTO, Double> colTotal;
    public TableColumn<OrderDTO, Button> colOption;
    private String searchText = "";

    private final OrderBO orderBO = (OrderBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ORDER);

    public void initialize() {
        colInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoice_number"));
        colCashierName.setCellValueFactory(new PropertyValueFactory<>("cashier_name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadOrders();

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            searchOrders(searchText);
        });
        searchOrders(searchText);

    }

    private void searchOrders(String text){
        String searchText = "%" + text + "%";
        ObservableList<OrderDTO> orderDTOS = FXCollections.observableArrayList();
        try {
            // Assuming orderBO.getAllOrders() returns an ArrayList<OrderDTO>
            ArrayList<OrderDTO> allOrders = orderBO.searchOrder(searchText);

            for (OrderDTO orders : allOrders) {
                Button btn = new Button("View more");
                OrderDTO orderDTO = new OrderDTO(
                        orders.getInvoice_number(),
                        orders.getCashier_name(),
                        orders.getDate(),
                        orders.getTime(),
                        orders.getTotal(),
                        btn
                );

                // Add the current orderDTO to the observable list
                orderDTOS.add(orderDTO);

                btn.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/OrderDetails.fxml"));
                        Parent parent = loader.load();
                        OrderDetailsController controller = loader.getController();
                        controller.loadItemDetails(orderDTO.getInvoice_number());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }  catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        // Set the items in tblOrders with the observable list
        tblOrders.setItems(orderDTOS);
    }

    private void loadOrders() {
//        ObservableList<OrderDTO> orderDTOS = FXCollections.observableArrayList();
//        try {
//            // Assuming orderBO.getAllOrders() returns an ArrayList<OrderDTO>
//            ArrayList<OrderDTO> allOrders = orderBO.getAllOrders();
//
//            for (OrderDTO orders : allOrders) {
//                Button btn = new Button("View more");
//                OrderDTO orderDTO = new OrderDTO(
//                        orders.getInvoice_number(),
//                        orders.getCashier_name(),
//                        orders.getDate(),
//                        orders.getTime(),
//                        orders.getTotal(),
//                        btn
//                );
//
//                // Add the current orderDTO to the observable list
//                orderDTOS.add(orderDTO);
//
//                btn.setOnAction(event -> {
//                    try {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/OrderDetails.fxml"));
//                        Parent parent = loader.load();
//                        OrderDetailsController controller = loader.getController();
//                        controller.loadItemDetails(orderDTO.getInvoice_number());
//                        Stage stage = new Stage();
//                        stage.setScene(new Scene(parent));
//                        stage.show();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        }  catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        // Set the items in tblOrders with the observable list
//        tblOrders.setItems(orderDTOS);
    }

}
