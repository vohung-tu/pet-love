package com.stc.petlove.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaDichVu {
    // Id loại thú cưng
    private String loaiThuCung;

    // Cân nặng thú cưng (kg)
    private double canNang;

    private double gia;
}
