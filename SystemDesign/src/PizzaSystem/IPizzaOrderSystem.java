package PizzaSystem;

import java.util.List;

public interface IPizzaOrderSystem {

    Double getBasePrice(String baseName, String size);

    Double getToppingsPrice(List<String> toppings);

    Double calculatePrice(List<Pizza> pizzas);

    Double calculatePriceAfterDiscount(List<Pizza> pizzas);
}
