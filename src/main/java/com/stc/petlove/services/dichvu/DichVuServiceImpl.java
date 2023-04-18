package com.stc.petlove.services.dichvu;

import com.stc.petlove.dtos.dichvu.DichVuDTO;
import com.stc.petlove.entities.DichVu;
import com.stc.petlove.repositories.DichVuRepository;
import com.stc.petlove.services.dichvu.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DichVuServiceImpl implements DichVuService {
    @Autowired
    private DichVuRepository dichVuRepository;

    @Override
    public List<DichVuDTO> findAll() {
        List<DichVu> dichVus = dichVuRepository.findAll();
        return dichVus.stream().map(DichVuDTO::new).collect(Collectors.toList());
    }

    @Override
    public DichVuDTO findByMaDichVu(String maDichVu) {
        DichVu dichVu = dichVuRepository.findByMaDichVu(maDichVu);
        return dichVu == null ? null : new DichVuDTO(dichVu);
    }

    @Override
    public DichVuDTO create(DichVuDTO dichVuDTO) {
        DichVu dichVu = new DichVu();
        dichVu.setMaDichVu(dichVuDTO.getMaDichVu());
        dichVu.setTenDichVu(dichVuDTO.getTenDichVu());
        dichVu.setNoiDung(dichVuDTO.getNoiDung());
        dichVu.setTrangThai(dichVuDTO.isTrangThai());
        DichVu savedDichVu = dichVuRepository.save(dichVu);
        return new DichVuDTO(savedDichVu);
    }
    @Override
    public DichVuDTO update(String id, DichVuDTO dichVuDTO) {
        DichVu dichVu = dichVuRepository.findById(id).orElse(null);
        if (dichVu == null) {
            return null;
        }
        dichVu.setMaDichVu(dichVuDTO.getMaDichVu());
        dichVu.setTenDichVu(dichVuDTO.getTenDichVu());
        dichVu.setNoiDung(dichVuDTO.getNoiDung());
        dichVu.setTrangThai(dichVuDTO.isTrangThai());
        DichVu savedDichVu = dichVuRepository.save(dichVu);
        return new DichVuDTO(savedDichVu);
    }

    @Override
    public void delete(String id) {
        dichVuRepository.deleteById(id);
    }
}