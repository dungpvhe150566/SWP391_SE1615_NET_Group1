package entity;

public class ProductStatus {

    private int StatusID;

    private String StatusName;

    public ProductStatus(int StatusID, String StatusName) {
        this.StatusID = StatusID;
        this.StatusName = StatusName;
    }
    

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }

}
