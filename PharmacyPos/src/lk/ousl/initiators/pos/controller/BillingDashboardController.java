package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BillingDashboardController {
    public Label lblDate;
    public Label lblTime;
    public Label lblCashierName;
    public JFXComboBox comboPaymentOption;
    public Label lblTotalAmount;
    public Label lblCash;
    public Label lblBalance;
    public JFXTextField txtItemCode;
    public JFXTextField txtQty;
    public TableView tblBillingTable;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblTotal;

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
    }

    public void btnViewInventoryOnAction(ActionEvent actionEvent) {
    }

    public void btnViewOrderDetailsOnAction(ActionEvent actionEvent) {
    }
}
