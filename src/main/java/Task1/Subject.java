package Task1;

public class Subject implements Activity, Info {
    private String name;
    private int jumpHeight;
    private int runLength;
    private boolean canOvercome = true;
    private String type;

    public Subject(String name, int jumpHeight, int runLength, String type) {
        this.name = name;
        this.jumpHeight = jumpHeight;
        this.runLength = runLength;
        this.type = type;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (!canOvercome) {
            System.out.println(name + " is tied and can't overcome the " + obstacle.getType());
            return;
        } else if (obstacle.getLength() > runLength) {
            canOvercome = false;
            System.out.printf("The %s is too long. %s can't overcome it\n", obstacle.getType(), this.name);
            return;
        }

        System.out.println(name + " runs over the " + obstacle.getType());
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (!canOvercome) {
            System.out.println(name + " is tied and can't overcome the " + obstacle.getType());
            return;
        } else if (obstacle.getHeight() > jumpHeight) {
            canOvercome = false;
            System.out.printf("The %s is too high. %s can't overcome it\n", obstacle.getType(), this.name);
            return;
        }
        System.out.println(name + " jumps over the " + obstacle.getType());
    }

    @Override
    public void info() {
        System.out.printf("%s is a %s, can jump over %d sm and run over %d m\n",
                this.name, this.type, this.jumpHeight, this.runLength);
    }
}
