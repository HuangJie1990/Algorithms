package Decorate;

public class HouseBlend extends Beverage {

    public HouseBlend(String description) {
        setDescription(description);
    }

    public HouseBlend() {
        this("HouseBlend");
    }

    @Override
    public double cost() {
        return .89;
    }
}