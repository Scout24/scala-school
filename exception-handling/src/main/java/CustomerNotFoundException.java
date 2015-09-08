public class CustomerNotFoundException extends Exception {
    private int id;

    public CustomerNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
