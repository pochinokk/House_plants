package com.example.House_plants_trpp.service;


import com.example.House_plants_trpp.config.CustomerDetails;
import com.example.House_plants_trpp.entity.Customer;
import com.example.House_plants_trpp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Класс представляет собой реализацию сервиса
 * для работы с правами доступа пользователей.
 */
@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = repository.findByName(username);
        return customer.map(CustomerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "не найден"));
    }

}