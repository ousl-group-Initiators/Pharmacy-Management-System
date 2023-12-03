package lk.ousl.initiators.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    public void manageSuppliersOnAction(ActionEvent actionEvent) {
    }

    // create ManageItems button OnAction method to load the dashboard ui
    public void manageItemsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ManageItem");
    }

    // create ManageDrugs button OnAction method to load the dashboard ui
    public void manageDrugsOnAction(ActionEvent actionEvent) {
    }

    // create ManageInventory button OnAction method to load the dashboard ui
    public void manageInventoryOnAction(ActionEvent actionEvent) {
    }

    // create Reports button OnAction method to load the dashboard ui
    public void reportsOnAction(ActionEvent actionEvent) {
    }

    // create Backups button OnAction method to load the dashboard ui
    public void backupsOnAction(ActionEvent actionEvent) {
    }

    // create Logout button OnAction method to load the dashboard ui
    public void logoutOnAction(ActionEvent actionEvent) {
    }
}
