package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.EmployeeBO;
import lk.ousl.initiators.pos.bo.custom.RegistrationBO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.EmployeeDTO;
import lk.ousl.initiators.pos.dto.JobRoleDTO;
import lk.ousl.initiators.pos.dto.RegistrationDTO;

import java.sql.Date;
import java.sql.SQLException;

public class RegistrationFormController {
    public AnchorPane RegistrationFormContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;
    public JFXComboBox<String> cmbJobRole;
    public JFXButton btnRegistration;

    private final RegistrationBO registrationBO = (RegistrationBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.REGISTRATION);

    public void initialize() {
        loadAllJobRoles();
    }

    private void loadAllJobRoles() {
        for (JobRoleDTO i : DBConnection.registrationDTOS) {
            cmbJobRole.getItems().add(i.getJobRole());
        }
    }


    public void RegisterOnAction(ActionEvent actionEvent) {
        try {
            boolean isSavedUser = registrationBO.saveUser(new RegistrationDTO(
                    txtUserName.getText(),
                    txtPassword.getText(),
                    txtConfirmPassword.getText(),
                    cmbJobRole.getValue()
            ));
            if (isSavedUser) {
                new Alert(Alert.AlertType.INFORMATION, "User Saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtUserName.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
        cmbJobRole.getSelectionModel().clearSelection();
    }
}
