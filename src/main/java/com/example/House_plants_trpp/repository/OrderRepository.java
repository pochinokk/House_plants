package com.example.House_plants_trpp.repository;

import com.example.House_plants_trpp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Интерфейс представляет собой репозиторий сущности заказа.
 * Отправляет и получает данные из таблицы заказов.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
