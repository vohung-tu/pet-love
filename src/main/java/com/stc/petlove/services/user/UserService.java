package com.stc.petlove.services.user;

import com.stc.petlove.dtos.user.UserDto;
import com.stc.petlove.entities.TaiKhoan;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface UserService {

    Page<TaiKhoan> filter(String search,
                      int page, int size, String sort, String column);

    List<TaiKhoan> getAllUsers(String search);

    TaiKhoan getUser(String id);

    TaiKhoan getUserByEmail(String email);

    TaiKhoan getUserBydienThoai(String dienThoai);

    TaiKhoan create(UserDto dto, Principal principal);

    TaiKhoan update(String id, UserDto dto, Principal principal);

    TaiKhoan changeStatus(String id, Principal principal);
}
