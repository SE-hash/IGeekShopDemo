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
@TableName(value = "t_product")
public class Product {
    private String id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private String category;
    private String isNew;
    private String picture;
}
