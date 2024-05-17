package com.example.House_plants_trpp.controller;



import com.example.House_plants_trpp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Класс представляет собой контроллер.
 * Обрабатывает запросы на предоставление страницы регистрации.
 */
@Controller
public class RegistrationController {
    @Autowired
    private CustomerService customerService;
    /**
     * Метод регистрирующий нового пользователя
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @param username имя пользователя
     * @param password пароль пользователя
     * @param tel телефон пользователя
     * @param address адрес пользователя
     * @param redirectAttributes объект, сохраняющий сообщение для пользователя при переходе
     * @return имя представления для страницы регистрации
     */
    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("tel") String tel,
                           @RequestParam("addr") String address,
                           Model model, RedirectAttributes redirectAttributes) {
        if (customerService.exists(username) || username.equals("anonymousUser")) {
            redirectAttributes.addFlashAttribute("er", "Извините, имя занято");
            return "redirect:/registration";
        }
        customerService.create(username, password, "USER", tel, address);
        System.out.println(username);
        System.out.println(password);
        System.out.println("USER");
        System.out.println(tel);
        System.out.println(address);
        redirectAttributes.addFlashAttribute("mes", "Вы успешно зарегистрировались");
        return "redirect:/authentication";
    }


    /**
     * Метод получения страницы регистрации
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @return имя представления для страницы регистрации
     */
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        return "registration_page";
    }
}



