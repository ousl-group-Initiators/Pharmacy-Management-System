package lk.ousl.initiators.pos.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class MainContextController {
    public Label lblDateTime;
    public AnchorPane mainContext;

    public void initialize() throws IOException {
        setDateAndTime();
        setUi("Dashboard");
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

    // create the method for loading the user interfaces
    private void setUi(String ui) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + ui + ".fxml")));
        mainContext.getChildren().clear();
        mainContext.getChildren().add(parent);
    }

    // create Dashboard button OnAction method to load the dashboard ui
    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }

    // create ManageEmployees button OnAction method to load the dashboard ui
    public void manageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ManageEmployee");
    }

    // create ManageSuppliers button OnAction method to load the dashboard ui
    public void manageSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ManageSuppliers");
    }

    // create ManageItems button OnAction method to load the dashboard ui
    public void manageItemsOnAction(ActionEvent actionEvent) {
    }

    // create ManageDrugs button OnAction method to load the dashboard ui
    public void manageDrugsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ManageDrugs");
    }

    // create ManageInventory button OnAction method to load the dashboard ui
    public void manageInventoryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Inventory");
    }

    // create Reports button OnAction method to load the dashboard ui
    public void reportsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Reports");
    }

    // create Orders button OnAction method to load the dashboard ui
    public void OrdersOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Orders");
    }

    // create Logout button OnAction method to load the dashboard ui
    public void logoutOnAction(ActionEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  want to Logout this System ?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                Stage stage = (Stage) mainContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml")))));
                stage.centerOnScreen();
                stage.setResizable(false);

                FadeTransition fadeTransition = new FadeTransition(Duration.millis(2500));
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(1.0);
                fadeTransition.play();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create Settings button OnAction method to load the dashboard ui
    public void settingsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Settings");
    }
}
