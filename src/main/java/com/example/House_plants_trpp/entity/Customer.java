package com.example.House_plants_trpp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;



/**
 * Класс представляет собой сущность пользователя.
 * Содержит поля id, name, password, roles, telephone и address.
 */
@Entity
@Getter
@Setter
@Builder
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true, name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
    @Column(name = "tel")
    private String tel;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
}
