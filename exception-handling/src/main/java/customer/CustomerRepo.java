package customer;

public interface CustomerRepo {

    /**
     * @param id of the customer
     * @return the customer name
     * @throws CustomerNotFoundException if the customer with the given id cannot be found
     */
    String getCustomerName(int id) throws CustomerNotFoundException;
}
