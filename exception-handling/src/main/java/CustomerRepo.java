public interface CustomerRepo {

    String getCustomerName(int id) throws CustomerNotFoundException, CustomerInactiveException;
}
