package com.stc.petlove.controllers;

import com.stc.petlove.entities.TaiKhoan;
import com.stc.petlove.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Filter user, tìm kiếm theo tên, " +
            "email, sắp xếp trạng thái giảm dần")
    @GetMapping("/filter")
    public ResponseEntity<Page<TaiKhoan>> filter(@RequestParam String search,
                                             @RequestParam int page,
                                             @RequestParam int size,
                                             @RequestParam String sort,
                                             @RequestParam String column) {
        return new ResponseEntity<>(userService.filter(search, page, size, sort, column),
                HttpStatus.OK);
    }

}
