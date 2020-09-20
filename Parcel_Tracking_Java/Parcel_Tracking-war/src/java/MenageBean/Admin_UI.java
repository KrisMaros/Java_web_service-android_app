
package MenageBean;

import DTO.CustomerDTO;
import DTO.DriverDTO;
import DTO.ParcelDTO;
import DTO.StatusDTO;
import DTO.SupplierDTO;
import EJB.Admin_UIRemote;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "adminUI")
@SessionScoped
public class Admin_UI implements Serializable {   
    
    @EJB
    private Admin_UIRemote admin_ui_remote;
    
    private String statusName;
    private String drivName;
    private String drivPwd;
    private String drivPwd2;
    private String drivAddress;
    private String drivCar;
    private int drivContact;    
    private String custName;
    private String custPwd;
    private String custPwd2;
    private String custAddress;
    private int custContact;    
    private int parcelId;
    private int customerId;
    private int driverId;
    private int statusId;     
    private int supplierId;    
    private String suppName;
    private String suppPwd;
    private String suppPwd2;    
    private String suppAddress;
    private int suppContact;
    private CustomerDTO custDTO;
    private StatusDTO statDTO;
    private DriverDTO drivDTO;
    private Collection<ParcelDTO> parcelCollection;
    private Collection<StatusDTO> statusCollection;    
    
    public Admin_UI() {
    }
    
    public String getMyCollections(int suppId) {
        
       parcelCollection = admin_ui_remote.viewMyCollections(suppId);               
       return "viewMyCollections.xhtml";
    }

    public String getParcels(int drivId) {
        
       parcelCollection = admin_ui_remote.viewParcels(drivId);               
       return "viewParcels.xhtml";
    }
    
    public String getMyParcels(int custId) {
        
       parcelCollection = admin_ui_remote.viewMyParcels(custId);               
       return "viewMyParcels.xhtml";
    }
    
    public String getMyDeliveredParcels(int custId) {
        
       parcelCollection = admin_ui_remote.viewMyDeliveredParcels(custId);               
       return "viewMyDeliveredParcels.xhtml";
    }  
    
    public String loginSupplier(String suppName, String suppPwd) {
        
        supplierId = admin_ui_remote.loginSupplier(suppName, suppPwd);        
        
        if (supplierId > 0)
        {
            return "supplier.xhtml";
        }
        else
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supplier password not correct"));
            return null;
        }        
    }
    
    public String loginDriver(String drivName, String drivPwd) {
        
        driverId = admin_ui_remote.loginDriver(drivName, drivPwd);        
        
        if (driverId != 0)
        {
            return "driver.xhtml";
        }
        else
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Driver password not correct"));
            return null;
        }        
    }
    
     public String loginCustomer(String custName, String custPwd) {
        
        customerId = admin_ui_remote.loginCustomer(custName, custPwd);        
        
        if (customerId != 0)
        {
            return "customer.xhtml";
        }
        else
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer password not correct"));
            return null;
        }        
    } 
    
    public Collection<SupplierDTO> getAllSuppliers() {
        
       return admin_ui_remote.getAllSuppliers();       
    }
    
    public Collection<DriverDTO> getAllDrivers() {
        
       return admin_ui_remote.getAllDrivers();       
    }
    
    public Collection<CustomerDTO> getAllCustomers() {
        
       return admin_ui_remote.getAllCustomers();       
    }
    
    public String getAllSupplierStatuses(String suppName, String drivName, String custName)  {
        
       statusCollection = admin_ui_remote.getAllStatuses(suppName, drivName, custName);
       return "bookParcelCollection.xhtml";       
    }
    
    public String getAllDriverStatuses(int parcId, String suppName, String drivName, String custName) {
        
       parcelId = parcId; 
       statusCollection = admin_ui_remote.getAllStatuses(suppName, drivName, custName);
       return "updateParcelStatus.xhtml";       
    }
    
    public String getAllCustomerStatuses(int parcId, String suppName, String drivName, String custName) {
        
       parcelId = parcId; 
       statusCollection = admin_ui_remote.getAllStatuses(suppName, drivName, custName);
       return "bookParcelReturn.xhtml";       
    }
    
    public String bookParcelReturn(int statusId)  { 
        
       Timestamp date = new Timestamp(new Date().getTime());
       
       for( ParcelDTO p : parcelCollection)  {
           
           if(p.getParcelId() == parcelId) {
               
               if(p.getAwaiting_return_collection_date() == null) {                   
                   
                    admin_ui_remote.bookParcelReturn(parcelId, date, statusId);                                   
               }
               else {
                   
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The return has been booked already"));
                   return null;
               }                       
           }
       }       
       return "customer.xhtml";       
    }    
    
    public String bookParcelCollection(int custId, int drivId, int statId) {
        
        Timestamp date = new Timestamp(new Date().getTime());
        admin_ui_remote.bookParcelCollection(new ParcelDTO(-1, date, null, null, null, null, null), custId, drivId, supplierId, statId);
        return "supplier.xhtml";
    }

    public String updateParcelStatus(int statusId )  {       
       
       Timestamp date = new Timestamp(new Date().getTime());
       
       for( ParcelDTO p : parcelCollection) {
           
           if(p.getParcelId() == parcelId) {
               
               if(p.getCollection_date() == null) {
                   
                   if(statusId == 2) {
                       admin_ui_remote.updateParcelStatus(parcelId, date, statusId);
                   }
                   else {
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parcel is not collected: wrong status selected"));
                       return null;
                   }                   
               }
               else if(p.getDelivery_date() == null) {
                   
                   if(statusId == 3) {
                       admin_ui_remote.updateParcelStatus(parcelId, date, statusId);
                   }
                   else {
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parcel is not delivered: wrong status selected"));
                       return null;
                   }       
               }
               else if(p.getAwaiting_return_collection_date() != null && p.getCollection_return_date() == null) {
                   
                   if(statusId == 5) {
                       admin_ui_remote.updateParcelStatus(parcelId, date, statusId);
                   }
                   else {
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Return is not collected: wrong status selected"));
                       return null;
                   }       
               }
               else if(p.getAwaiting_return_collection_date() == null) {
                   
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parcel not returned"));
                    return null;
               }               
               else if(p.getReturn_delivery_date() == null) {
                   
                   if(statusId == 6) {
                       admin_ui_remote.updateParcelStatus(parcelId, date, statusId);
                   }
                   else {
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Return is not delivered: wrong status selected"));
                       return null;
                   }       
               }
               else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("All statuses updated"));
                    return null;
               }
           }           
       }       
       return "driver.xhtml";       
    }     
    
    public String registerDriver() throws NoSuchAlgorithmException {
        
        boolean dataOK = false;

        if (drivPwd.equals(drivPwd2))
        {            
                byte[] hash = MessageDigest.getInstance("SHA-256")
                              .digest(drivPwd.getBytes(StandardCharsets.UTF_8));

                drivPwd = Base64.getEncoder().encodeToString(hash);
                
                dataOK = admin_ui_remote.registerDriver(new DriverDTO(-1, drivName, drivPwd, drivAddress, drivCar, drivContact)); 
        }        
        if (dataOK)
        {
            return "index.xhtml";
        }
        else
        {           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwords are not the same"));            
            return null;
        }         
    }
    
    public String registerCustomer() throws NoSuchAlgorithmException {
        
        boolean dataOK = false;

        if (custPwd.equals(custPwd2))
        {            
                byte[] hash = MessageDigest.getInstance("SHA-256")
                              .digest(custPwd.getBytes(StandardCharsets.UTF_8));

                custPwd = Base64.getEncoder().encodeToString(hash);
                
                dataOK = admin_ui_remote.registerCustomer(new CustomerDTO(-1, custName, custPwd, custAddress, custContact)); 
        }        
        if (dataOK)
        {
            return "index.xhtml";
        }
        else
        {           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwords are not the same"));            
            return null;
        }       
    }    
    
    public String registerSupplier() throws NoSuchAlgorithmException
    {
        boolean dataOK = false;

        if (suppPwd.equals(suppPwd2))
        {            
                byte[] hash = MessageDigest.getInstance("SHA-256")
                              .digest(suppPwd.getBytes(StandardCharsets.UTF_8));

                suppPwd = Base64.getEncoder().encodeToString(hash);
                
                dataOK = admin_ui_remote.registerSupplier(new SupplierDTO(-1, suppName, suppPwd, suppAddress, suppContact)); 
        }        
        if (dataOK)
        {
            return "index.xhtml";
        }
        else
        {           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwords are not the same"));            
            return null;
        }
    }
    
    public String Logout() {
        
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         return "index.xhtml";
    }    

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;        
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getSuppPwd() {
        return suppPwd;
    }

    public void setSuppPwd(String suppPwd) {
        this.suppPwd = suppPwd;
    }

    public String getSuppAddress() {
        return suppAddress;
    }

    public void setSuppAddress(String suppAddress) {
        this.suppAddress = suppAddress;
    }

    public int getSuppContact() {
        return suppContact;
    }

    public void setSuppContact(int suppContact) {
        this.suppContact = suppContact;
    }

    public String getSuppPwd2() {
        return suppPwd2;
    }

    public void setSuppPwd2(String suppPwd2) {
        this.suppPwd2 = suppPwd2;
    }

    public int getParcelId() {
        return parcelId;
    } 

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDrivName() {
        return drivName;
    }

    public void setDrivName(String drivName) {
        this.drivName = drivName;
    }

    public String getDrivPwd() {
        return drivPwd;
    }

    public void setDrivPwd(String drivPwd) {
        this.drivPwd = drivPwd;
    }

    public String getDrivAddress() {
        return drivAddress;
    }

    public void setDrivAddress(String drivAddress) {
        this.drivAddress = drivAddress;
    }

    public String getDrivCar() {
        return drivCar;
    }

    public void setDrivCar(String drivCar) {
        this.drivCar = drivCar;
    }

    public int getDrivContact() {
        return drivContact;
    }

    public void setDrivContact(int drivContact) {
        this.drivContact = drivContact;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public int getCustContact() {
        return custContact;
    }

    public void setCustContact(int custContact) {
        this.custContact = custContact;
    }    

    public String getDrivPwd2() {
        return drivPwd2;
    }

    public void setDrivPwd2(String drivPwd2) {
        this.drivPwd2 = drivPwd2;
    }

    public String getCustPwd2() {
        return custPwd2;
    }

    public void setCustPwd2(String custPwd2) {
        this.custPwd2 = custPwd2;
    }
    
    public Collection<ParcelDTO> getParcelCollection() {
        return parcelCollection;
    }
    
    public void setParcelCollection(Collection<ParcelDTO> parcelCollection) {
        this.parcelCollection = parcelCollection;
    }

    public Collection<StatusDTO> getStatusCollection() {
        return statusCollection;
    }

    public void setStatusCollection(Collection<StatusDTO> statusCollection) {
        this.statusCollection = statusCollection;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public CustomerDTO getCustDTO() {
        return custDTO;
    }

    public void setCustDTO(CustomerDTO custDTO) {
        this.custDTO = custDTO;
    } 

    public StatusDTO getStatDTO() {
        return statDTO;
    }

    public void setStatDTO(StatusDTO statDTO) {
        this.statDTO = statDTO;
    }

    public DriverDTO getDrivDTO() {
        return drivDTO;
    }

    public void setDrivDTO(DriverDTO drivDTO) {
        this.drivDTO = drivDTO;
    }     
}
