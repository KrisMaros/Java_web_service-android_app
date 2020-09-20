
package EJB;

import DTO.SupplierDTO;
import entity.Supplier;
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
public class SupplierHandler implements SupplierHandlerRemote {

    @PersistenceContext(unitName = "Parcel_Tracking-ejbPU")
    private EntityManager em;
    
    public void persist(Object object)  {
        em.persist(object);
    }    
    
   @Override
   public boolean createSupplier(SupplierDTO supplier) {       
       boolean check = false;
       Supplier s = new Supplier();
       s.setSuppName(supplier.getSuppName());
       s.setSuppPwd(supplier.getSuppPwd());
       s.setSuppAddress(supplier.getSuppAddress());
       s.setSuppContact(supplier.getSuppContact());
       try
        {
            persist(s);
            check = true;
        }
        catch (Exception e)
        {
            throw new EJBException("Problem registering supplier", e);
        }
        return check;
   }
   
   @Override
   public int loginSupplier(String suppName, String suppPassword)
    {
        int suppId = 0;        
        try
        {
            Query sq = em.createNamedQuery("Supplier.findBySuppName");
            sq.setParameter("suppName", suppName);
            Supplier s = (Supplier)sq.getSingleResult();
            
            if(s != null) {
                
                byte[] hash = MessageDigest.getInstance("SHA-256")
                              .digest(suppPassword.getBytes(StandardCharsets.UTF_8));

                suppPassword = Base64.getEncoder().encodeToString(hash);
                
                if(s.getSuppPwd().equals(suppPassword))  {
                    
                   suppId = s.getSupplierId(); 
                }                
            }                
        }
        catch (Exception e)
        {
            throw new EJBException("Supplier name not found", e);
        }        
        return suppId;
    }   
 
   @Override
   public Collection<SupplierDTO> findAllSuppliers()
    {
        Query q = em.createNamedQuery("Supplier.findAll");        
        List<Supplier> list = q.getResultList();
        Collection<SupplierDTO> suppList = new ArrayList<>(list.size());
        for (Supplier s : list) {       
           
           SupplierDTO suppDTO = new SupplierDTO(s.getSupplierId(),
                                                 s.getSuppName(),
                                                 s.getSuppPwd(),
                                                 s.getSuppAddress(),
                                                 s.getSuppContact());
           suppList.add(suppDTO);
        }        
        return suppList;
    }   
}
