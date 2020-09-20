
package DTO;

import java.io.Serializable;
import java.util.Collection;


public class CustomerDTO implements Serializable   {
    
     private int customerId;
     private String custName;
     private String custPwd;
     private String custAddress;
     private int custContact;
     private boolean credentialsOk;     
     private Collection<ParcelDTO> parcelCollection;

    public CustomerDTO(int customerId, String custName, String custPwd, String custAddress, int custContact) {
        this.customerId = customerId;
        this.custName = custName;
        this.custPwd = custPwd;
        this.custAddress = custAddress;
        this.custContact = custContact;                
    }

    public CustomerDTO(int customerId, String custName, boolean credentialsOk) {
        this.customerId = customerId;
        this.custName = custName;
        this.credentialsOk = credentialsOk;
    }    

    public CustomerDTO() {
    }

    public boolean isCredentialsOk() {
        return credentialsOk;
    }

    public void setCredentialsOk(boolean credentialsOk) {
        this.credentialsOk = credentialsOk;
    }     
    
    public int getCustomerId() {
        return customerId;
    }       
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    /**
     * @return the parcelCollection
     */
    public Collection<ParcelDTO> getParcelCollection() {
        return parcelCollection;
    }

    /**
     * @param parcelCollection the parcelCollection to set
     */
    public void setParcelCollection(Collection<ParcelDTO> parcelCollection) {
        this.parcelCollection = parcelCollection;
    }

    @Override
    public int hashCode()
    {
        return this.customerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerDTO other = (CustomerDTO) obj;
        if (this.customerId != other.customerId) {
            return false;
        }
        return true;
    }    
    
    @Override
    public String toString() {
        return customerId + ":" + custName ;
    }       
    
}
