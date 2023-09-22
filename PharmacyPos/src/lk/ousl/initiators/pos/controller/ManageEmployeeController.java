package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.dto.EmployeeDTO;

public class ManageEmployeeController {
    public AnchorPane manageEmployeeContext;


    public JFXTextField txtEmployeeNic;
    public JFXTextField txtLastName;
    public JFXTextField txtAge;
    public JFXTextField txtFirstName;
    public JFXTextField txtTelephoneNumber;
    public DatePicker dateDateOfBirth;
    public JFXTextArea txtAddress;
    public JFXComboBox cmbJobRole;
    public JFXTextArea txtDescription;
    public TableView<EmployeeDTO> tblEmployee;
    public TableColumn<EmployeeDTO, String > colNic;
    public TableColumn<EmployeeDTO, String> colName;
    public TableColumn<EmployeeDTO, DatePicker> colDOB;
    public TableColumn<EmployeeDTO, Integer> colTelephoneNumber;
    public TableColumn<EmployeeDTO, String> colAddress;
    public TableColumn<EmployeeDTO, ComboBox> colJobRole;
    public TableColumn<EmployeeDTO, Button> colOption;
    public TextField txtSearch;

    public void btnSaveEmployeeOnAction(ActionEvent actionEvent) {

    }

    public void btnNewEmployeeOnAction(ActionEvent actionEvent) {

    }
}
