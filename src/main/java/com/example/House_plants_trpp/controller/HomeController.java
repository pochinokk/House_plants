package com.example.House_plants_trpp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Класс представляет собой контроллер.
 * Обрабатывает запросы на предоставление страницы главной страницы.
 */
@Controller
public class HomeController {
    /**
     * Метод получения главной страницы
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @return имя представления для главной страницы
     */
    @GetMapping("/")
    public String main(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        return "index";
    }
    /**
     * Метод получения главной страницы
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @return имя представления для главной страницы
     */
    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        return "index";
    }
}
