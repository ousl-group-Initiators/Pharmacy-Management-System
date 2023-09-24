package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.EmployeeBO;
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
        clearFields();

        colNic.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
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
            searchEmployees(searchText);
        });
        searchEmployees(searchText);

//        cmbJobRole.getItems().addAll("Admin","Cashier","Pharmacist","Pharmacy Technician","Pharmacy Assistant");

    }

    private void loadAllJobRoles() {
        for (JobRoleDTO i : DBConnection.jobRoleDTOS) {
            cmbJobRole.getItems().add(i.getJobRole());
        }
    }


    public void btnSaveEmployeeOnAction(ActionEvent actionEvent) {
        if (btnSaveEmployee.getText().equalsIgnoreCase("Save Employee")) {
            try {
                boolean isSavedEmployee = employeeBO.saveEmployee(new EmployeeDTO(
                        txtEmployeeNic.getText(),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        Date.valueOf(dateDateOfBirth.getValue()),
                        Integer.parseInt(txtAge.getText()),
                        Integer.parseInt(txtTelephoneNumber.getText()),
                        txtAddress.getText(),
                        cmbJobRole.getValue().toString(),
                        txtDescription.getText()
                ));
                if (isSavedEmployee) {
                    searchEmployees(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Employee Saved!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean isUpdatedEmployee = employeeBO.updateEmployee(new EmployeeDTO(
                        txtEmployeeNic.getText(),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        Date.valueOf(dateDateOfBirth.getValue()),
                        Integer.parseInt(txtAge.getText()),
                        Integer.parseInt(txtTelephoneNumber.getText()),
                        txtAddress.getText(),
                        cmbJobRole.getValue().toString(),
                        txtDescription.getText()
                ));
                 if (isUpdatedEmployee) {
                    searchEmployees(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Employee Updated !").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void searchEmployees(String text) {
        String searchText = "%" + text + "%";
        //set value to table
        ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList();
        try {
            ArrayList<EmployeeDTO> list = employeeBO.searchEmployee(searchText);
            for (EmployeeDTO employeeDTO : list) {
                Button button = new Button("Delete");
                EmployeeDTO employeeDTO1 = new EmployeeDTO(
                        employeeDTO.getEmp_id(),
                        employeeDTO.getFirst_name(),
                        employeeDTO.getLast_name(),
                        employeeDTO.getDate_of_birth(),
                        employeeDTO.getAge(),
                        employeeDTO.getTelephone_number(),
                        employeeDTO.getAddress(),
                        employeeDTO.getJob_role(),
                        employeeDTO.getDescription(),
                        button
                );
                employeeDTOS.add(employeeDTO1);
                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  want to delete this employee ?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {

                        try {
                            boolean isDeleteCustomer = employeeBO.deleteEmployee(employeeDTO1.getEmp_id());
                            if (isDeleteCustomer) {
                                searchEmployees(searchText);
                                new Alert(Alert.AlertType.INFORMATION, "Employee Deleted").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
                            }
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        tblEmployee.setItems(employeeDTOS);
    }

   /* private void loadAllEmployees() {
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
    }*/

   /* boolean existEmployeeId(String id) throws SQLException, ClassNotFoundException {
        return employeeBO.ifEmployeeExist(id);
    }*/

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
        dateDateOfBirth.setValue(null);
        txtAge.clear();
        txtTelephoneNumber.clear();
        txtAddress.clear();
        cmbJobRole.getSelectionModel().clearSelection();
        txtDescription.clear();
    }

}
