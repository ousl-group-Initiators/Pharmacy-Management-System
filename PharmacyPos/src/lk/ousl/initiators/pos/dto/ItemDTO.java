package lk.ousl.initiators.pos.dto;

import javafx.scene.control.Button;

import java.util.Date;

public class ItemDTO {
    private String item_id;
    private String item_name;
    private String item_code;
    private Date manufacture_date;
    private Date expire_date;
    private int item_qty;
    private Double unit_price;
    private Double unit_discount;
    private String supplier_id;
    private String item_description;
    private javafx.scene.control.Button button;

    public ItemDTO() {

    }

    public ItemDTO(String item_id, String item_name, String item_code, Date manufacture_date, Date expire_date, int item_qty, Double unit_price, Double unit_discount, String supplier_id, String item_description) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_code = item_code;
        this.manufacture_date = manufacture_date;
        this.expire_date = expire_date;
        this.item_qty = item_qty;
        this.unit_price = unit_price;
        this.unit_discount = unit_discount;
        this.supplier_id = supplier_id;
        this.item_description = item_description;
    }
    public ItemDTO(String item_id, String item_name, String item_code, Date manufacture_date, Date expire_date, int item_qty, Double unit_price, Double unit_discount, String supplier_id, String item_description, Button button) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_code = item_code;
        this.manufacture_date = manufacture_date;
        this.expire_date = expire_date;
        this.item_qty = item_qty;
        this.unit_price = unit_price;
        this.unit_discount = unit_discount;
        this.supplier_id = supplier_id;
        this.item_description = item_description;
        this.button = button;
    }
    public String getItem_id(){

        return item_id;
    }
    public void setItem_id(String item_id){

        this.item_id = item_id;
    }
    public String getItem_name(){

        return item_name;
    }
    public void setItem_name(String item_name){

        this.item_name = item_name;
    }
    public String getItem_code(){

        return item_code;
    }
    public void setItem_code(String item_code){

        this.item_code = item_code;
    }
    public Date getManufacture_date(){

        return manufacture_date;
    }
    public void setManufacture_date(Date manufacture_date){

        this.manufacture_date = manufacture_date;
    }
    public Date getExpire_date(){

        return expire_date;
    }
    public void setExpire_date(Date expire_date){

        this.expire_date = expire_date;
    }
    public int getItem_qty(){

        return item_qty;
    }
    public void setItem_qty(int item_qty){

        this.item_qty = item_qty;
    }
    public Double getUnit_price(){

        return unit_price;
    }
    public void setUnit_price(Double unit_price){

        this.unit_price = unit_price;
    }
    public Double getUnit_discount(){

        return unit_discount;
    }
    public void setUnit_discount(Double unit_discount){
        this.unit_discount = unit_discount;
    }
    public String getSupplier_id(){

        return supplier_id;
    }
    public void setSupplier_id(String supplier_id){

        this.supplier_id = supplier_id;
    }
    public String getItem_description(){

        return item_description;
    }
    public void setItem_description(String item_description){
        this.item_description = item_description;
    }
    public Button getButton(){

        return button;
    }
    public void setButton(Button button){

        this.button = button;
    }

}
