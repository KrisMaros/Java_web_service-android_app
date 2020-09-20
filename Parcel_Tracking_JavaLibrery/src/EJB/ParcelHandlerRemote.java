
package EJB;

import DTO.ParcelDTO;
import DTO.StatusDTO;
import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.Remote;

@Remote
public interface ParcelHandlerRemote {

    public void createParcelCollection(ParcelDTO parcel, int custId, int drivId, int suppId, int statusId);

    public Collection<ParcelDTO> findMyCollections(int supplierId);   

    public Collection<ParcelDTO> findMyParcels(int customerId);

    public Collection<ParcelDTO> findParcels(int driverId);

    public void createParcelReturn(int parcelId, Timestamp parcelReturnDate, int statusId);

    public Collection<ParcelDTO> findMyDeliveredParcels(int customerId);

    public void updateParcelStatus(int parcelId, Timestamp Date, int statusId);

    public Collection<ParcelDTO> findAllParcels();    

    public StatusDTO bookParcelReturnRestfull(int parcelId, int statusId);

    public StatusDTO updateParcelStatusRestfull(int parcelId, int statusId);
    
}
