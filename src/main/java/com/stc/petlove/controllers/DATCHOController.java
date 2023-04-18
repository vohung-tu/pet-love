package com.stc.petlove.controllers;

import com.stc.petlove.entities.DatCho;
import com.stc.petlove.services.datcho.DATCHOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datcho")
public class DATCHOController {
    @Autowired
    private DATCHOService datchoService;

    @GetMapping("")
    public List<DatCho> findAll() {
        return datchoService.findAll();
    }

    @GetMapping("/{id}")
    public DatCho findById(@PathVariable String id) {
        return datchoService.findById(id);
    }

    @PostMapping("")
    public DatCho save(@RequestBody DatCho datcho) {
        return datchoService.save(datcho);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        datchoService.deleteById(id);
    }

}
