package lk.ousl.initiators.pos.entity;

import java.sql.Date;
import java.sql.Time;

public class Orders {
    private String invoice_number;
    private String cashier_name;
    private Date date;
    private Time time;
    private double total;

    public Orders() {
    }

    public Orders(String invoice_number, String cashier_name, Date date, Time time, double total) {
        this.invoice_number = invoice_number;
        this.cashier_name = cashier_name;
        this.date = date;
        this.time = time;
        this.total = total;
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

    @Override
    public String toString() {
        return "Orders{" +
                "invoice_number='" + invoice_number + '\'' +
                ", cashier_name='" + cashier_name + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", total=" + total +
                '}';
    }
}
