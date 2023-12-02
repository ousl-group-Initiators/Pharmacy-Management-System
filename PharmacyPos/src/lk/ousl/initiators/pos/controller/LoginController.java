package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.LoginBO;
import lk.ousl.initiators.pos.dto.LoginDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public AnchorPane loginFormContext;
    public JFXTextField txtUserName;
    public JFXPasswordField pswPassword;
    public JFXButton btnLogIn;

    private final LoginBO loginBO = (LoginBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.LOGIN);

    public void loginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
//        Stage stage = (Stage) loginFormContext.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainContext.fxml")))));
//        stage.centerOnScreen();
//        stage.setResizable(true);
//    }

        String userName = txtUserName.getText();
        String password = pswPassword.getText();

        LoginDTO loginDTO = new LoginDTO(userName, password);
        loginBO.ifUserExists(userName, password);

        if (loginDTO.getUserName().equals("Shihan") && loginDTO.getPassword().equals("1234")){
            Stage logStage = (Stage) btnLogIn.getScene().getWindow();
            logStage.close();

            URL resource = this.getClass().getResource("../view/MainContext.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setTitle("ADMIN MAIN VIEW   |   SHIHAN NUWANJITH");
//            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

        }else if(loginDTO.getUserName().equals("Udayanga") && loginDTO.getPassword().equals("4567")){
            Stage logStage = (Stage) btnLogIn.getScene().getWindow();
            logStage.close();

            URL resource = this.getClass().getResource("../view/BillingDashboard.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setTitle("CASHIER MAIN VIEW   |   NUWANJITH UDAYANGA");
//            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        }else
            new Alert(Alert.AlertType.WARNING, "Invalid User Name or Password").show();

    }
}
