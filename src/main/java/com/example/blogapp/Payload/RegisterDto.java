package com.example.blogapp.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {
    private Long id ;

    private String name ;

    private String email ;

    private String password;
}
