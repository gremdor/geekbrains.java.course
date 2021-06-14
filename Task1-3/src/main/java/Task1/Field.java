package Task1;

public class Field implements Obstacle {
    private int size;
    private final String TYPE = "Field";


    public Field(int size) {
        this.size = size;
    }

    @Override
    public void setLength(int length) {
        this.size = length;
    }

    @Override
    public void setHeight(int height) {
        return;
    }

    @Override
    public int getLength() {
        return this.size;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void info() {
        System.out.printf("%s length is %d m\n", this.getType(), this.size);
    }

    @Override
    public String getType() {
        return this.TYPE;
    }

}
