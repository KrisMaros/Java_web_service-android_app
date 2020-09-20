
package DTO;

import java.io.Serializable;
import java.util.Collection;

public class StatusDTO implements Serializable  {
    
    private int statusId;
    private String statusName;
    private Collection<ParcelDTO> parcelCollection;

    public StatusDTO(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public StatusDTO() {
    }        

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }    
    
    public String getStatusName() {
        return statusName;
    }
    
    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
        return this.statusId;
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
        final StatusDTO other = (StatusDTO) obj;
        if (this.statusId != other.statusId) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return statusId + ":" + statusName ;
    }    
}
