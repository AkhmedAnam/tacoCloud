package ru.ahmed.anam.taco;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")//обработка запроса для корневого пути /
    public String home(){
        return "home";//возвращает имя представления
    }
}
