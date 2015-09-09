package customer;

public class CustomerRepoImpl implements CustomerRepo {
    @Override
    public String getCustomerName(int id) throws CustomerNotFoundException {
        if(id == 27) return "John Smith";
        if(id == 28) return "Peter Muller";
        if(id == 29) return "Hans Dampf";

        throw new CustomerNotFoundException(id);

    }
}
