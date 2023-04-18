package com.stc.petlove.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loai-thu-cung")
public class LoaiThuCung {
    @Id
    private String id;

    // mã không được trùng
    private String maLoaiThuCung;

    private String tenLoaiThuCung;

    private boolean trangThai = true;
}
