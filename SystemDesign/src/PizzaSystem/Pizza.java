package PizzaSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pizza {

    // base fields
    private String base;
    private String baseSize;
    private List<String> toppings;


    public Pizza() {
        this.toppings = new ArrayList<>();
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBaseSize() {
        return baseSize;
    }

    public void setBaseSize(String baseSize) {
        this.baseSize = baseSize;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }
    public void addTopping(String topping){
        this.toppings.add(topping);
    }
}

