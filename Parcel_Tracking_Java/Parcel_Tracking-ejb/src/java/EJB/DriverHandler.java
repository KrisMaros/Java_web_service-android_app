
package EJB;


import DTO.DriverDTO;
import entity.Driver;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class DriverHandler implements DriverHandlerRemote {

    @PersistenceContext(unitName = "Parcel_Tracking-ejbPU")
    private EntityManager em;
    
    public void persist(Object object)  {
        em.persist(object);
    }    
    
   @Override
   public boolean createDriver(DriverDTO driv) {       
       boolean check = false;
       Driver d = new Driver();
       d.setDrivName(driv.getDrivName());
       d.setDrivPwd(driv.getDrivPwd());
       d.setDrivAddress(driv.getDrivAddress());
       d.setDrivCar(driv.getDrivCar());
       d.setDrivContact(driv.getDrivContact());
       try
        {
            persist(d);
            check = true;
        }
        catch (Exception e)
        {
            throw new EJBException("Problem registring driver", e);
        }
        return check;
   }

    @Override
    public int loginDriver(String drivName, String drivPassword)
    {
        int drivId = 0;        
        try
        {
            Query sq = em.createNamedQuery("Driver.findByDrivName");
            sq.setParameter("drivName", drivName);
            Driver s = (Driver)sq.getSingleResult();
            
            if(s != null) {
                
                byte[] hash = MessageDigest.getInstance("SHA-256")
                              .digest(drivPassword.getBytes(StandardCharsets.UTF_8));

                drivPassword = Base64.getEncoder().encodeToString(hash);
                
                if(s.getDrivPwd().equals(drivPassword))  {
                    
                   drivId = s.getDriverId(); 
                }                
            }                
        }
        catch (Exception e)
        {
            throw new EJBException("Driver name not found", e);
        }        
        return drivId;
    }   
 
    @Override
    public Collection<DriverDTO> findAllDrivers()
    {
        Query q = em.createNamedQuery("Driver.findAll");        
        List<Driver> list = q.getResultList();
        Collection<DriverDTO> drivList = new ArrayList<>(list.size());
        for (Driver s : list) {       
           
           DriverDTO drivDTO = new DriverDTO(s.getDriverId(),
                                             s.getDrivName(),
                                             s.getDrivPwd(),
                                             s.getDrivAddress(),
                                             s.getDrivCar(),
                                             s.getDrivContact());
           drivList.add(drivDTO);
        }        
        return drivList;
    }      
   
}
