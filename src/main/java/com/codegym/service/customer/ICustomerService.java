package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.Service;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface ICustomerService extends Service<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
