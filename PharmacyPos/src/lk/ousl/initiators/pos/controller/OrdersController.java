package lk.ousl.initiators.pos.controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OrdersController {
    public AnchorPane OrdersContext;
    public TextField txtSearch;
    public TableView tblOrders;
    public TableColumn colInvoiceNumber;
    public TableColumn colCashierName;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colTotal;
    public TableColumn colOption;
}
