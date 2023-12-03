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
import lk.ousl.initiators.pos.bo.custom.ItemBO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.ItemDTO;
import lk.ousl.initiators.pos.dto.SupplierDTO;
import lk.ousl.initiators.pos.entity.Item;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ManageItemController {

    public AnchorPane manageItemContext;

    public JFXTextField txtItemId;

    public DatePicker dateExpDate;

    public JFXTextField txtItemName;

    public JFXTextField txtItemQty;

    public JFXTextField txtItemCode;

    public JFXTextField txtUnitPrice;

    public DatePicker dateManufactureDate;

    public JFXTextField txtUnitDiscount;

    public JFXComboBox<String> cmbSupplierID;

    public JFXTextArea txtItemDescription;

    public TableView<ItemDTO> tblItem;

    public TableColumn<ItemDTO, String> colItemId;

    public TableColumn<ItemDTO, String> colItemName;

    public TableColumn<ItemDTO, String> colItemCode;

    public TableColumn<ItemDTO, DatePicker> colManufactureDate;

    public TableColumn<ItemDTO, DatePicker> colExpireDate;

    public TableColumn<ItemDTO, Integer> colItemQty;

    public TableColumn<ItemDTO, Double> colUnitPrice;

    public TableColumn<ItemDTO, Double> colUnitDiscount;

    public TableColumn<ItemDTO, String> colSupplierID;

    public TableColumn<ItemDTO, String> colOption;

    public JFXButton btnNewItem;

    public JFXButton btnSaveItem;
    public TextField txtSearchItem;
//    public JFXComboBox cmbSupplierID;
    private String searchText = "";

    private final ItemBO itemBO = (ItemBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Item);


    public void initialize() {
        loadAllSupplierID();
        clearFields();

        colItemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        colManufactureDate.setCellValueFactory(new PropertyValueFactory<>("manufacture_date"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expire_date"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("item_qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colUnitDiscount.setCellValueFactory(new PropertyValueFactory<>("unit_discount"));
     //   cmbSupplierID.setCellFactory(new PropertyValueFactory<>("supplier_id"));
//      cmbSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblItem.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });

        txtSearchItem.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            searchItems(searchText);
        });
        searchItems(searchText);
    }

    private void loadAllSupplierID() {
        for (SupplierDTO i : DBConnection.supplierDTOS) {
            cmbSupplierID.getItems().add(i.getSupplierID());
        }
    }

//    public void btnSaveItemOnAction(javafx.event.ActionEvent actionEvent) {
//    }
    public void btnSaveItemOnAction(ActionEvent actionEvent) {
        if (btnSaveItem.getText().equalsIgnoreCase("Save Item")) {
            try {
                boolean isSavedItem = itemBO.saveItem(new ItemDTO(
                        txtItemId.getText(),
                        txtItemName.getText(),
                        txtItemCode.getText(),
                        Date.valueOf(dateManufactureDate.getValue()),
                        Date.valueOf(dateExpDate.getValue()),
                        Integer.parseInt(txtItemQty.getText()),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Double.parseDouble(txtUnitDiscount.getText()),
                        cmbSupplierID.getValue().toString(),
                        txtItemDescription.getText()
                ));
                if (isSavedItem) {
                    searchItems(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean isUpdatedItem = itemBO.updateItem(new ItemDTO(
                        txtItemId.getText(),
                        txtItemName.getText(),
                        txtItemCode.getText(),
                        Date.valueOf(dateManufactureDate.getValue()),
                        Date.valueOf(dateExpDate.getValue()),
                        Integer.parseInt(txtItemQty.getText()),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Double.parseDouble(txtUnitDiscount.getText()),
                        cmbSupplierID.getValue().toString(),
                        txtItemDescription.getText()
                ));
                if (isUpdatedItem) {
                    searchItems(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Item Updated !").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void searchItems(String text) {
        String searchText = "%" + text + "%";
        //set value to table
        ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList();
        try {
            ArrayList<ItemDTO> list = itemBO.searchItem(searchText);
            for (ItemDTO itemDTO : list) {
                Button button = new Button("Delete");
                ItemDTO itemDTO1 = new ItemDTO(
                        itemDTO.getItem_id(),
                        itemDTO.getItem_name(),
                        itemDTO.getItem_code(),
                        itemDTO.getManufacture_date(),
                        itemDTO.getExpire_date(),
                        itemDTO.getItem_qty(),
                        itemDTO.getUnit_price(),
                        itemDTO.getUnit_discount(),
                        itemDTO.getSupplier_id(),
                        itemDTO.getItem_description(),
                        button
                );
                itemDTOS.add(itemDTO1);
                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  want to delete this item ?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {

                        try {
                            boolean isDeleteCustomer = itemBO.deleteItem(itemDTO1.getItem_id());
                            if (isDeleteCustomer) {
                                searchItems(searchText);
                                new Alert(Alert.AlertType.INFORMATION, "Item Deleted").show();
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
        tblItem.setItems(itemDTOS);
    }

//    public void btnNewItemOnAction(javafx.event.ActionEvent actionEvent) {
//        clearFields();
//        btnSaveItem.setText("Save Item");
//    }
    public void btnNewItemOnAction(ActionEvent actionEvent) {
        clearFields();
        btnSaveItem.setText("Save Item");
    }

    private void setData(ItemDTO dto) {
        txtItemId.setText(dto.getItem_id());
        txtItemName.setText(dto.getItem_name());
        txtItemCode.setText(dto.getItem_code());
        dateManufactureDate.setPromptText(String.valueOf(dto.getManufacture_date()));
        dateExpDate.setPromptText(String.valueOf(dto.getExpire_date()));
        txtItemQty.setText(String.valueOf(dto.getItem_qty()));
        txtUnitPrice.setText(String.valueOf(dto.getUnit_price()));
        txtUnitDiscount.setText(String.valueOf(dto.getUnit_discount()));
        cmbSupplierID.setValue(dto.getSupplier_id());
        txtItemDescription.setText(dto.getItem_description());
        btnSaveItem.setText("Update Item");
    }

    private void clearFields() {
        txtItemId.clear();
        txtItemName.clear();
        txtItemCode.clear();
        dateManufactureDate.setValue(null);
        dateExpDate.setValue(null);
        txtItemQty.clear();
        txtUnitPrice.clear();
        txtUnitDiscount.clear();
        cmbSupplierID.getSelectionModel().clearSelection();
        txtItemDescription.clear();
    }
    
}


