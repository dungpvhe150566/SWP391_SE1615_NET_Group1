/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
/**
 *
 * @author Pham Van Trong
 */
public class Ship {
    private int id;
    private String CityName;
    
    private int ShipPrice;

//    public int getId() {
//        return id;
//    }
//
//    public String getCityName() {
//        return CityName;
//    }
//
//    public int getShipPrice() {
//        return ShipPrice;
//    }
    
}
