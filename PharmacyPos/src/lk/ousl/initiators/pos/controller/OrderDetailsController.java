package lk.ousl.initiators.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.CartDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDetailsController {
    public AnchorPane OrderDetailsContext;
    public TableView<CartDTO> tblOrderDetails;
    public TableColumn<CartDTO, String> colItemCode;
    public TableColumn<CartDTO, String> colDescription;
    public TableColumn<CartDTO, Double> colUnitPrice;
    public TableColumn<CartDTO, Integer> colQty;
    public TableColumn<CartDTO, Double> colDiscount;
    public TableColumn<CartDTO, Double> colTotal;

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    public void loadItemDetails(String oderId){

        try{
            String sql = "SELECT o.invoice_number, d.invoice_number, d.drug_id,d.description d.unitprice, d.qty"+
                    " FROM `Order` o INNER JOIN  `Order Details` d ON o.invoice_number=d.invoice_number AND o.invoice_number=?";
            PreparedStatement statement = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            statement.setString(1,oderId);
            ResultSet set = statement.executeQuery();

            ObservableList<CartDTO> obsItemList = FXCollections.observableArrayList();
            while (set.next()){
                double tempUnitPrice = set.getDouble(3);
                int tempQtyOnHand = set.getInt(4);
                double tempDiscount = set.getDouble(5);
                double tempTotal = tempQtyOnHand * tempUnitPrice;

                CartDTO itemDetailsTm = new CartDTO(set.getString(1),set.getString(2),tempUnitPrice,tempQtyOnHand,tempDiscount,tempTotal);
                obsItemList.add(itemDetailsTm);
            }
            tblOrderDetails.setItems(obsItemList);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
