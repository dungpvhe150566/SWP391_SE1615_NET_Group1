
package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Pham Van Trong
 */
@Getter
@Setter
@Builder
@ToString
public class Statistical {
     private int day;
    private String thu;
    private int numOfOrder;
    private double totalMoney;
    private int year;
    private int month;
}
