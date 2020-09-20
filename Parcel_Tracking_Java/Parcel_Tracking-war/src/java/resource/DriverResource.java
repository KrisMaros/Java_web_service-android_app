
package resource;

import DTO.CustomerDTO;
import DTO.DriverDTO;
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
@Path("driver")
public class DriverResource {

    @Context
    private UriInfo context;
    private  Collection<DriverDTO> messageList = new ArrayList<DriverDTO>();    
    @EJB
    private  Admin_UIRemote admin_ui_remote;

    
    public DriverResource() {
    }
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DriverDTO> getJson()   {
        
        for(DriverDTO driv : admin_ui_remote.getAllDrivers()) {           

            messageList.add(driv);
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
    public DriverDTO putJson(DriverDTO content)
    {
        int drivId = 0;        
        DriverDTO driv = new DriverDTO(content.getDriverId(), content.getDrivName(), false);
        
        drivId = admin_ui_remote.loginDriver(content.getDrivName(), content.getDrivPwd());
        
        if(drivId > 0) {
            driv.setCredentialsOk(true);
            return driv;
        }
        return driv;
    }
}
