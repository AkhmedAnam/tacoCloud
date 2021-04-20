package ru.ahmed.anam.taco;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatos", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Letucce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "SALSA", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
        Ingredient.Type[] values = Ingredient.Type.values();
        for (Ingredient.Type value : values) {
            model.addAttribute(
                    value.toString().toLowerCase(),
                    ingredients.stream().filter(ingredient -> ingredient.getType() == value).collect(Collectors.toList())
            );
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String proccessDesign(@Valid Taco design, Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
        System.out.println("Processing design: " + design);
        return "redirect:/orders/current";
    }

}
