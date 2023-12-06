package lk.ousl.initiators.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.DrugsBO;
import lk.ousl.initiators.pos.bo.custom.OrderBO;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.OrderDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryController {
    public AnchorPane InventoryContext;
    public TableView<DrugsDTO> tblInventory;
    public TableColumn<DrugsDTO, String> colItemCode;
    public TableColumn<DrugsDTO, String> colName;
    public TableColumn<DrugsDTO, Integer> colStock;
    public TextField txtSearch;
    public TableColumn<DrugsDTO, String> colBatchNumber;
    public TableColumn<DrugsDTO, Double> colUnitPrice;
    public TableColumn<DrugsDTO, Double> colDiscount;
    private String searchText = "";

    private final DrugsBO drugsBO = (DrugsBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Drugs);

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("drug_name"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batch_number"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("unit_discount"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("drug_quantity"));


        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            searchOrders(searchText);
        });
        searchOrders(searchText);

    }

    private void searchOrders(String text){
        String searchText = "%" + text + "%";
        ObservableList<DrugsDTO> drugsDTOS = FXCollections.observableArrayList();
        try {
            ArrayList<DrugsDTO> allDrugs = drugsBO.searchDrugs(searchText);

            for (DrugsDTO drugs : allDrugs) {
                DrugsDTO drugsDTO = new DrugsDTO(
                        drugs.getDrug_id(),
                        drugs.getDrug_name(),
                        drugs.getBatch_number(),
                        drugs.getDrug_quantity(),
                        drugs.getUnit_price(),
                        drugs.getUnit_discount()
                );

                // Add the current orderDTO to the observable list
                drugsDTOS.add(drugsDTO);
            }
        }  catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        // Set the items in tblOrders with the observable list
        tblInventory.setItems(drugsDTOS);
    }

    public void SearchOnAction(ActionEvent actionEvent) {
    }
}
