package com.example.House_plants_trpp.controller;


import com.example.House_plants_trpp.config.CustomerDetails;
import com.example.House_plants_trpp.entity.Product;
import com.example.House_plants_trpp.service.OrderService;
import com.example.House_plants_trpp.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * Обрабатывает запросы на предоставление страницы создания заказа.
 */
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@AllArgsConstructor
public class OrderCreationController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    /**
     * Метод получения страницы создания заказа
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @param sort_type тип сортировки
     * @param session объект, необходимы для получения имени текущего пользователя
     * @return имя представления для страницы создания заказа
     */
    @GetMapping("/order_creation")
    public String positions(@RequestParam(value = "sort_type", required = false) String sort_type,
                            Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        session.setAttribute("username", username);
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        Iterable<Product> positions = productService.readAll();
        if (sort_type != null)
        {
            if(sort_type.equals("name"))
            {
                positions = productService.readAllSortedByName();
            }
            else if(sort_type.equals("ascend"))
            {
                positions = productService.readAllSortedByPriceAscending();
            }
            else if(sort_type.equals("descend"))
            {
                positions = productService.readAllSortedByPriceDescending();
            }
        }
        model.addAttribute("positions", positions);
        return "order_creation_page";
    }

    /**
     * Метод сохрнания заказа пользователя
     * @param model объект Model, содержащий атрибуты для рендеринга представления
     * @param str объект, содержащий набор продуктов
     * @param redirectAttributes объект, сохраняющий сообщение для пользователя при переходе
     * @return имя представления для страницы создания заказа
     */
    @PostMapping("/save_order")
    public String createOrder(@RequestParam String str, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerDetails customerDetails = (CustomerDetails) authentication.getPrincipal();
        Long customer_id = customerDetails.getId();
        String amount = orderService.getOrderAmount(str);
        str= str.replace("_", " ");
        System.out.println(str);
        System.out.println(amount);
        if (!amount.equals("-1"))
        {
            orderService.create(amount, str, customer_id);
            System.out.println("OK");
            return "redirect:/order_creation?message";
        }
        else{
            System.out.println("Bad");
            redirectAttributes.addFlashAttribute("er", "Ошибка создания заказа");
            return "redirect:/order_creation?error";
        }
    }

}


