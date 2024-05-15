package com.example.House_plants_trpp.config;


import com.example.House_plants_trpp.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
/**
 * Класс представляет собой реализацию интерфейса,
 * отвечающего за управление правами дооступа пользователей.
 */
@AllArgsConstructor
public class CustomerDetails implements UserDetails {
    /**
     * Поле содержащее пользователя
     */
    private Customer customer;
    /**
     * Метод получения ID пользователя
     * @return ID пользователя
     */
    public Long getId() {
        return customer.getId();
    }
    /**
     * Метод получения прав доступа пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(customer.getRoles().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    /**
     * Метод получения пароля пользователя
     */
    @Override
    public String getPassword() {
        return customer.getPassword();
    }
    /**
     * Метод получения имени пользователя
     */
    @Override
    public String getUsername() {
        return customer.getName();
    }
    /**
     * Метод проверки просроченности аккаунта
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * Метод проверки заблокированности аккаунта
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * Метод проверки просроченности данных
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Метод проверки доступности аккаунта
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}