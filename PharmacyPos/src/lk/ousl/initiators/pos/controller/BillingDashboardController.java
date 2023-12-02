package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BillingDashboardController {
    public Label lblDate;
    public Label lblTime;
    public Label lblCashierName;
    public JFXComboBox comboPaymentOption;
    public Label lblTotalAmount;
    public Label lblCash;
    public Label lblBalance;
    public Label lblInvoiceNo;
    public TableView tblBillingTable;
    public Label lblTotal;
    public Label lblDateTime;

    public void initialize() throws IOException {
        setDateAndTime();
    }

    // create the local date and time
    private void setDateAndTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   |   HH:mm:ss");
                    lblDateTime.setText(LocalDateTime.now().format(formatter));
                }),new KeyFrame(Duration.seconds(1))
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
}
