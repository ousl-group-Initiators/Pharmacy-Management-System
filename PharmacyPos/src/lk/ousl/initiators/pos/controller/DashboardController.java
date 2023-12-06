package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.DashboardBO;
import lk.ousl.initiators.pos.bo.custom.OrderBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class DashboardController {

    public AnchorPane dashboardContext;
    public JFXButton btnMangeDrugs;
    public JFXButton btnManageItem;
    public Label lblExpireDrugs;
    public Label lblDrugOutOfStock;
    public Label lblExpiredItems;
    public Label lblItemOutOfStock;
    public Label lblStockedDrugs;
    public Label lblStockedItems;
    public Label lblTotalEmployee;
    public JFXButton btnManageEmployee;
    public Label lblTotalSuppliers;
    public JFXButton btnManageSupplier;

    private final DashboardBO dashboardBO = (DashboardBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.DASHBOARD);

    public void initialize() {
        calculateEmployeeCount();
        calculateSuppliersCount();
        calculateDrugsCount();
    }

    // create View Billing Dashboard button OnAction method to load the billingDashboard ui
    public void viewBillingDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/BillingDashboard.fxml")))));
    }

    public void manageDrugsOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ManageDrugs.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void manageItemOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ManageItem.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void manageDrugsOnAction02(ActionEvent actionEvent) throws IOException {
        manageDrugsOnAction(actionEvent);
    }

    public void manageItemOnAction02(ActionEvent actionEvent) throws IOException {
        manageItemOnAction(actionEvent);
    }

    public void manageDrugsOnAction03(ActionEvent actionEvent) throws IOException {
        manageDrugsOnAction(actionEvent);
    }

    public void manageItemOnAction03(ActionEvent actionEvent) throws IOException {
        manageItemOnAction(actionEvent);
    }

    public void ManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ManageEmployee.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void ManageSupplierOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ManageSuppliers.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void calculateEmployeeCount() {
        try {
            int employeeCount = dashboardBO.getEmployeeCount();
            lblTotalEmployee.setText(String.valueOf(employeeCount));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void calculateSuppliersCount() {
        try {
            int supplierCount = dashboardBO.getSuppliersCount();
            lblTotalEmployee.setText(String.valueOf(supplierCount));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void calculateDrugsCount() {
        try {
            int drugsCount = dashboardBO.getDrugsCount();
            lblStockedDrugs.setText(String.valueOf(drugsCount));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
