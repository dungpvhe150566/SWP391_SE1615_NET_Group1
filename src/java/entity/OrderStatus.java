package entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class OrderStatus {

    private int ID;

    private String Name;

    public OrderStatus(int ID, String Name) {
        this.ID = ID;
        this.Name = Name;
    }

//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(char Name) {
//        this.Name = Name;
//    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
