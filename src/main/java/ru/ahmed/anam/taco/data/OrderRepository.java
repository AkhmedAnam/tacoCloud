package ru.ahmed.anam.taco.data;

import org.springframework.data.repository.CrudRepository;
import ru.ahmed.anam.taco.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
