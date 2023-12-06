package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.RegistrationBO;
import lk.ousl.initiators.pos.bo.custom.ReportBO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReportsController {
    public AnchorPane ReportsContext;
    public Label lblDate;
    public Label lblDailySalesTotal;
    public Label bllTotalSalesTotal;
    public Label lblMonth;
    public Label lblMonthlySalesTotal;
    public JFXButton btnViewDailySales;

    private final ReportBO reportBO = (ReportBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.REPORT);

    public void initialize() {
        setDate();
        setMonth();
        calculateAndDisplayDailySales();
        calculateAndDisplayTotalSales();
        calculateAndDisplayMonthlySales();
    }

    // create the payment details column local date
    private void setDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public void setMonth() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Format the current month as a string
        String currentMonth = currentDate.format(DateTimeFormatter.ofPattern("MMMM"));
        // Set the formatted month in the label
        lblMonth.setText(currentMonth);
    }

    public void btnViewDailySalesOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Orders.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void calculateAndDisplayDailySales() {
        try {
            double dailySales = reportBO.getDailySales();
            lblDailySalesTotal.setText(String.format("%.2f", dailySales));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calculateAndDisplayTotalSales() {
        try {
            double totalSale = reportBO.getTotalSales();
            bllTotalSalesTotal.setText(String.format("%.2f", totalSale));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calculateAndDisplayMonthlySales() {
        try {
            double monthlySales = reportBO.getTotalSales();
            lblMonthlySalesTotal.setText(String.format("%.2f", monthlySales));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
