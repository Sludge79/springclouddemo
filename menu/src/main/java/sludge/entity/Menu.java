package sludge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Giggles
 * @Date 2019-09-26 10:23 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private long id;
    private String name;
    private BigDecimal price;
    private String flavor;
    private int tid;
}
