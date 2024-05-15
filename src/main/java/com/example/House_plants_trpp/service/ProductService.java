package com.example.House_plants_trpp.service;

import com.example.House_plants_trpp.entity.Product;
import com.example.House_plants_trpp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Класс представляет собой сервис для работы
 * с репозитоием сущности позиции.
 */
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    /**
     * Метод создания позиции в таблице
     * @param name название позиции
     * @param price цена
     * @return позиция
     */
    public Product create(String name, String price) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .build();
        return productRepository.save(product);
    }
    /**
     * Метод получения всех позиций из таблицы, отсортированных по имени по алфавиту
     * @return список отсортированных позиций
     */
    public Iterable<Product> readAllSortedByName() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    /**
     * Метод получения всех позиций из таблицы, отсортированных по возрастанию цены
     * @return список отсортированных позиций
     */
    public Iterable<Product> readAllSortedByPriceAscending() {
        List<Product> positions = productRepository.findAll();
        positions.sort(Comparator.comparingDouble(product -> Double.parseDouble(product.getPrice())));
        return positions;
    }
    /**
     * Метод получения всех позиций из таблицы, отсортированных по убыванию цены
     * @return список отсортированных позиций
     */
    public Iterable<Product> readAllSortedByPriceDescending() {
        List<Product> positions = productRepository.findAll();
        Comparator<Product> priceComparator = Comparator.comparingDouble(product -> Double.
                parseDouble(product.getPrice()));
        Collections.sort(positions, priceComparator.reversed());
        return positions;
    }
    /**
     * Метод получения всех позиций из таблицы
     * @return список позиций
     */
    public List<Product> readAll() {
        return productRepository.findAll();
    }
    /**
     * Метод удаления позиции из таблицы
     * @param id ID позиции
     */
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
