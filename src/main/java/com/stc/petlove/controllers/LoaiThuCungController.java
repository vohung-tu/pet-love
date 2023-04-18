package com.stc.petlove.controllers;

import com.stc.petlove.entities.LoaiThuCung;
import com.stc.petlove.services.loaithucung.LoaiThuCungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loai-thu-cung")
public class LoaiThuCungController {
    @Autowired
    private LoaiThuCungService service;

    @GetMapping("")
    public ResponseEntity<List<LoaiThuCung>> getAll() {
        List<LoaiThuCung> loaiThuCungs = service.getAll();
        return new ResponseEntity<>(loaiThuCungs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiThuCung> getById(@PathVariable String id) {
        LoaiThuCung loaiThuCung = service.getById(id);
        if (loaiThuCung == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loaiThuCung, HttpStatus.OK);
    }

    @GetMapping("/ma-loai-thu-cung/{maLoaiThuCung}")
    public ResponseEntity<LoaiThuCung> getByMaLoaiThuCung(@PathVariable String maLoaiThuCung) {
        LoaiThuCung loaiThuCung = service.getByMaLoaiThuCung(maLoaiThuCung);
        if (loaiThuCung == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loaiThuCung, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> add(@RequestBody LoaiThuCung loaiThuCung) {
        LoaiThuCung existingLoaiThuCung = service.getByMaLoaiThuCung(loaiThuCung.getMaLoaiThuCung());
        if (existingLoaiThuCung != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        service.add(loaiThuCung);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody LoaiThuCung loaiThuCung) {
        LoaiThuCung existingLoaiThuCung = service.getById(id);
        if (existingLoaiThuCung == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingLoaiThuCung.setMaLoaiThuCung(loaiThuCung.getMaLoaiThuCung());
        existingLoaiThuCung.setTenLoaiThuCung(loaiThuCung.getTenLoaiThuCung());
        existingLoaiThuCung.setTrangThai(loaiThuCung.isTrangThai());
        service.update(existingLoaiThuCung);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        LoaiThuCung existingLoaiThuCung = service.getById(id);
        if (existingLoaiThuCung == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}