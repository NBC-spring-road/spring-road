package com.example.springroadproject.entity;

import com.example.springroadproject.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Table
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;


    public User(UserRequestDto userRequestDto,String encodedPassword) {
        this.username = userRequestDto.getUsername();
        this.password = encodedPassword;
        this.introduction = userRequestDto.getIntroduction();
        this.address = userRequestDto.getAddress();
        this.phone = userRequestDto.getPhone();
    }

    public User update(UserRequestDto userRequestDto, String encodedPassword) {
        this.username = userRequestDto.getUsername()== null? this.username : userRequestDto.getUsername();
        this.password = Objects.equals(encodedPassword, "") ? this.password : encodedPassword;
        this.introduction = userRequestDto.getIntroduction()== null? this.introduction : userRequestDto.getIntroduction();
        this.address = userRequestDto.getAddress()== null? this.address : userRequestDto.getAddress();
        this.phone = userRequestDto.getPhone()== null? this.phone : userRequestDto.getPhone();
        return this;
    }
}
