
package EJB;

import DTO.CustomerDTO;
import java.util.Collection;
import javax.ejb.Remote;


@Remote
public interface CustomerHandlerRemote  {

    public boolean createCustomer(CustomerDTO cust);    

    public int loginCustomer(String custName, String custPassword);

    public Collection<CustomerDTO> findAllCustomers();

    public int loginCustomerRestfull(String custName, String custPassword);
    
}
