package lk.ousl.initiators.pos.entity;

public class OrderDetails {
    private String drug_id;
    private String invoice_number;
    private String description;
    private double unitPrice;
    private int qty;
    private double discount;
    private double total;

    public OrderDetails() {
    }

    public OrderDetails(String drug_id, String invoice_number, String description, double unitPrice, int qty, double discount, double total) {
        this.drug_id = drug_id;
        this.invoice_number = invoice_number;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
        this.total = total;
    }

    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "drug_id='" + drug_id + '\'' +
                ", invoice_number='" + invoice_number + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}
