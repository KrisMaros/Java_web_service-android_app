
package DTO;

import java.io.Serializable;
import java.util.Date;


public class ParcelDTO implements Serializable {
    
    private int parcelId;
    private Date awaiting_collection_date;
    private Date collection_date;
    private Date delivery_date;
    private Date awaiting_return_collection_date;
    private Date collection_return_date;
    private Date return_delivery_date;    
    private CustomerDTO customerDTO;
    private DriverDTO driverDTO ;
    private StatusDTO statusDTO;
    private SupplierDTO supplierDTO;    

    public ParcelDTO(int parcelId, Date awaiting_collection_date, Date collection_date, 
                     Date delivery_date, Date awaiting_return_collection_date, 
                     Date collection_return_date, Date return_delivery_date) 
    {
        this.parcelId = parcelId;
        this.awaiting_collection_date = awaiting_collection_date;
        this.collection_date = collection_date;
        this.delivery_date = delivery_date;
        this.awaiting_return_collection_date = awaiting_return_collection_date;
        this.collection_return_date = collection_return_date;
        this.return_delivery_date = return_delivery_date;
    }

    public ParcelDTO() {
    }   
    
    
    public int getParcelId() {
        return parcelId;
    }
    
    public void setParcelId(int parcelId) {
        this.parcelId = parcelId;
    }    
    
    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }
    
    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
    
    public DriverDTO getDriverDTO() {
        return driverDTO;
    }
    
    public void setDriverDTO(DriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }
    
    public StatusDTO getStatusDTO() {
        return statusDTO;
    }
    
    public void setStatusDTO(StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }
    
    public SupplierDTO getSupplierDTO() {
        return supplierDTO;
    }
    
    public void setSupplierDTO(SupplierDTO supplierDTO) {
        this.supplierDTO = supplierDTO;
    }

    /**
     * @return the awaiting_collection_date
     */
    public Date getAwaiting_collection_date() {
        return awaiting_collection_date;
    }

    /**
     * @param awaiting_collection_date the awaiting_collection_date to set
     */
    public void setAwaiting_collection_date(Date awaiting_collection_date) {
        this.awaiting_collection_date = awaiting_collection_date;
    }

    /**
     * @return the collection_date
     */
    public Date getCollection_date() {
        return collection_date;
    }

    /**
     * @param collection_date the collection_date to set
     */
    public void setCollection_date(Date collection_date) {
        this.collection_date = collection_date;
    }

    /**
     * @return the delivery_date
     */
    public Date getDelivery_date() {
        return delivery_date;
    }

    /**
     * @param delivery_date the delivery_date to set
     */
    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    /**
     * @return the awaiting_return_collection_date
     */
    public Date getAwaiting_return_collection_date() {
        return awaiting_return_collection_date;
    }

    /**
     * @param awaiting_return_collection_date the awaiting_return_collection_date to set
     */
    public void setAwaiting_return_collection_date(Date awaiting_return_collection_date) {
        this.awaiting_return_collection_date = awaiting_return_collection_date;
    }

    /**
     * @return the collection_return_date
     */
    public Date getCollection_return_date() {
        return collection_return_date;
    }

    /**
     * @param collection_return_date the collection_return_date to set
     */
    public void setCollection_return_date(Date collection_return_date) {
        this.collection_return_date = collection_return_date;
    }

    /**
     * @return the return_delivery_date
     */
    public Date getReturn_delivery_date() {
        return return_delivery_date;
    }

    /**
     * @param return_delivery_date the return_delivery_date to set
     */
    public void setReturn_delivery_date(Date return_delivery_date) {
        this.return_delivery_date = return_delivery_date;
    }    
}
