package com.example.House_plants_trpp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Класс представляет собой контроллер.
 * Обрабатывает запросы на предоставление страницы каталога.
 */
@Controller
public class CatalogController {
    /**
     * Метод получения страницы каталога
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @return имя представления для страницы каталога
     */
    @GetMapping("/catalog")
    public String catalog(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        return "catalog_page";
    }
}