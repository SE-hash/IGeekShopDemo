package com.lake.springboot.Bean;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.context.annotation.Primary;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class User {
    private String username;
    private String password;
    private String birthday;
    private String telephone;
    private String gender;
}
