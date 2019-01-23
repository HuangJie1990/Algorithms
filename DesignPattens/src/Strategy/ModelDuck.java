package Strategy;

public class ModelDuck extends Duck {
    public ModelDuck(){
        flyBehavior = new FlyNoWay();
        quarkBehavior = new Quark();
    }
    @Override
    public void display() {
        System.out.println("I'am a model duck");
    }
}
