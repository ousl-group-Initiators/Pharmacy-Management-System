package lk.ousl.initiators.pos.dto;

public class SupplyIdDTO {
    private int id;
    private String supplyId;

    public SupplyIdDTO() {
    }
    public SupplyIdDTO(int id, String supplyId) {
        this.id = id;
        this.supplyId = supplyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }




}

