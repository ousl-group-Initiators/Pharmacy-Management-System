package lk.ousl.initiators.pos.entity;

import java.util.Date;

public class Drugs {
    private String drug_id;
    private String drug_name;
    private String batch_number;
    private int drug_quantity;
    private double unit_price;
    private double unit_discount;
    private Date MFD;
    private Date EXD;
    private String Supply_id;
    private String description;

    public Drugs(){}

    public Drugs(String drug_id, String drug_name, String batch_number, Date MFD, Date EXD,int drug_quantity, double unit_price, double unit_discount,  String supply_id, String description) {
        this.drug_id = drug_id;
        this.drug_name = drug_name;
        this.batch_number = batch_number;
        this.drug_quantity = drug_quantity;
        this.unit_price = unit_price;
        this.unit_discount = unit_discount;
        this.MFD = MFD;
        this.EXD = EXD;
        this.Supply_id = supply_id;
        this.description = description;
    }


    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public int getDrug_quantity() {
        return drug_quantity;
    }

    public void setDrug_quantity(int drug_quantity) {
        this.drug_quantity = drug_quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getUnit_discount() {
        return unit_discount;
    }

    public void setUnit_discount(double unit_discount) {
        this.unit_discount = unit_discount;
    }

    public Date getMFD() {
        return MFD;
    }

    public void setMFD(Date MFD) {
        this.MFD = MFD;
    }

    public Date getEXD() {
        return EXD;
    }

    public void setEXD(Date EXD) {
        this.EXD = EXD;
    }

    public String getSupply_id() {
        return Supply_id;
    }

    public void setSupply_id(String supply_id) {
        Supply_id = supply_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Drugs{" +
                "drug_id='" + drug_id + '\'' +
                ",drug_name='" + drug_name + '\'' +
                ",batch_number='" + batch_number + '\'' +
                ",drug_quantity=" + drug_quantity +
                ", unit_price=" + unit_price +
                ", unit_discount=" + unit_discount +
                ", MFD=" + MFD +
                ", EXD =" + EXD  +
                ", Supply_id='" + Supply_id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

