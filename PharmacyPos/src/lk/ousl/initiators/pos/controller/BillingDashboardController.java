package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.OrderBO;
import lk.ousl.initiators.pos.dao.custom.DrugsDAO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.*;
import lk.ousl.initiators.pos.entity.Drugs;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BillingDashboardController {
    public Label lblDate;
    public Label lblTime;
    public Label lblCashierName;
    public JFXComboBox<String> cmbPaymentOption;
    public Label lblTotalAmount;
    public JFXComboBox<String> cmbItemCode;
    public Label lblBalance;
    public Label lblInvoiceNo;
    public Label lblTotal;
    public Label lblDateTime;
    public JFXTextField txtCash;
    public JFXTextField txtItemCode;
    public JFXTextField lblDescription;
    public JFXTextField lblUnitPrice;
    public JFXTextField lblQtyOnHand;
    public JFXTextField lblDiscount;
    public JFXTextField txtQty;
    public JFXButton btnAddToCart;
    public TableView<CartDTO> tblBillingTable;
    public TableColumn<CartDTO, String> colItemCode;
    public TableColumn<CartDTO, String> colDescription;
    public TableColumn<CartDTO, Double> colUnitPrice;
    public TableColumn<CartDTO, Integer> colQty;
    public TableColumn<CartDTO, Double> colDiscount;
    public TableColumn<CartDTO, Double> colTotal;
    public TableColumn<CartDTO, Button> colOption;

    private final OrderBO orderBO = (OrderBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ORDER);

    public void initialize() {
        setDateAndTime();
        setDate();
        setTime();
        clearFields();
        loadAllPaymentMethods();
//        loadItemCodes();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblBillingTable.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });
    }

//    private void loadItemCodes(){
//        try {
//            ArrayList<DrugsDTO> all = orderBO.getAllItems();
//            for (DrugsDTO dto : all) {
//                cmbItemCode.getItems().add(dto.getDrug_id());
//                System.out.println(dto);
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    // Load Payment Methods
    private void loadAllPaymentMethods() {
        for (PaymentMethodDTO i : DBConnection.paymentMethodDTOS) {
            cmbPaymentOption.getItems().add(i.getPaymentName());
        }
    }

    // create the local date and time
    private void setDateAndTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   |   HH:mm:ss");
                    lblDateTime.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // create the payment details column local date
    private void setDate() {
        LocalDate localDate = LocalDate.now();
        lblDate.setText(String.valueOf(localDate));
    }

    // create the payment details column local Time
    private void setTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime localTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    lblTime.setText(localTime.format(formatter));
                }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnViewInventoryOnAction(ActionEvent actionEvent) {
    }

    public void btnViewOrderDetailsOnAction(ActionEvent actionEvent) {
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
    }

    private void setData(CartDTO dto) {
        cmbItemCode.setValue(dto.getCode());
        lblDescription.setText(dto.getDescription());
        lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        lblQtyOnHand.setPromptText(String.valueOf(dto.getQty()));
        lblDiscount.setText(String.valueOf(dto.getDiscount()));
        btnAddToCart.setText("Update Cart");
    }

    private void clearFields(){
        cmbItemCode.getSelectionModel().clearSelection();
        lblDescription.clear();
        lblUnitPrice.clear();
        lblQtyOnHand.clear();
        lblDiscount.clear();
        txtQty.clear();
    }
}
