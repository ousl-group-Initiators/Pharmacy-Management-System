package lk.ousl.initiators.pos.dto;

public class SupplierDTO {
    private int id;
    private String supplierID;

    public SupplierDTO() {
    }

    public SupplierDTO(int id, String supplierID) {
        this.id = id;
        this.supplierID = supplierID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
}
