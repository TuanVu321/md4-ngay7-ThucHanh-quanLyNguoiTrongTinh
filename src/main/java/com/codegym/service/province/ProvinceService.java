package com.codegym.service.province;

import com.codegym.model.Province;
import com.codegym.repository.province.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProvinceService implements IProvinceService{
    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public Page<Province> findAll(Pageable pageable) {
        return provinceRepository.findAll(pageable);
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void save(Province model) {
        provinceRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.delete(id);
    }
}
