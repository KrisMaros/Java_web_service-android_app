
package EJB;

import DTO.DriverDTO;
import java.util.Collection;
import javax.ejb.Remote;

@Remote
public interface DriverHandlerRemote {

    public boolean createDriver(DriverDTO driv);    

    public int loginDriver(String drivName, String drivPassword);

    public Collection<DriverDTO> findAllDrivers();
    
}
