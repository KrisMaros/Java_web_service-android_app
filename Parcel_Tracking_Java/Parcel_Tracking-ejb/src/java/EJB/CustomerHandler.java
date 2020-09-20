
package EJB;

import DTO.CustomerDTO;
import entity.Customer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJBException ;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class CustomerHandler implements CustomerHandlerRemote {
    
    
    @PersistenceContext(unitName = "Parcel_Tracking-ejbPU")
    private EntityManager em;
    
    public void persist(Object object)  {
        em.persist(object);
    }    
    
    @Override
    public boolean createCustomer(CustomerDTO cust) {       
       boolean check = false;
       Customer c = new Customer();
       c.setCustName(cust.getCustName());
       c.setCustPwd(cust.getCustPwd());
       c.setCustAddress(cust.getCustAddress());
       c.setCustContact(cust.getCustContact());
       try
        {
            persist(c);
            check = true;
        }
        catch (Exception e)
        {
            throw new EJBException("Problem registring customer", e);
        }
        return check;
   }
    
    @Override
    public int loginCustomer(String custName, String custPassword) 
    {
        int custId = 0;        
        try
        {
            Query sq = em.createNamedQuery("Customer.findByCustName");
            sq.setParameter("custName", custName);            
                
            Customer s = (Customer)sq.getSingleResult();
            
            if(s != null) {
                
                byte[] hash = MessageDigest.getInstance("SHA-256")
                              .digest(custPassword.getBytes(StandardCharsets.UTF_8));

                custPassword = Base64.getEncoder().encodeToString(hash);
                
                if(s.getCustPwd().equals(custPassword))  {
                    
                   custId = s.getCustomerId(); 
                }                
            }                        
        }
        catch (Exception e)
        {    
            
            throw new EJBException("Customer name not found", null);
        }
        return custId;        
    }
    
    @Override
    public int loginCustomerRestfull(String custName, String custPassword)
    {
        int custId = 0;        
        try
        {
            Query sq = em.createNamedQuery("Customer.findByCustName");
            sq.setParameter("custName", custName);            
                
            Customer s = (Customer)sq.getSingleResult();
            
            if(s != null) {
                
                byte[] hash = MessageDigest.getInstance("SHA-256")
                              .digest(custPassword.getBytes(StandardCharsets.UTF_8));

                custPassword = Base64.getEncoder().encodeToString(hash);
                
                if(s.getCustPwd().equals(custPassword))  {
                    
                   custId = s.getCustomerId(); 
                }                
            }                        
        }
        catch (Exception e)
        {            
            throw new EJBException("Customer name not found", null);
        }
        return custId;        
    }   
 
    @Override
    public Collection<CustomerDTO> findAllCustomers()
    {
        Query q = em.createNamedQuery("Customer.findAll");        
        List<Customer> list = q.getResultList();
        Collection<CustomerDTO> custList = new ArrayList<>(list.size());
        for (Customer s : list) {       
           
           CustomerDTO custDTO = new CustomerDTO(s.getCustomerId(),
                                             s.getCustName(),
                                             s.getCustPwd(),
                                             s.getCustAddress(),                                             
                                             s.getCustContact());
           custList.add(custDTO);
        }        
        return custList;
    }       
}
