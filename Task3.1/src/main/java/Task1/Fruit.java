package Task1;

public abstract class Fruit {
    private float weight;
    private String type;



    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String[] str = type.split("\\.");
        this.type = str[str.length-1];
    }
}

