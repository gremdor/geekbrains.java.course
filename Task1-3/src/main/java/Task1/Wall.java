package Task1;

import java.util.Scanner;

public class Wall implements Obstacle {
    private int size;
    private final String TYPE = "Wall";

    public Wall(int size) {
        this.size = size;
    }

    @Override
    public void setLength(int length) {
        return;
    }

    @Override
    public void setHeight(int height) {
        this.size = height;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public int getHeight() {
        return this.size;
    }


    @Override
    public void info() {
        System.out.printf("%s height is %d sm\n", this.getType(), this.size);
    }

    @Override
    public String getType() {
        return this.TYPE;
    }
}
