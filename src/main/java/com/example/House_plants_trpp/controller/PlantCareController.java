package com.example.House_plants_trpp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Класс представляет собой контроллер.
 * Обрабатывает запросы на предоставление страницы
 * с советами об уходе за растениями.
 */
@Controller
public class PlantCareController {
    /**
     * Метод получения страницы с советами по уходу за растениями
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @return имя представления для страницы с советами по уходу за растениями
     */
    @GetMapping("/plant_care")
    public String plant_care(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        return "plant_care_page";
    }
}

