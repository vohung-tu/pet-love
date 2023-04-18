package com.stc.petlove.services.datcho;

import com.stc.petlove.entities.DatCho;
import com.stc.petlove.repositories.DATCHORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DATCHOService {
    @Autowired
    private DATCHORepository datchoRepository;

    public List<DatCho> findAll() {
        return datchoRepository.findAll();
    }

    public DatCho findById(String id) {
        return datchoRepository.findById(id).orElse(null);
    }

    public DatCho save(DatCho datcho) {
        return datchoRepository.save(datcho);
    }

    public void deleteById(String id) {
        datchoRepository.deleteById(id);
    }
}
