public class CustomerRepoImpl implements CustomerRepo {
    @Override
    public String getCustomerName(int id) throws CustomerNotFoundException, CustomerInactiveException {
        if(id == 27) return "John Smith";
        if(id == 28) return "Peter Muller";
        if(id == 29) throw new CustomerInactiveException(id);
        throw new CustomerNotFoundException(id);

    }
}
