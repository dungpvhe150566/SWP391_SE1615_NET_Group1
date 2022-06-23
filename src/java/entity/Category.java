package entity;

public class Category {

    private int CategoryID;

    private String CategoryName;

    private String icon;
   

    public Category(int CategoryID, String CategoryName, String icon) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.icon = icon;
    }

    public Category() {
    }

  

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "CategoryID: "+CategoryID+"  CategoryName: "+CategoryName +" Icon: "+icon;
    }

    
    
}
