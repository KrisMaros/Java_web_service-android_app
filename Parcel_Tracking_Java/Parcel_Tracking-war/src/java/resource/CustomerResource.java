
package resource;

import DTO.CustomerDTO;
import EJB.Admin_UIRemote;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Krzychu
 */
@Path("customer")
public class CustomerResource {

    @Context
    private UriInfo context;
    private  Collection<CustomerDTO> messageList = new ArrayList<CustomerDTO>();    
    @EJB
    private  Admin_UIRemote admin_ui_remote;

    
    public CustomerResource() {
    }
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CustomerDTO> getJson()   {
        
        for(CustomerDTO cust : admin_ui_remote.getAllCustomers()) {           

            messageList.add(cust);
        }
        return messageList;
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void postJson(CustomerDTO content)
//    {      
//        
//          admin_ui_remote.registerCustomer(content);
//    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO putJson(CustomerDTO content)
    {
        int custId = 0;        
        CustomerDTO cust = new CustomerDTO(0, content.getCustName(), false);
        
        custId = admin_ui_remote.loginCustomerRestfull(content.getCustName(), content.getCustPwd());
        
        if(custId > 0) {
            cust.setCredentialsOk(true);
            cust.setCustomerId(custId);
            cust.setCustPwd(content.getCustPwd());
            return cust;
        }        
        return cust;
    }
}
