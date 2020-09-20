
package DTO;

import java.io.Serializable;
import java.util.Collection;


public class DriverDTO implements Serializable  {
    
    private int driverId;
    private String drivName;
    private String drivPwd;
    private String drivAddress;
    private String drivCar;
    private int drivContact;
    private boolean credentialsOk;
    private Collection<ParcelDTO> parcelCollection;

    public DriverDTO(int driverId, String drivName, String drivPwd, String drivAddress, String drivCar, int drivContact) {
        this.driverId = driverId;
        this.drivName = drivName;
        this.drivPwd = drivPwd;
        this.drivAddress = drivAddress;
        this.drivCar = drivCar;
        this.drivContact = drivContact;
    }

    public DriverDTO(int driverId, String drivName, boolean credentialsOk) {
        this.driverId = driverId;
        this.drivName = drivName;
        this.credentialsOk = credentialsOk;
    }

      

    public DriverDTO() {
    }

    public boolean isCredentialsOk() {
        return credentialsOk;
    }

    public void setCredentialsOk(boolean credentialsOk) {
        this.credentialsOk = credentialsOk;
    }        
    
    public int getDriverId() {
        return driverId;
    }
    
    public void setDriverId(int driverId) {
        this.driverId = driverId;
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
        return this.driverId;
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
        final DriverDTO other = (DriverDTO) obj;
        if (this.driverId != other.driverId) {
            return false;
        }
        return true;
    }    
    
    @Override
    public String toString() {
        return driverId + ":" + drivName ;
    }   
}
