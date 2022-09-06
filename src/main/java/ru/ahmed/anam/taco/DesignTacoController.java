package ru.ahmed.anam.taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.ahmed.anam.taco.data.IngredientRepository;
import ru.ahmed.anam.taco.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Ingredient.Type[] values = Ingredient.Type.values();
        for (Ingredient.Type value : values) {
            model.addAttribute(
                    value.toString().toLowerCase(),
                    ingredients.stream().filter(ingredient -> ingredient.getType() == value).collect(Collectors.toList())
            );
        }
        return "design";
    }

    @PostMapping
    public String proccessDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        final Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        System.out.println("Processing design: " + saved);
        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

}
