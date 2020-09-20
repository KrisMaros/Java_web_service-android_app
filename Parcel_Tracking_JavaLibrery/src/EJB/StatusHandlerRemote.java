
package EJB;


import DTO.StatusDTO;
import java.util.Collection;
import javax.ejb.Remote;

@Remote
public interface StatusHandlerRemote {   

    public Collection<StatusDTO> findAllStatuses(String suppName, String drivName, String custName);
    
}
