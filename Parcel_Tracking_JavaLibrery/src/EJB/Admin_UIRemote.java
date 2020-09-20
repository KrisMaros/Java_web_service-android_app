
package EJB;

import DTO.CustomerDTO;
import DTO.DriverDTO;
import DTO.ParcelDTO;
import DTO.StatusDTO;
import DTO.SupplierDTO;
import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.Remote;

@Remote
public interface Admin_UIRemote {

    public Collection<ParcelDTO> viewMyCollections(int suppId);

    public Collection<ParcelDTO> viewParcels(int drivId);

    public Collection<ParcelDTO> viewMyParcels(int custId);

    public Collection<ParcelDTO> viewMyDeliveredParcels(int custId);

    public int loginSupplier(String suppName, String suppPwd);

    public int loginDriver(String drivName, String drivPwd);

    public int loginCustomer(String custName, String custPwd);

    public Collection<SupplierDTO> getAllSuppliers();

    public Collection<DriverDTO> getAllDrivers();

    public Collection<CustomerDTO> getAllCustomers();

    public Collection<StatusDTO> getAllStatuses(String suppName, String drivName, String custName);

    public void bookParcelReturn(int parcelId, Timestamp date, int statusId);

    public void bookParcelCollection(ParcelDTO parcel, int custId, int drivId, int suppId, int statusId);

    public void updateParcelStatus(int parcelId, Timestamp date, int statusId);

    public boolean registerDriver(DriverDTO driver);

    public boolean registerCustomer(CustomerDTO customer);

    public boolean registerSupplier(SupplierDTO supplier);

    public Collection<ParcelDTO> findAllParcels();

    public int loginCustomerRestfull(String custName, String custPwd);

    public StatusDTO bookParcelReturnRestfull(int parcelId, int statusId);

    public StatusDTO updateParcelStatusRestfull(int parcelId, int statusId);
    
}
