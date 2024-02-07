package PizzaSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaOrderSystem implements IPizzaOrderSystem {
    double discountRatio;
    // BASE ENUM
    private static final String FLOUR = "flour";
    private static final String WHEAT = "wheat";

    // TOPPING ENUM
    private static final String CHEESE = "cheese";
    private static final String SAUSAGE = "sausage";
    private static final String MUSHROOMS = "mushrooms";

    // size ratio enum
    private static final String SMALL = "SMALL";
    private static final String MEDIUM = "MEDIUM";
    private static final String LARGE = "LARGE";

    private static final Map<String, Double> BASE_PRICES = new HashMap<>();
    private static final Map<String, Double> SIZE_RATIO = new HashMap<>();
    private static final Map<String, Double> TOPPING_PRICES = new HashMap<>();

    static {
        // Add more toppings and prices as needed

        BASE_PRICES.put(FLOUR, 8.0);
        BASE_PRICES.put(WHEAT, 9.0);

        SIZE_RATIO.put(SMALL, 1.0); // small as default
        SIZE_RATIO.put(MEDIUM, 1.2);
        SIZE_RATIO.put(LARGE, 1.5);


        TOPPING_PRICES.put(CHEESE, 1.0);
        TOPPING_PRICES.put(SAUSAGE, 1.5);
        TOPPING_PRICES.put(MUSHROOMS, 1.0);

    }

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    @Override
    public Double getBasePrice(String baseName, String size) {
        return BASE_PRICES.get(baseName) * SIZE_RATIO.get(size);
    }

    @Override
    public Double getToppingsPrice(List<String> toppings) {
        double price = 0.0;
        for (String topping : toppings) {
            price += TOPPING_PRICES.get(topping);
        }
        return price;
    }

    @Override
    public Double calculatePrice(List<Pizza> pizzas) {
        double price = 0.0;
        for (Pizza pizza : pizzas) {
            price += getBasePrice(pizza.getBase(), pizza.getBaseSize());
            price += getToppingsPrice(pizza.getToppings());
        }
        return price;
    }

    @Override
    public Double calculatePriceAfterDiscount(List<Pizza> pizzas) {
        return calculatePrice(pizzas) * getDiscountRatio();
    }

    public static void main(String[] args) {
        PizzaOrderSystem orderSystem = new PizzaOrderSystem();
        List<Pizza> list = new ArrayList<>();
        Pizza pizza = new Pizza();
        pizza.setBase(FLOUR);
        pizza.setBaseSize(SMALL);
        pizza.addTopping(CHEESE);
        pizza.addTopping(SAUSAGE);
        pizza.addTopping(MUSHROOMS);
        list.add(pizza);
        Double price = orderSystem.calculatePrice(list);
        System.out.println(price);
    }
}
