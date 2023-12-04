package lk.ousl.initiators.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ousl.initiators.pos.bo.BoFactory;
import lk.ousl.initiators.pos.bo.custom.OrderBO;
import lk.ousl.initiators.pos.db.DBConnection;
import lk.ousl.initiators.pos.dto.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BillingDashboardController {
    public Label lblDate;
    public Label lblTime;
    public Label lblCashierName;
    public JFXComboBox<String> cmbPaymentOption;
    public Label lblTotalAmount;
    public JFXComboBox<String> cmbItemCode;
    public Label lblBalance;
    public Label lblInvoiceNo;
    public Label lblTotal;
    public Label lblDateTime;
    public JFXTextField txtCash;
    public JFXTextField txtItemCode;
    public JFXTextField lblDescription;
    public JFXTextField lblUnitPrice;
    public JFXTextField lblQtyOnHand;
    public JFXTextField lblDiscount;
    public JFXTextField txtQty;
    public JFXButton btnAddToCart;
    public TableView<CartDTO> tblBillingTable;
    public TableColumn<CartDTO, String> colItemCode;
    public TableColumn<CartDTO, String> colDescription;
    public TableColumn<CartDTO, Double> colUnitPrice;
    public TableColumn<CartDTO, Integer> colQty;
    public TableColumn<CartDTO, Double> colDiscount;
    public TableColumn<CartDTO, Double> colTotal;
    public TableColumn<CartDTO, Button> colOption;
    public AnchorPane billingDashboardContext;
    public JFXButton btnPlaceOrder;
    public JFXButton btnCancelOrder;
    public String orderId;

    private final OrderBO orderBO = (OrderBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ORDER);

    public void initialize() {
        setDateAndTime();
        setDate();
        setTime();
        clearFields();
        loadAllPaymentMethods();
        loadAllDrugsIds();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<CartDTO, Button> lastCol = (TableColumn<CartDTO, Button>)tblBillingTable.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                tblBillingTable.getItems().remove(param.getValue());
                tblBillingTable.getSelectionModel().clearSelection();
                calculateTotal();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        orderId = generateNewOrderId();
        lblInvoiceNo.setText(" " + orderId);
        lblDate.setText(LocalDate.now().toString());
        lblTime.setText(LocalTime.now().toString());
        lblDescription.setFocusTraversable(false);
        lblDescription.setEditable(false);
        lblUnitPrice.setFocusTraversable(false);
        lblUnitPrice.setEditable(false);
        lblQtyOnHand.setFocusTraversable(false);
        lblQtyOnHand.setEditable(false);
        lblDiscount.setFocusTraversable(false);
        lblDiscount.setEditable(false);
        txtQty.setOnAction(event -> btnAddToCart.fire());

        tblBillingTable.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue, newItemCode) ->{
            txtQty.setEditable(newItemCode != null);
            btnAddToCart.setDisable(newItemCode == null);

            if (null != newItemCode){
                try {
                    if (!existItem(newItemCode + "")){
                        new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + newItemCode + "").show();
                    }
                    DrugsDTO drugs = orderBO.searchDrugs(newItemCode + "");
                    lblDescription.setText(drugs.getDrug_name());
                    lblUnitPrice.setText(String.valueOf(drugs.getUnit_price()));
                    Optional<CartDTO> optOrderDetail = tblBillingTable.getItems().stream().filter(detail -> detail.getCode().equals(newItemCode)).findFirst();
                    lblQtyOnHand.setText((optOrderDetail.isPresent() ? drugs.getDrug_quantity() - optOrderDetail.get().getQty() : drugs.getDrug_quantity()) + "");
                    lblDiscount.setText(String.valueOf(drugs.getUnit_discount()));
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }else{
                lblDescription.clear();
                lblUnitPrice.clear();
                lblQtyOnHand.clear();
                lblDiscount.clear();
                txtQty.clear();
            }
        });

        tblBillingTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {
            if (selectedOrderDetail != null) {
                cmbItemCode.setDisable(true);
                cmbItemCode.setValue(selectedOrderDetail.getCode());
                btnAddToCart.setText("Update to Cart");
                txtQty.setText(selectedOrderDetail.getQty() + "");
            } else {
                btnAddToCart.setText("Add to Cart");
                cmbItemCode.setDisable(false);
                cmbItemCode.getSelectionModel().clearSelection();
                txtQty.clear();
            }
        });
    }

    // Load All DrugsIds
    private void loadAllDrugsIds(){
        try {
            ArrayList<DrugsDTO> all = orderBO.getAllItems();
            for (DrugsDTO d : all){
                cmbItemCode.getItems().add(d.getDrug_id());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return orderBO.ifDrugsExist(code);
    }

    // Load Payment Methods
    private void loadAllPaymentMethods() {
        for (PaymentMethodDTO i : DBConnection.paymentMethodDTOS) {
            cmbPaymentOption.getItems().add(i.getPaymentName());
        }
    }

    // create the local date and time
    private void setDateAndTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   |   HH:mm:ss");
                    lblDateTime.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // create the payment details column local date
    private void setDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    // create the payment details column local Time
    private void setTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime localTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    lblTime.setText(localTime.format(formatter));
                }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 ||
                Integer.parseInt(txtQty.getText()) > Integer.parseInt(lblQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;
        }

        String itemCode = cmbItemCode.getValue().toString();
        String description = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double discount = Double.parseDouble(lblDiscount.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = unitPrice*(qty)-qty*(unitPrice*(discount)/100);

        boolean exists = tblBillingTable.getItems().stream().anyMatch(detail -> detail.getCode().equals(itemCode));

        if (exists) {
            CartDTO cartDTO = tblBillingTable.getItems().stream().filter(detail -> detail.getCode().equals(itemCode)).findFirst().get();

            if (btnAddToCart.getText().equalsIgnoreCase("Update to Cart")) {
                cartDTO.setQty(qty);
                cartDTO.setTotal(total);
                cartDTO.setDiscount(discount);
                tblBillingTable.getSelectionModel().clearSelection();
            } else {
                cartDTO.setQty(cartDTO.getQty() + qty);
                total = cartDTO.getQty()*unitPrice;
                cartDTO.setTotal(total);
            }
            tblBillingTable.refresh();
        } else {
            tblBillingTable.getItems().add(new CartDTO(itemCode, description,unitPrice, qty, discount, total));
        }
        cmbItemCode.getSelectionModel().clearSelection();
        cmbItemCode.requestFocus();
        calculateTotal();
    }

    private void calculateTotal() {
        BigDecimal total = new BigDecimal(0);
        for (CartDTO o : tblBillingTable.getItems()){
            total = total.add(BigDecimal.valueOf(o.getTotal()));
        }
        lblTotal.setText(" " + total);
        lblTotalAmount.setText(" " + total);
    }

    private void calculateBalance(){
        try {
            // Get total amount from label
            BigDecimal totalAmount = new BigDecimal(lblTotalAmount.getText());

            // Get cash from text field
            BigDecimal cash = new BigDecimal(txtCash.getText().trim());

            // Calculate balance
            BigDecimal balance = cash.subtract(totalAmount);

            // Set the balance in the label
            lblBalance.setText(" " + balance.toString());

        } catch (NumberFormatException e) {
            // Handle invalid input
            new Alert(Alert.AlertType.ERROR, "Invalid Cash or Total Amount").show();
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
//        calculateBalance();
        boolean b = saveOrder(orderId,
                lblCashierName.getText(),
                java.sql.Date.valueOf(lblDate.getText()),
                Time.valueOf(lblTime.getText()),
                Double.parseDouble(lblTotalAmount.getText()),
                tblBillingTable.getItems().stream().map(cartDTO -> new OrderDetailsDTO(orderId,
                        cartDTO.getCode(),
                        cartDTO.getDescription(),
                        cartDTO.getUnitPrice(),
                        cartDTO.getQty(),
                        cartDTO.getDiscount(),
                        cartDTO.getTotal())).collect(Collectors.toList()));
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Successfully Done").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }

    public boolean saveOrder(String invoice_number, String cashier_name, Date date, Time time, double total, List<OrderDetailsDTO> orderDetails) {
        try {
            OrderDTO orderDTO = new OrderDTO(invoice_number, cashier_name, (java.sql.Date) date,  time, total, orderDetails);
            System.out.println("Print - " + orderDTO);
            return orderBO.purchaseOrder(orderDTO);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        orderId = generateNewOrderId();
        lblInvoiceNo.setText(" " + orderId);
        cmbItemCode.getSelectionModel().clearSelection();
        lblDescription.clear();
        lblUnitPrice.clear();
        lblQtyOnHand.clear();
        lblDiscount.clear();
        txtQty.clear();
        lblTotal.setText("00.00");
        lblTotalAmount.setText("00.00");
        txtCash.clear();
        lblBalance.setText("00.00");
        cmbItemCode.setDisable(false);
        tblBillingTable.getItems().clear();
    }

    // set the view Inventory ui
    public void btnViewInventoryOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Inventory.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // set the view order details ui
    public void btnViewOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Orders.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // set the logout button function
    public void btnLogOutOnAction(ActionEvent actionEvent) {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  want to Logout this System ?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                Stage stage = (Stage) billingDashboardContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml")))));
                stage.centerOnScreen();
                stage.setResizable(false);

                FadeTransition fadeTransition = new FadeTransition(Duration.millis(2500));
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(1.0);
                fadeTransition.play();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateNewOrderId(){
        try {
            return orderBO.generateNewOrderId();
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Failed to generate a new order id").show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    private void setData(CartDTO dto) {
        cmbItemCode.setValue(dto.getCode());
        lblDescription.setText(dto.getDescription());
        lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        lblQtyOnHand.setPromptText(String.valueOf(dto.getQty()));
        lblDiscount.setText(String.valueOf(dto.getDiscount()));
        btnAddToCart.setText("Update Cart");
    }

    private void clearFields(){
        cmbItemCode.getSelectionModel().clearSelection();
        lblDescription.clear();
        lblUnitPrice.clear();
        lblQtyOnHand.clear();
        lblDiscount.clear();
        txtQty.clear();
    }

}
