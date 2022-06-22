package entity;

public class Manufacturer {

    private int ManufacturerID;

    private String ManufacturerName;
    
    private int count;

    public Manufacturer() {
    }

    public Manufacturer(int ManufacturerID, String ManufacturerName) {
        this.ManufacturerID = ManufacturerID;
        this.ManufacturerName = ManufacturerName;
    }

    public Manufacturer(int ManufacturerID, String ManufacturerName, int count) {
        this.ManufacturerID = ManufacturerID;
        this.ManufacturerName = ManufacturerName;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    

    public int getManufacturerID() {
        return ManufacturerID;
    }

    public void setManufacturerID(int ManufacturerID) {
        this.ManufacturerID = ManufacturerID;
    }

    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String ManufacturerName) {
        this.ManufacturerName = ManufacturerName;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
/// Update comment
/// Update comment
/// Update comment
    
    
}
