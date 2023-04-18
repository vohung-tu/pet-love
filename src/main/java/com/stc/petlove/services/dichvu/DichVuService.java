package com.stc.petlove.services.dichvu;

import com.stc.petlove.dtos.dichvu.DichVuDTO;

import java.util.List;

public interface DichVuService {
    List<DichVuDTO> findAll();

    DichVuDTO findByMaDichVu(String maDichVu);

    DichVuDTO create(DichVuDTO dichVuDTO);

    DichVuDTO update(String id, DichVuDTO dichVuDTO);

    void delete(String id);
}
