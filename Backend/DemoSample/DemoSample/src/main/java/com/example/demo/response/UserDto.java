package com.example.demo.response;

import com.example.demo.enums.UserRole;
import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String name;
    private String email;
    private long phone;
    private UserRole userRole;

    public static UserDto convertDto(User user){
        return UserDto.builder()
                .id(user.getUserId())
                .name(user.getName())
                .userRole(user.getUserRole())
                .email(user.getEmail())
                .phone(user.getPhoneNumber()!= null ? user.getPhoneNumber() : 0l)
                .build();
    }

}
