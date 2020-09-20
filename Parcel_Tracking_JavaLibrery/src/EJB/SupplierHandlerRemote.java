
package EJB;

import DTO.SupplierDTO;
import java.util.Collection;
import javax.ejb.Remote;


@Remote
public interface SupplierHandlerRemote {

    public boolean createSupplier(SupplierDTO supplier);    

    public Collection<SupplierDTO> findAllSuppliers();

    public int loginSupplier(String suppName, String suppPassword);
    
}
