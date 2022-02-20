package com.lake.springboot.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 28794
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "orderdeatils")
public class Order {
    private String orderId;
    private String productId;
    private String productName;
    private String username;
    private Double unitPrice;
    private Integer quantity;
    private Double discount = 1.000;
    private String orderDate;
    private String address;
}
