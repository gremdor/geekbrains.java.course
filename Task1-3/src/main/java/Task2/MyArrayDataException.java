package Task2;

public class MyArrayDataException extends RuntimeException{
    private String data;

    public String getData() {
        return data;
    }

    public MyArrayDataException(String data) {
        this.data = data;
    }
}
