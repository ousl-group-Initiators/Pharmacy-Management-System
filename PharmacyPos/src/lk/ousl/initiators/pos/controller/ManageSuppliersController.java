package lk.ousl.initiators.pos.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.SupplierBO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.SupplierDTO;
import lk.ousl.initiators.pos.dto.SupplierDTO;
import lk.ousl.initiators.pos.dto.JobRoleDTO;
import lk.ousl.initiators.pos.entity.Supplier;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ManageSuppliersController {
    public AnchorPane manageSupplierContext;
    public JFXTextField txtSupplierNic;
    public JFXTextField txtLastName;
    public JFXTextField txtAge;
    public JFXTextField txtFirstName;
    public JFXTextField txtTelephoneNumber;
    public DatePicker dateDateOfBirth;
    public JFXTextArea txtAddress;
    public JFXTextField txtEmail;
    public JFXTextArea txtDescription;
    public TableView<SupplierDTO> tblSupplier;
    public TableColumn<SupplierDTO, String> colNic;
    public TableColumn<SupplierDTO, String> colName;
    public TableColumn<SupplierDTO, DatePicker> colDOB;
    public TableColumn<SupplierDTO, Integer> colTelephoneNumber;
    public TableColumn<SupplierDTO, String> colAddress;
    public TableColumn<SupplierDTO, String> colEmail;
    public TableColumn<SupplierDTO, Button> colOption;
    public TextField txtSearch;
    public JFXButton btnSaveSupplier;
    public JFXButton btnNewSupplier;
    private String searchText = "";

    private final SupplierBO supplierBO = (SupplierBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Supplier);

    public void initialize() {

        clearFields();

        colNic.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<>("telephone_number"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            searchSuppliers(searchText);
        });
        searchSuppliers(searchText);
    }

    public void btnSaveSupplierOnAction(ActionEvent actionEvent) {

        if (btnSaveSupplier.getText().equalsIgnoreCase("Save Supplier")) {
            try {
                boolean isSavedSupplier = supplierBO.saveSupplier(new SupplierDTO(
                        txtSupplierNic.getText(),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        Date.valueOf(dateDateOfBirth.getValue()),
                        Integer.parseInt(txtAge.getText()),
                        Integer.parseInt(txtTelephoneNumber.getText()),
                        txtAddress.getText(),
                        txtEmail.getText(),
                        txtDescription.getText()
                ));
                if (isSavedSupplier) {
                    searchSuppliers(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Saved!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean isUpdatedSupplier = supplierBO.updateSupplier(new SupplierDTO(
                        txtSupplierNic.getText(),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        Date.valueOf(dateDateOfBirth.getValue()),
                        Integer.parseInt(txtAge.getText()),
                        Integer.parseInt(txtTelephoneNumber.getText()),
                        txtAddress.getText(),
                        txtEmail.getText(),
                        txtDescription.getText()
                ));
                if (isUpdatedSupplier) {
                    searchSuppliers(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Updated !").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void searchSuppliers(String text) {
        String searchText = "%" + text + "%";
        //set value to table
        ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList();
        try {
            ArrayList<SupplierDTO> list = supplierBO.searchSupplier(searchText);
            for (SupplierDTO supplierDTO : list) {
                Button button = new Button("Delete");


                SupplierDTO supplierDTO1 = new SupplierDTO(
                        supplierDTO.getSupplier_id(),
                        supplierDTO.getFirst_name(),
                        supplierDTO.getLast_name(),
                        supplierDTO.getDate_of_birth(),
                        supplierDTO.getAge(),
                        supplierDTO.getTelephone_number(),
                        supplierDTO.getAddress(),
                        supplierDTO.getEmail(),
                        supplierDTO.getDescription(),
                        button
                );
                supplierDTOS.add(supplierDTO1);
                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  want to delete this supplier ?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {

                        try {
                            boolean isDeleteCustomer = supplierBO.deleteSupplier(supplierDTO1.getSupplier_id());
                            if (isDeleteCustomer) {
                                searchSuppliers(searchText);
                                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted").show();
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
        tblSupplier.setItems(supplierDTOS);
    }

   /* private void loadAllSuppliers() {
        try {
            ArrayList<SupplierDTO> allSuppliers = employeeBO.getAllSupplier();
            for (SupplierDTO e : allSuppliers) {
                tblSupplier.getItems().add(new SupplierDTO(
                        e.getSupplier_id(),
                        e.getFirst_name(),
                        e.getLast_name(),
                        e.getDate_of_birth(),
                        e.getAge(),
                        e.getTelephone_number(),
                        e.getAddress(),
                        e.getEmail(),
                        e.getDescription()
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

   /* boolean existSupplierId(String id) throws SQLException, ClassNotFoundException {
        return supplierBO.ifSupplierExist(id);
    }*/

    public void btnNewSupplierOnAction(ActionEvent actionEvent) {

        clearFields();
        btnSaveSupplier.setText("Save Supplier");
    }

    private void setData(SupplierDTO dto) {
        txtSupplierNic.setText(dto.getSupplier_id());
        txtFirstName.setText(dto.getFirst_name());
        txtLastName.setText(dto.getLast_name());
        dateDateOfBirth.setPromptText(String.valueOf(dto.getDate_of_birth()));
        txtAge.setText(String.valueOf(dto.getAge()));
        txtTelephoneNumber.setText(String.valueOf(dto.getTelephone_number()));
        txtAddress.setText(dto.getAddress());
        txtEmail.setText(dto.getEmail());
        txtDescription.setText(dto.getDescription());
        btnSaveSupplier.setText("Update Supplier");
    }

    private void clearFields() {
        txtSupplierNic.clear();
        txtFirstName.clear();
        txtLastName.clear();
        dateDateOfBirth.setValue(null);
        txtAge.clear();
        txtTelephoneNumber.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtDescription.clear();
    }

}
