package com.stc.petlove.services.loaithucung;

import com.stc.petlove.entities.LoaiThuCung;
import com.stc.petlove.repositories.LoaiThuCungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiThuCungService {
    @Autowired
    private LoaiThuCungRepository repository;

    public List<LoaiThuCung> getAll() {
        return repository.findAll();
    }

    public LoaiThuCung getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public LoaiThuCung getByMaLoaiThuCung(String maLoaiThuCung) {
        return repository.findByMaLoaiThuCung(maLoaiThuCung);
    }

    public void add(LoaiThuCung loaiThuCung) {
        repository.save(loaiThuCung);
    }

    public void update(LoaiThuCung loaiThuCung) {
        repository.save(loaiThuCung);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
