package com.example.House_plants_trpp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Класс представляет собой контроллер.
 * Обрабатывает запрос на предоставление страницы с контактной информацией.
 */
@Controller
public class AboutUsController {
    /**
     * Метод получения страницы контактной информации
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @return имя представления для страницы "О нас"
     */
    @GetMapping("/about_us")
    public String about_us(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        return "about_us_page";
    }
}
