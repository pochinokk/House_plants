package com.example.House_plants_trpp.repository;

import com.example.House_plants_trpp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Интерфейс представляет собой репозиторий сущности пользователя.
 * Отправляет и получает данные из таблицы пользователей.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Метод поиска пользователя по имени
     * @param username имя пользователя
     * @return пользователь
     */
    Optional<Customer> findByName(String username);
}
