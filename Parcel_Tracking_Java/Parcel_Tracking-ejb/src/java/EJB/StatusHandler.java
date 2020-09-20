
package EJB;

import DTO.StatusDTO;
import entity.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class StatusHandler implements StatusHandlerRemote  {

    @PersistenceContext(unitName = "Parcel_Tracking-ejbPU")
    private EntityManager em;
    
    public void persist(Object object)  {
        em.persist(object);
    }    
    
    @Override
    public Collection<StatusDTO> findAllStatuses(String suppName, String drivName, String custName)
    {
        Query q = em.createNamedQuery("Status.findAll");        
        List<Status> list = q.getResultList();
        Collection<StatusDTO> statList = new ArrayList<>(list.size());
        
        for (int i=0; i < list.size(); i++) {        
        
           if(!suppName.isEmpty() && i == 0) {
               
                Status s = (Status)list.get(i);                
                StatusDTO statDTO = new StatusDTO(s.getStatusId(),
                                              s.getStatusName());
                statList.add(statDTO);
                break;               
           }
           else if (!drivName.isEmpty() && i != 0 && i != 3) {
               
                Status s = (Status)list.get(i);           
                StatusDTO statDTO = new StatusDTO(s.getStatusId(),
                                                  s.getStatusName());
                statList.add(statDTO);
           }
           if(!custName.isEmpty() && i == 3) {
               
                Status s = (Status)list.get(i);                
                StatusDTO statDTO = new StatusDTO(s.getStatusId(),
                                              s.getStatusName());
                statList.add(statDTO);
                break;               
           }           
        }        
        return statList;
    }   
}
