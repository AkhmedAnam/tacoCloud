package ru.ahmed.anam.taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.ahmed.anam.taco.data.OrderRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderConfig orderConfig;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderConfig orderConfig) {
        this.orderRepository = orderRepository;
        this.orderConfig = orderConfig;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        System.out.println("lastOrderSize = " + orderConfig.getLastOrderSize());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if(errors.hasErrors()){
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();
        
        return "redirect:/";
    }
}
