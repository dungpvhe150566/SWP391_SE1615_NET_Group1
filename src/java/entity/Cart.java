package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Cart {

    private int UserID;

    private int ProductID;

    private int Amount;
    
    private Product product;
    private Ship ship;
    
}
