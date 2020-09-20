
package EJB;

import DTO.CustomerDTO;
import DTO.DriverDTO;
import DTO.ParcelDTO;
import DTO.StatusDTO;
import DTO.SupplierDTO;
import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class Admin_UI implements Admin_UIRemote {

    @EJB
    private SupplierHandlerRemote supplier_handler_remote;
    
    @EJB
    private ParcelHandlerRemote parcel_handler_remote;
    
    @EJB
    private CustomerHandlerRemote customer_handler_remote;
    
    @EJB
    private DriverHandlerRemote driver_handler_remote;
    
    @EJB
    private StatusHandlerRemote status_handler_remote;
    
    @Override
    public Collection<ParcelDTO> viewMyCollections(int suppId) {
        
       return parcel_handler_remote.findMyCollections(suppId);              
       
    }

    @Override
    public Collection<ParcelDTO> viewParcels(int drivId) {
        
       return parcel_handler_remote.findParcels(drivId);     
    }
    
    @Override
    public Collection<ParcelDTO> findAllParcels() {
        
       return parcel_handler_remote.findAllParcels();     
    }
    
    @Override
    public Collection<ParcelDTO> viewMyParcels(int custId) {
        
       return parcel_handler_remote.findMyParcels(custId);      
    }
    
    @Override
    public Collection<ParcelDTO> viewMyDeliveredParcels(int custId) {
        
       return parcel_handler_remote.findMyDeliveredParcels(custId);       
    }  
    
    @Override
    public int loginSupplier(String suppName, String suppPwd) {
        
        return supplier_handler_remote.loginSupplier(suppName, suppPwd);    
    }
    
    @Override
    public int loginDriver(String drivName, String drivPwd) {
        
        return driver_handler_remote.loginDriver(drivName, drivPwd);   
    }
    
    @Override
    public int loginCustomer(String custName, String custPwd) {
        
        return customer_handler_remote.loginCustomer(custName, custPwd);           
    } 
    
    @Override
    public int loginCustomerRestfull(String custName, String custPwd) {
        
        return customer_handler_remote.loginCustomerRestfull(custName, custPwd);           
    } 
    
    @Override
    public Collection<SupplierDTO> getAllSuppliers() {
        
       return supplier_handler_remote.findAllSuppliers();       
    }
    
    @Override
    public Collection<DriverDTO> getAllDrivers() {
        
       return driver_handler_remote.findAllDrivers();       
    }
    
    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        
       return customer_handler_remote.findAllCustomers();       
    }
    
    @Override
    public Collection<StatusDTO> getAllStatuses(String suppName, String drivName, String custName)  {
        
       return status_handler_remote.findAllStatuses(suppName, drivName, custName);              
    }    
    
    @Override
    public void bookParcelReturn(int parcelId, Timestamp date, int statusId)  {         
       
       parcel_handler_remote.createParcelReturn(parcelId, date, statusId);              
    }

    @Override
    public StatusDTO bookParcelReturnRestfull(int parcelId, int statusId)  {         
       
       return parcel_handler_remote.bookParcelReturnRestfull(parcelId, statusId);              
    }    
    
    @Override
    public void bookParcelCollection(ParcelDTO parcel, int custId, int drivId, int suppId, int statusId) {        
        
       parcel_handler_remote.createParcelCollection(parcel, custId, drivId, suppId, statusId);        
    }

    @Override
    public void updateParcelStatus(int parcelId, Timestamp date, int statusId )  {      
       
        parcel_handler_remote.updateParcelStatus(parcelId, date, statusId);                  
    }
    
    @Override
    public StatusDTO updateParcelStatusRestfull(int parcelId, int statusId )  {      
       
        return parcel_handler_remote.updateParcelStatusRestfull(parcelId, statusId);                  
    } 
    
    @Override
    public boolean registerDriver(DriverDTO driver)  {       
                
        return driver_handler_remote.createDriver(driver);        
    }
    
    @Override
    public boolean registerCustomer(CustomerDTO customer) {        
                
        return customer_handler_remote.createCustomer(customer);
    }    
    
    @Override
    public boolean registerSupplier(SupplierDTO supplier)  {     
                
        return supplier_handler_remote.createSupplier(supplier);        
    }
}
