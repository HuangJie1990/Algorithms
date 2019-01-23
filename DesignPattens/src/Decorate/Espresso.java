package Decorate;

public class Espresso extends Beverage {

    public Espresso(String description) {
        setDescription(description);
    }

    public Espresso() {
        this("Espresso");
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
