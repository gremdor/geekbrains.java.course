package Task1;

public class Orange extends Fruit {
    private final float WEIGHT = 1.5f;

    public Orange() {
        super();
        setWeight(WEIGHT);
        setType(this.getClass().getName());
    }


}
