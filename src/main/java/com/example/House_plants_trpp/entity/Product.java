package com.example.House_plants_trpp.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс представляет собой сущность позиции.
 * Содержит поля id, name и price.
 */
@Entity
@Getter
@Setter
@Builder
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;
}
