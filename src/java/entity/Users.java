package entity;

public class Users {

    private int UserID;

    private String Username;

    private String Password;

    private String email;

    private String ActiveCode;

    private int isSeller;

    private int isAdmin;

    private int StatusID;
    
    

    public Users(int aInt, String string, String string0, String string1, String string2, int aInt0, int aInt1, int aInt2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Users() {
    }
    

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActiveCode() {
        return ActiveCode;
    }

    public void setActiveCode(String ActiveCode) {
        this.ActiveCode = ActiveCode;
    }


    public int getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(int isSeller) {
        this.isSeller = isSeller;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    @Override
    public String toString() {
        return "Users{" + "UserID=" + UserID + ", Username=" + Username + ", Password=" + Password + ", email=" + email + ", ActiveCode=" + ActiveCode + ", isSeller=" + isSeller + ", isAdmin=" + isAdmin + ", StatusID=" + StatusID + '}';
    }

}
