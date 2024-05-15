package com.example.House_plants_trpp.config;

import com.example.House_plants_trpp.service.CustomerService;
import com.example.House_plants_trpp.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Класс представляет является компонентом,
 * инициализурующим 1 администратора, 1 тестового пользователя и
 * заполняет таблицу базы данных позициями.
 */
@Component
public class InitConfig {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;
    /**
     * Метод заполнения начальными данными базы данных
     */
    @PostConstruct
    public void initData() {
        makeAdmin();
        makeTestUser();
        makePositions();
    }
    /**
     * Метод создания администратора
     */
    public void makeAdmin(){
        customerService.create("admin", "1234", "ADMIN", "+75555555555",
                "адрес админа");
    }
    /**
     * Метод создания тестового пользователя
     */
    public void makeTestUser(){
        customerService.create("test", "1234", "USER", "+76666666666",
                "адрес тестововго пользователя");
    }
    /**
     * Метод создания позиций растений в базе данных
     */
    public void makePositions(){
        productService.create("Фикус Бенгальский", "1200");//1
        productService.create("Фикус Бенджамина", "1500"); //2
        productService.create("Фикус Старлайт", "1900");   //3
        productService.create("Фикус Кинки", "1500");      //4
        productService.create("Фикус Вианди", "1400");     //5
        productService.create("Антуриум", "1000");         //6
        productService.create("Бегония", "800");           //7
        productService.create("Герань", "400");            //8
        productService.create("Фиалка", "400");            //9
        productService.create("Цикламен", "800");          //10
        productService.create("Алое", "600");              //11
        productService.create("Крассула", "800");          //12
        productService.create("Эхеверия", "500");          //13
        productService.create("Рипсалис", "500");          //14
        productService.create("Замиокулькас", "500");      //15
    }
}
