package ru.ahmed.anam.taco.data;

import org.springframework.data.repository.CrudRepository;
import ru.ahmed.anam.taco.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
