package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.customer.ICustomerService;

import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;
    @ModelAttribute("provinces")
    public  Page <Province> provinces(Pageable pageable){
        return provinceService.findAll(pageable);
    }
    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        customerService.save(customer);
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("message","them thanh cong");
        return modelAndView;
    }
    @GetMapping("/")
    public ModelAndView listCustomers(Pageable pageable){
        Page <Customer> customers = customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if(customer!=null){
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("customer/error");
            return modelAndView;
        }
    }
    @PostMapping("/edit-customer")
    public  ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","sua thanh cong");
        return modelAndView;

    }
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if(customer!=null){
            ModelAndView modelAndView = new ModelAndView("customer/delete");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("customer/error");
            return modelAndView;
        }

    }
    @PostMapping("/delete-customer")
    public  String delete(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:/";
    }
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable Long id){
        Province province = provinceService.findById(id);
        if(province==null){
            return new ModelAndView("customer/error");
        }
        ModelAndView modelAndView = new ModelAndView("customer/view");
        Iterable <Customer> customers = customerService.findAllByProvince(province);
        modelAndView.addObject("province",province);
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

}
