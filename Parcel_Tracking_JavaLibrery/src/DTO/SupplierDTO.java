
package DTO;

import java.io.Serializable;
import java.util.Collection;


public class SupplierDTO implements Serializable  {
    
    private int supplierId;
    private String suppName;
    private String suppPwd;
    private String suppAddress;
    private int suppContact;
    private Collection<ParcelDTO> parcelCollection;
    
    public SupplierDTO() {
        
    }     

    public SupplierDTO(int supplierId, String suppName, String suppPwd, String suppAddress, int suppContact) {
        this.supplierId = supplierId;
        this.suppName = suppName;
        this.suppPwd = suppPwd;
        this.suppAddress = suppAddress;
        this.suppContact = suppContact;
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
    
    public Collection<ParcelDTO> getParcelCollection() {
        return parcelCollection;
    }
    
    public void setParcelCollection(Collection<ParcelDTO> parcelCollection) {
        this.parcelCollection = parcelCollection;
    }   
}
