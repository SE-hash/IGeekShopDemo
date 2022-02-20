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
@TableName(value = "t_admin")
public class Admin {
    private String ID;
    private String password;
}
