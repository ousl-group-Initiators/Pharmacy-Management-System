package lk.ousl.initiators.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ousl.initiators.pos.dto.OrderDTO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
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

    private final OrderBO orderBO = (OrderBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ORDER);

    public void initialize(){
        colInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoice_number"));
        colCashierName.setCellValueFactory(new PropertyValueFactory<>("cashier_name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadOrders();
    }

    private void loadOrders(){
        try{
            String sql = "SELECT * FROM `Order`";
            PreparedStatement statement = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();

            ObservableList<OrderDTO> obsOderList = FXCollections.observableArrayList();
            while (set.next()){

                Button btn = new Button("View more");

                OrderDTO orderDTO = new OrderDTO(set.getString(1),
                        set.getString(2),
                        set.getDate(3),  //set.getString(2)
                        set.getTime(4),
                        set.getDouble(5),
                        btn);
                obsOderList.add(orderDTO);

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
            tblOrders.setItems(obsOderList);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }













}
