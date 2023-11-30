package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.DrugsBO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.EmployeeDTO;
import lk.ousl.initiators.pos.dto.JobRoleDTO;
import lk.ousl.initiators.pos.dto.SupplyIdDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ManageDrugsController {
    public AnchorPane manageDrugsContext;
    public JFXTextField txtDrugsId;
    public DatePicker dateManufactureDate;
    public DatePicker dateExpireDate;
    public JFXComboBox cmbSupplierId;
    public JFXTextField txtBatchNumber;
    public JFXTextField txtDrugsName;
    public JFXTextField txtDrugQuantity;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtUnitDiscount;
    public JFXTextArea txtDescription;
    public JFXButton btnSaveDrugs;
    public JFXButton btnNewDrugs;
    public TextField txtSearch;
    public TableView<DrugsDTO> tblDrugs;
    public TableColumn<DrugsDTO, String> colDrugsId;
    public TableColumn<DrugsDTO, String> colName;
    public TableColumn<DrugsDTO, String> colBatchNumber;
    public TableColumn<DrugsDTO, DatePicker> colMFD;
    public TableColumn<DrugsDTO, DatePicker> colEXD;
    public TableColumn<DrugsDTO, Integer> colDrugQuantity;
    public TableColumn<DrugsDTO, Double> colUnitPrice;
    public TableColumn<DrugsDTO, Double> colDiscount;
    public TableColumn<DrugsDTO, String> colSupplierId;
    public TableColumn<EmployeeDTO, Button> colOption;
    public TableColumn<DrugsDTO, String> colDescription;
    private String searchText = "";

    private final DrugsBO drugsBO = (DrugsBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Drugs);

    public void initialize(){
        loadAllSupplyId();
        clearFields();

        colDrugsId.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("drug_name"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batch_number"));
        colMFD.setCellValueFactory(new PropertyValueFactory<>("MFD"));
        colEXD.setCellValueFactory(new PropertyValueFactory<>("EXD"));
        colDrugQuantity.setCellValueFactory(new PropertyValueFactory<>("drug_quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("unit_discount"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supply_id"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));
        //colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tblDrugs.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            searchDrugs(searchText);
        });
        searchDrugs(searchText);

    }


    private void loadAllSupplyId() {
        for (SupplyIdDTO i : DBConnection.supplyIdDTOS) {
            cmbSupplierId.getItems().add(i.getSupplyId());
        }
    }


    public void btnSaveDrugsOnAction(ActionEvent actionEvent) {
        if (btnSaveDrugs.getText().equalsIgnoreCase("Save Drugs")) {
            try {
                boolean isSavedDrugs = drugsBO.saveDrugs(new DrugsDTO(
                        txtDrugsId.getText(),
                        txtDrugsName.getText(),
                        txtBatchNumber.getText(),
                        Date.valueOf(dateManufactureDate.getValue()),
                        Date.valueOf(dateExpireDate.getValue()),
                        Integer.parseInt(txtDrugQuantity.getText()),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Double.parseDouble(txtUnitDiscount.getText()),
                        cmbSupplierId.getValue().toString(),
                        txtDescription.getText()
                ));
                if (isSavedDrugs) {
                    searchDrugs(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Drugs Saved!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean isUpdatedDrugs = drugsBO.updateDrugs(new DrugsDTO(
                        txtDrugsId.getText(),
                        txtDrugsName.getText(),
                        txtBatchNumber.getText(),
                        Date.valueOf(dateManufactureDate.getValue()),
                        Date.valueOf(dateExpireDate.getValue()),
                        Integer.parseInt(txtDrugQuantity.getText()),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Double.parseDouble(txtUnitDiscount.getText()),
                        cmbSupplierId.getValue().toString(),
                        txtDescription.getText()
                ));
                if (isUpdatedDrugs) {
                    searchDrugs(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Drugs Updated !").show();


                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void searchDrugs(String text) {
        String searchText = "%" + text + "%";
        //set value to table
        ObservableList<DrugsDTO> drugsDTOS = FXCollections.observableArrayList();
        try {
            ArrayList<DrugsDTO> list = drugsBO.searchDrugs(searchText);
            for (DrugsDTO drugsDTO : list) {
                Button button = new Button("Delete");
                DrugsDTO drugsDTO1 = new DrugsDTO(
                        drugsDTO.getDrug_id(),
                        drugsDTO.getDrug_name(),
                        drugsDTO.getBatch_number(),
                        drugsDTO.getMFD(),
                        drugsDTO.getEXD(),
                        drugsDTO.getDrug_quantity(),
                        drugsDTO.getUnit_price(),
                        drugsDTO.getUnit_discount(),
                        drugsDTO.getSupply_id(),
                        drugsDTO.getDescription(),
                        button
                );
                drugsDTOS.add(drugsDTO1);
                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  want to delete this drug ?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {

                        try {
                            boolean isDeleteCustomer = drugsBO.deleteDrugs(drugsDTO1.getDrug_id());
                            if (isDeleteCustomer) {
                                searchDrugs(searchText);
                                new Alert(Alert.AlertType.INFORMATION, "Drug Deleted").show();
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
        tblDrugs.setItems(drugsDTOS);
    }

    public void btnNewDrugsOnAction(ActionEvent actionEvent) {
        clearFields();
        btnSaveDrugs.setText("Save Drugs");
    }

    private void setData(DrugsDTO dto) {
        txtDrugsId.setText(dto.getDrug_id());
        txtDrugsName.setText(dto.getDrug_name());
        txtBatchNumber.setText(dto.getBatch_number());
        dateManufactureDate.setPromptText(String.valueOf(dto.getMFD()));
        dateExpireDate.setPromptText(String.valueOf(dto.getEXD()));
        txtDrugQuantity.setText(String.valueOf(dto.getDrug_quantity()));
        txtUnitPrice.setText(String.valueOf(dto.getUnit_price()));
        txtUnitDiscount.setText(String.valueOf(dto.getUnit_discount()));
        cmbSupplierId.setValue(dto.getSupply_id());
        txtDescription.setText(dto.getDescription());
        btnSaveDrugs.setText("Update Drugs");
    }

    private void clearFields() {
        txtDrugsId.clear();
        txtDrugsName.clear();
        txtBatchNumber.clear();
        dateManufactureDate.setValue(null);
        dateExpireDate.setValue(null);
        txtDrugQuantity.clear();
        txtUnitPrice.clear();
        txtUnitDiscount.clear();
        cmbSupplierId.getSelectionModel().clearSelection();
        txtDescription.clear();
    }
}


