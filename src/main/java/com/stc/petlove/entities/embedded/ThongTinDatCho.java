package com.stc.petlove.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThongTinDatCho {
    // dịch vụ id
    private String dichVu;

    // lấy giá từ bảng dịch vụ tại thời điểm hiện tại bỏ vào đây
    private String giaDichVu;
}
