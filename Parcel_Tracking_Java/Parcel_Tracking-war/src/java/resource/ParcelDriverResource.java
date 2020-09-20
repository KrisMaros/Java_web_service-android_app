/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import DTO.ParcelDTO;
import DTO.CustomerDTO;
import DTO.DriverDTO;
import DTO.StatusDTO;
import DTO.SupplierDTO;
import EJB.Admin_UIRemote;
import java.io.Serializable;
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
@Path("parcel_driver")
public class ParcelDriverResource {

    @Context
    private UriInfo context;
    private  Collection<ParcelDTO> message = new ArrayList<ParcelDTO>();
    @EJB
    private  Admin_UIRemote admin_ui_remote;

    
    public ParcelDriverResource() {
    }
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ParcelDTO> getJson()   {
        
        for(ParcelDTO parc : admin_ui_remote.findAllParcels()) {           

            message.add(parc);
        }
        return message;
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void postJson(ParcelDTO content)
//    {         
//         
//    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ParcelDTO putJson(ParcelDTO content)
    {
       StatusDTO status = new StatusDTO();
        
       status = admin_ui_remote.updateParcelStatusRestfull(content.getParcelId(), content.getStatusDTO().getStatusId());
       
       ParcelDTO parc = new ParcelDTO();
       parc.setStatusDTO(status);
       
       return parc;     
    }
}
