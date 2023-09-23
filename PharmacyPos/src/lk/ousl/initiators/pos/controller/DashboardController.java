package lk.ousl.initiators.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {

    public AnchorPane dashboardContext;

    // create View Billing Dashboard button OnAction method to load the billingDashboard ui
    public void viewBillingDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/BillingDashboard.fxml")))));
    }
}
