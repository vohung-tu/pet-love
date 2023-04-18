package com.stc.petlove.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class TaiKhoan extends  BaseEntity{
    @Id
    private String id;

    private String name;

    // email không được trùng nhau
    private String email;

    @JsonIgnore
    private String password;

    private String dienThoai;

    private List<String> roles = new ArrayList<>();

    private boolean trangThai = true;

    public TaiKhoan(String name, String email, String password, String dienThoai, List<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dienThoai = dienThoai;
        this.roles = roles;
    }

    public <T> TaiKhoan(String datnv, String s, String s1, List<T> asList) {
    }
}
