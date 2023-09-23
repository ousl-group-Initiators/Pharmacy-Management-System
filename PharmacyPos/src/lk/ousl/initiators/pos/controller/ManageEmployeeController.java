package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.EmployeeBO;
import lk.ousl.initiators.pos.dao.CrudUtil;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.EmployeeDTO;
import lk.ousl.initiators.pos.dto.JobRoleDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
    public TableColumn<EmployeeDTO, String> colNic;
    public TableColumn<EmployeeDTO, String> colName;
    public TableColumn<EmployeeDTO, DatePicker> colDOB;
    public TableColumn<EmployeeDTO, Integer> colTelephoneNumber;
    public TableColumn<EmployeeDTO, String> colAddress;
    public TableColumn<EmployeeDTO, String> colJobRole;
    public TableColumn<EmployeeDTO, Button> colOption;
    public TextField txtSearch;
    public JFXButton btnSaveEmployee;
    public JFXButton btnNewEmployee;
    private String searchText = "";

    private final EmployeeBO employeeBO = (EmployeeBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Employee);


    public void initialize() {
        loadAllJobRoles();
        loadAllEmployees();
        clearFields();
//        searchEmployees(searchText);

        colNic.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
//        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
//        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("age"));
        colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<>("telephone_number"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("job_role"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
//            searchEmployees(searchText);
        });


    }

    private void loadAllJobRoles() {
        for (JobRoleDTO i : DBConnection.jobRoleDTOS) {
            cmbJobRole.getItems().add(i.getJobRole());
        }
    }


    public void btnSaveEmployeeOnAction(ActionEvent actionEvent) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                txtEmployeeNic.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                Date.valueOf(dateDateOfBirth.getValue()),
                Integer.parseInt(txtAge.getText()),
                Integer.parseInt(txtTelephoneNumber.getText()),
                txtAddress.getText(),
                cmbJobRole.getValue().toString(),
                txtDescription.getText()
        );
        if (btnSaveEmployee.getText().equalsIgnoreCase("Save Employee")) {
            try {
                if(existCustomerId(employeeDTO.getEmp_id())){
                    new Alert(Alert.AlertType.ERROR, employeeDTO.getEmp_id() + " " +"Already Exists").show();
                }else if (employeeBO.addEmployee(employeeDTO)) {
                    new Alert(Alert.AlertType.INFORMATION, "Employee Saved!").show();
//                    searchEmployees(searchText);
                    clearFields();
                    tblEmployee.refresh();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!existCustomerId(employeeDTO.getEmp_id())){
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with this Employee Id " +" "+ employeeDTO.getEmp_id()).show();
                }else if (employeeBO.updateEmployee(employeeDTO)) {
                    new Alert(Alert.AlertType.INFORMATION, "Employee Updated !").show();
//                    searchEmployees(searchText);
                    clearFields();
                    tblEmployee.refresh();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

   /* private void searchEmployees(String text){
        String searchText = "%"+text+"%";
        try {
            ObservableList<EmployeeDTO> tmList = FXCollections.observableArrayList();
            Button button = new Button("Delete");
//            while (employeeBO.searchEmployee()){
//                Button button = new Button("Delete");
//                EmployeeDTO employeeDTO = new EmployeeDTO(
//
//                );
            button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to delete this customer?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    try {
                        if (employeeBO.deleteEmployee(searchText)) {
                            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//         catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }*/

    private void loadAllEmployees() {
        try {
            ArrayList<EmployeeDTO> allEmployees = employeeBO.getAllEmployee();
            for (EmployeeDTO e : allEmployees) {
                tblEmployee.getItems().add(new EmployeeDTO(
                        e.getEmp_id(),
                        e.getFirst_name(),
                        e.getLast_name(),
                        e.getDate_of_birth(),
                        e.getAge(),
                        e.getTelephone_number(),
                        e.getAddress(),
                        e.getJob_role(),
                        e.getDescription()
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    boolean existCustomerId(String id) throws SQLException, ClassNotFoundException {
        return employeeBO.employeeExist(id);
    }

    public void btnNewEmployeeOnAction(ActionEvent actionEvent) {
        clearFields();
        btnSaveEmployee.setText("Save Employee");
    }

    private void setData(EmployeeDTO dto) {
        txtEmployeeNic.setText(dto.getEmp_id());
        txtFirstName.setText(dto.getFirst_name());
        txtLastName.setText(dto.getLast_name());
        dateDateOfBirth.setPromptText(String.valueOf(dto.getDate_of_birth()));
        txtAge.setText(String.valueOf(dto.getAge()));
        txtTelephoneNumber.setText(String.valueOf(dto.getTelephone_number()));
        txtAddress.setText(dto.getAddress());
        cmbJobRole.setValue(dto.getJob_role());
        txtDescription.setText(dto.getDescription());
        btnSaveEmployee.setText("Update Employee");
    }

    private void clearFields() {
        txtEmployeeNic.clear();
        txtFirstName.clear();
        txtLastName.clear();
        dateDateOfBirth.setPromptText("");
        txtAge.clear();
        txtTelephoneNumber.clear();
        txtAddress.clear();
        cmbJobRole.setValue("Job Role");
        txtDescription.clear();
    }

}
