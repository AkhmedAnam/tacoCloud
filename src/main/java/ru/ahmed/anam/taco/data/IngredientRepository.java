package ru.ahmed.anam.taco.data;

import org.springframework.data.repository.CrudRepository;
import ru.ahmed.anam.taco.Ingredient;

public interface IngredientRepository  extends CrudRepository<Ingredient, String> {

}
