package com.lake.springboot.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_shoppcar")
public class ShoppingCart {
    private String username;
    private String productId;
    private String name;
    private Double price;
    private Integer number = 1;
}
