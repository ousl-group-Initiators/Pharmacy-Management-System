package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.DrugsBO;
import lk.ousl.initiators.pos.dto.DrugsDTO;

public class ManageDrugsController {
    public AnchorPane manageDrugsContext;
    public JFXTextField txtDrugsId;
    public DatePicker dateManufactureDate;
    public DatePicker dateExpireDate;
    public JFXComboBox txtSpplierId;
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
    private String searchText = "";

    private final DrugsBO drugsBO = (DrugsBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Drugs);

    public void initialize(){
        colDrugsId.setCellValueFactory(new PropertyValueFactory<>("drugs_id"));
        colDrugsId.setCellValueFactory(new PropertyValueFactory<>("drugs_id"));

    }




    public void btnSaveDrugsOnAction(ActionEvent actionEvent) {
    }

    public void btnNewDrugsOnAction(ActionEvent actionEvent) {
    }
}
