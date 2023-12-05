package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {

    public AnchorPane dashboardContext;
    public JFXButton btnMangeDrugs;
    public JFXButton btnManageItem;

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
}
