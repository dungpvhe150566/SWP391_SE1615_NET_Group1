/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class Blog {
    int ID;
    String Title;
    String Content;
    String imageLink;
    int SellerID;
    String MOdife;

  

    public Blog() {
    }

    public Blog(String Title, String Content, String imageLink, int SellerID) {
        this.Title = Title;
        this.Content = Content;
        this.imageLink = imageLink;
        this.SellerID = SellerID;
    }

    public Blog(int ID, String Title, String Content, String imageLink, int SellerID, String MOdife) {
        this.ID = ID;
        this.Title = Title;
        this.Content = Content;
        this.imageLink = imageLink;
        this.SellerID = SellerID;
        this.MOdife = MOdife;
    }

    public Blog(int ID, String Title, String Content, String imageLink, int SellerID) {
        this.ID = ID;
        this.Title = Title;
        this.Content = Content;
        this.imageLink = imageLink;
        this.SellerID = SellerID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public String toString() {
        return "Blog{" + "ID=" + ID + ", Title=" + Title + ", Content=" + Content + ", imageLink=" + imageLink + ", SellerID=" + SellerID + '}';
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getSellerID() {
        return SellerID;
    }

    public void setSellerID(int SellerID) {
        this.SellerID = SellerID;
    }
      public String getMOdife() {
        return MOdife;
    }

    public void setMOdife(String MOdife) {
        this.MOdife = MOdife;
    }
    
    
}
