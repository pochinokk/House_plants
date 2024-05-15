package com.example.House_plants_trpp.repository;

import com.example.House_plants_trpp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Интерфейс представляет собой репозиторий сущности позиции.
 * Отправляет и получает данные из таблицы позиции.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
