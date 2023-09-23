package lk.ousl.initiators.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public AnchorPane loginFormContext;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) loginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainContext.fxml")))));
        stage.centerOnScreen();
        stage.setResizable(true);
    }
}
