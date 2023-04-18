package com.stc.petlove.dtos.dichvu;

import com.stc.petlove.entities.DichVu;
import com.stc.petlove.entities.embedded.GiaDichVu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DichVuDTO {
    private String id;
    private String maDichVu;
    private String tenDichVu;
    private String noiDung;
    private boolean trangThai;

    public DichVuDTO(DichVu dichVu) {
        this.id = dichVu.getId();
        this.maDichVu = dichVu.getMaDichVu();
        this.tenDichVu = dichVu.getTenDichVu();
        this.noiDung = dichVu.getNoiDung();
        this.trangThai = dichVu.isTrangThai();
    }

    public DichVu toEntity() {
        DichVu dichVu = new DichVu();
        dichVu.setId(this.id);
        dichVu.setMaDichVu(this.maDichVu);
        dichVu.setTenDichVu(this.tenDichVu);
        dichVu.setNoiDung(this.noiDung);
        dichVu.setTrangThai(this.trangThai);
        return dichVu;
    }
}
