package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.province.IProvinceService;
import com.codegym.service.province.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("province/list");
        Page <Province> provinces = provinceService.findAll(pageable);
        modelAndView.addObject("provinces",provinces);
        return modelAndView;
    }
    @GetMapping("/create-province")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province",new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@ModelAttribute("province") Province province){
        ModelAndView modelAndView = new ModelAndView("province/create");
        provinceService.save(province);
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message","tao thanh cong");
        return modelAndView;

    }
    @GetMapping("/edit-province/{id}")
    public  ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("province/edit");
        Province province = provinceService.findById(id);
        modelAndView.addObject("province",province);
        return modelAndView;
    }
    @PostMapping("/edit-province")
    public  ModelAndView updateProvince(@ModelAttribute("province") Province province){
        ModelAndView modelAndView = new ModelAndView("province/edit");
        provinceService.save(province);
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "Province updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-province/{id}")
    public  ModelAndView showDeleteForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("province/delete");
        Province province = provinceService.findById(id);
        modelAndView.addObject("province",province);
        return modelAndView;
    }
    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("province") Province province){
        provinceService.remove(province.getId());
        return "redirect:provinces";
    }



}
