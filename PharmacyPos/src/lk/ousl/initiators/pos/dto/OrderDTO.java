package lk.ousl.initiators.pos.dto;

import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class OrderDTO {
    private String invoice_number;
    private String cashier_name;
    private Date date;
    private Time time;
    private double total;
    private javafx.scene.control.Button button;
    private List<OrderDetailsDTO> orderDetails;

    public OrderDTO(String invoice_number, String cashier_name, Date date, Time time, double total, Button button, List<OrderDetailsDTO> orderDetails) {
        this.invoice_number = invoice_number;
        this.cashier_name = cashier_name;
        this.date = date;
        this.time = time;
        this.total = total;
        this.button = button;
        this.orderDetails = orderDetails;
    }

    public OrderDTO(String invoice_number, String cashier_name, Date date, Time time, double total, List<OrderDetailsDTO> orderDetails) {
        this.invoice_number = invoice_number;
        this.cashier_name = cashier_name;
        this.date = date;
        this.time = time;
        this.total = total;
        this.orderDetails = orderDetails;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public List<OrderDetailsDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "invoice_number='" + invoice_number + '\'' +
                ", cashier_name='" + cashier_name + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", total=" + total +
                ", button=" + button +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
