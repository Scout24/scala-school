package customer;

public class CustomerInactiveException extends Exception {
    private final int id;

    public CustomerInactiveException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
