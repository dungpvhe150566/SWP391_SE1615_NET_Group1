package entity;

public class Product {

    private int ProductID;

    private String ProductName;

    private String Description;

    private int OriginalPrice;

    private int SellPrice;

    private int SellPercent;

    private String imageLink;

    private int CategoryID;

    private int SellerID;

    private int Amount;

    private int StatusID;

    private int ManufacturerID;

    private float height;

    private float width;

    private float weight;

    public Product(String ProductName, String Description, int OriginalPrice, int SellPrice, int SellPercent, String imageLink, int CategoryID, int SellerID, int Amount, int StatusID, int ManufacturerID, float height, float width, float weight) {
        this.ProductName = ProductName;
        this.Description = Description;
        this.OriginalPrice = OriginalPrice;
        this.SellPrice = SellPrice;
        this.SellPercent = SellPercent;
        this.imageLink = imageLink;
        this.CategoryID = CategoryID;
        this.SellerID = SellerID;
        this.Amount = Amount;
        this.StatusID = StatusID;
        this.ManufacturerID = ManufacturerID;
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    public Product(int ProductID, String ProductName, String Description, int OriginalPrice, int SellPrice, int SellPercent, String imageLink, int CategoryID, int SellerID, int Amount, int StatusID, int ManufacturerID, float height, float width, float weight) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Description = Description;
        this.OriginalPrice = OriginalPrice;
        this.SellPrice = SellPrice;
        this.SellPercent = SellPercent;
        this.imageLink = imageLink;
        this.CategoryID = CategoryID;
        this.SellerID = SellerID;
        this.Amount = Amount;
        this.StatusID = StatusID;
        this.ManufacturerID = ManufacturerID;
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    public Product(int ProductID, String ProductName, String Description, int SellPrice, String imageLink) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Description = Description;
        this.SellPrice = SellPrice;
        this.imageLink = imageLink;

    }

    public Product() {
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(int OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }

    public int getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(int SellPrice) {
        this.SellPrice = SellPrice;
    }

    public int getSellPercent() {
        return SellPercent;
    }

    public void setSellPercent(int SellPercent) {
        this.SellPercent = SellPercent;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getSellerID() {
        return SellerID;
    }

    public void setSellerID(int SellerID) {
        this.SellerID = SellerID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public int getManufacturerID() {
        return ManufacturerID;
    }

    public void setManufacturerID(int ManufacturerID) {
        this.ManufacturerID = ManufacturerID;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductID=" + ProductID + ", ProductName=" + ProductName + ", Description=" + Description + ", OriginalPrice=" + OriginalPrice + ", SellPrice=" + SellPrice + ", SellPercent=" + SellPercent + ", imageLink=" + imageLink + ", CategoryID=" + CategoryID + ", SellerID=" + SellerID + ", Amount=" + Amount + ", StatusID=" + StatusID + ", ManufacturerID=" + ManufacturerID + ", height=" + height + ", width=" + width + ", weight=" + weight + '}';
    }

}
