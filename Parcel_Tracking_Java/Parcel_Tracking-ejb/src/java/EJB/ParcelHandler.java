
package EJB;

import DTO.CustomerDTO;
import DTO.DriverDTO;
import DTO.ParcelDTO;
import DTO.StatusDTO;
import DTO.SupplierDTO;
import entity.Customer;
import entity.Driver;
import entity.Parcel;
import entity.Status;
import entity.Supplier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;


@Stateless
public class ParcelHandler implements ParcelHandlerRemote {

    @PersistenceContext(unitName = "Parcel_Tracking-ejbPU")
    private EntityManager em;
    
    public void persist(Object object)  {
        em.persist(object);
    }    
   
   @Override
   public void createParcelCollection(ParcelDTO parcel, int custId, int drivId, int suppId, int statusId) {        
       
       Parcel p = new Parcel();
       p.setAwaitingCollectionDate(parcel.getAwaiting_collection_date());
       p.setCollectionDate(parcel.getCollection_date());
       p.setDeliveryDate(parcel.getDelivery_date());
       p.setAwaitingReturnCollectionDate(parcel.getAwaiting_return_collection_date());
       p.setCollectionReturnDate(parcel.getCollection_return_date());
       p.setReturnDeliveryDate(parcel.getReturn_delivery_date());       
       
       Query sq = em.createNamedQuery("Customer.findByCustomerId");
       sq.setParameter("customerId", custId);
       Customer cust = (Customer)sq.getSingleResult();       
       p.setCustomerId(cust);
       
       Query sq2 = em.createNamedQuery("Driver.findByDriverId");
       sq2.setParameter("driverId", drivId);
       Driver driv = (Driver)sq2.getSingleResult();       
       p.setDriverId(driv);       
       
       Query sq3 = em.createNamedQuery("Status.findByStatusId");
       sq3.setParameter("statusId", statusId);
       Status stat = (Status)sq3.getSingleResult();       
       p.setStatusId(stat);
       
       Query sq4 = em.createNamedQuery("Supplier.findBySupplierId");
       sq4.setParameter("supplierId", suppId);
       Supplier supp = (Supplier)sq4.getSingleResult();       
       p.setSupplierId(supp);
       
       try
        {
            persist(p);            
        }
        catch (Exception e)
        {
            throw new EJBException("Problem booking parcel collection", e);
        }        
   }
   
   
   @Override
   public Collection<ParcelDTO> findMyCollections(int supplierId)
    {
        Query q = em.createNamedQuery("Parcel.findAll");        
        List<Parcel> list = q.getResultList();
        Collection<ParcelDTO> parcelList = new ArrayList<>(list.size());
        for (Parcel p : list) {
            
            if(p.getSupplierId().getSupplierId() == supplierId) {
                
               ParcelDTO parcelDTO = new ParcelDTO( p.getParcelId(), 
                                                    p.getAwaitingCollectionDate(),
                                                    p.getCollectionDate(),
                                                    p.getDeliveryDate(),
                                                    p.getAwaitingReturnCollectionDate(),
                                                    p.getCollectionReturnDate(), 
                                                    p.getReturnDeliveryDate() );
            
                CustomerDTO custDTO = new CustomerDTO(p.getCustomerId().getCustomerId(),
                                                       p.getCustomerId().getCustName(),
                                                       p.getCustomerId().getCustPwd(),
                                                       p.getCustomerId().getCustAddress(),
                                                       p.getCustomerId().getCustContact());

                parcelDTO.setCustomerDTO(custDTO);

                DriverDTO drivDTO = new DriverDTO(p.getDriverId().getDriverId(),
                                                  p.getDriverId().getDrivName(),
                                                  p.getDriverId().getDrivPwd(),
                                                  p.getDriverId().getDrivAddress(),
                                                  p.getDriverId().getDrivCar(),
                                                  p.getDriverId().getDrivContact());

               parcelDTO.setDriverDTO(drivDTO);

               SupplierDTO suppDTO = new SupplierDTO(p.getSupplierId().getSupplierId(),
                                                     p.getSupplierId().getSuppName(),
                                                     p.getSupplierId().getSuppPwd(),
                                                     p.getSupplierId().getSuppAddress(),
                                                     p.getSupplierId().getSuppContact());
               parcelDTO.setSupplierDTO(suppDTO);

               StatusDTO statusDTO = new StatusDTO(p.getStatusId().getStatusId(),
                                                   p.getStatusId().getStatusName());

               parcelDTO.setStatusDTO(statusDTO); 

               parcelList.add(parcelDTO); 
           }                    
        }        
        return parcelList;
    }
   
    @Override
    public Collection<ParcelDTO> findMyParcels(int customerId)
    {
        Query q = em.createNamedQuery("Parcel.findAll");        
        List<Parcel> list = q.getResultList();
        Collection<ParcelDTO> parcelList = new ArrayList<>(list.size());
        for (Parcel p : list) {
            
            if(p.getCustomerId().getCustomerId() == customerId) {
                
               ParcelDTO parcelDTO = new ParcelDTO(p.getParcelId(), 
                                                    p.getAwaitingCollectionDate(),
                                                    p.getCollectionDate(),
                                                    p.getDeliveryDate(),
                                                    p.getAwaitingReturnCollectionDate(),
                                                    p.getCollectionReturnDate(), 
                                                    p.getReturnDeliveryDate() );
            
                CustomerDTO custDTO = new CustomerDTO(p.getCustomerId().getCustomerId(),
                                                      p.getCustomerId().getCustName(),
                                                      p.getCustomerId().getCustPwd(),
                                                      p.getCustomerId().getCustAddress(),
                                                      p.getCustomerId().getCustContact());

                parcelDTO.setCustomerDTO(custDTO);

                DriverDTO drivDTO = new DriverDTO(p.getDriverId().getDriverId(),
                                                  p.getDriverId().getDrivName(),
                                                  p.getDriverId().getDrivPwd(),
                                                  p.getDriverId().getDrivAddress(),
                                                  p.getDriverId().getDrivCar(),
                                                  p.getDriverId().getDrivContact());

               parcelDTO.setDriverDTO(drivDTO);

               SupplierDTO suppDTO = new SupplierDTO(p.getSupplierId().getSupplierId(),
                                                     p.getSupplierId().getSuppName(),
                                                     p.getSupplierId().getSuppPwd(),
                                                     p.getSupplierId().getSuppAddress(),
                                                     p.getSupplierId().getSuppContact());
               parcelDTO.setSupplierDTO(suppDTO);

               StatusDTO statusDTO = new StatusDTO(p.getStatusId().getStatusId(),
                                                   p.getStatusId().getStatusName());

               parcelDTO.setStatusDTO(statusDTO); 

               parcelList.add(parcelDTO); 
           }                    
        }        
        return parcelList;
    }
    
    @Override
    public Collection<ParcelDTO> findMyDeliveredParcels(int customerId)
    {
        Query q = em.createNamedQuery("Parcel.findAll");        
        List<Parcel> list = q.getResultList();
        Collection<ParcelDTO> parcelList = new ArrayList<>(list.size());
        for (Parcel p : list) {
            
            if(p.getCustomerId().getCustomerId() == customerId && p.getDeliveryDate() != null) {
                
               ParcelDTO parcelDTO = new ParcelDTO(p.getParcelId(), 
                                                    p.getAwaitingCollectionDate(),
                                                    p.getCollectionDate(),
                                                    p.getDeliveryDate(),
                                                    p.getAwaitingReturnCollectionDate(),
                                                    p.getCollectionReturnDate(), 
                                                    p.getReturnDeliveryDate() );
            
                CustomerDTO custDTO = new CustomerDTO(p.getCustomerId().getCustomerId(),
                                                      p.getCustomerId().getCustName(),
                                                      p.getCustomerId().getCustPwd(),
                                                      p.getCustomerId().getCustAddress(),
                                                      p.getCustomerId().getCustContact());

                parcelDTO.setCustomerDTO(custDTO);

                DriverDTO drivDTO = new DriverDTO(p.getDriverId().getDriverId(),
                                                  p.getDriverId().getDrivName(),
                                                  p.getDriverId().getDrivPwd(),
                                                  p.getDriverId().getDrivAddress(),
                                                  p.getDriverId().getDrivCar(),
                                                  p.getDriverId().getDrivContact());

               parcelDTO.setDriverDTO(drivDTO);

               SupplierDTO suppDTO = new SupplierDTO(p.getSupplierId().getSupplierId(),
                                                     p.getSupplierId().getSuppName(),
                                                     p.getSupplierId().getSuppPwd(),
                                                     p.getSupplierId().getSuppAddress(),
                                                     p.getSupplierId().getSuppContact());
               parcelDTO.setSupplierDTO(suppDTO);

               StatusDTO statusDTO = new StatusDTO(p.getStatusId().getStatusId(),
                                                   p.getStatusId().getStatusName());

               parcelDTO.setStatusDTO(statusDTO); 

               parcelList.add(parcelDTO); 
           }                    
        }        
        return parcelList;
    }

    @Override
    public Collection<ParcelDTO> findParcels(int driverId)
    {
        Query q = em.createNamedQuery("Parcel.findAll");        
        List<Parcel> list = q.getResultList();
        Collection<ParcelDTO> parcelList = new ArrayList<>(list.size());
        for (Parcel p : list) {
            
            if(p.getDriverId().getDriverId() == driverId) {
                
               ParcelDTO parcelDTO = new ParcelDTO( p.getParcelId(), 
                                                    p.getAwaitingCollectionDate(),
                                                    p.getCollectionDate(),
                                                    p.getDeliveryDate(),
                                                    p.getAwaitingReturnCollectionDate(),
                                                    p.getCollectionReturnDate(), 
                                                    p.getReturnDeliveryDate() );
            
                CustomerDTO custDTO = new CustomerDTO(p.getCustomerId().getCustomerId(),
                                                      p.getCustomerId().getCustName(),
                                                      p.getCustomerId().getCustPwd(),
                                                      p.getCustomerId().getCustAddress(),
                                                      p.getCustomerId().getCustContact());

                parcelDTO.setCustomerDTO(custDTO);

                DriverDTO drivDTO = new DriverDTO(p.getDriverId().getDriverId(),
                                                  p.getDriverId().getDrivName(),
                                                  p.getDriverId().getDrivPwd(),
                                                  p.getDriverId().getDrivAddress(),
                                                  p.getDriverId().getDrivCar(),
                                                  p.getDriverId().getDrivContact());

               parcelDTO.setDriverDTO(drivDTO);

               SupplierDTO suppDTO = new SupplierDTO(p.getSupplierId().getSupplierId(),
                                                     p.getSupplierId().getSuppName(),
                                                     p.getSupplierId().getSuppPwd(),
                                                     p.getSupplierId().getSuppAddress(),
                                                     p.getSupplierId().getSuppContact());
               parcelDTO.setSupplierDTO(suppDTO);

               StatusDTO statusDTO = new StatusDTO(p.getStatusId().getStatusId(),
                                                   p.getStatusId().getStatusName());

               parcelDTO.setStatusDTO(statusDTO); 

               parcelList.add(parcelDTO); 
           }                    
        }        
        return parcelList;
    }
    
    @Override
    public Collection<ParcelDTO> findAllParcels()
    {
        Query q = em.createNamedQuery("Parcel.findAll");        
        List<Parcel> list = q.getResultList();
        Collection<ParcelDTO> parcelList = new ArrayList<>(list.size());
        for (Parcel p : list) {            
                
               ParcelDTO parcelDTO = new ParcelDTO( p.getParcelId(), 
                                                    p.getAwaitingCollectionDate(),
                                                    p.getCollectionDate(),
                                                    p.getDeliveryDate(),
                                                    p.getAwaitingReturnCollectionDate(),
                                                    p.getCollectionReturnDate(), 
                                                    p.getReturnDeliveryDate() );
            
                CustomerDTO custDTO = new CustomerDTO(p.getCustomerId().getCustomerId(),
                                                      p.getCustomerId().getCustName(),
                                                      p.getCustomerId().getCustPwd(),
                                                      p.getCustomerId().getCustAddress(),
                                                      p.getCustomerId().getCustContact());

                parcelDTO.setCustomerDTO(custDTO);

                DriverDTO drivDTO = new DriverDTO(p.getDriverId().getDriverId(),
                                                  p.getDriverId().getDrivName(),
                                                  p.getDriverId().getDrivPwd(),
                                                  p.getDriverId().getDrivAddress(),
                                                  p.getDriverId().getDrivCar(),
                                                  p.getDriverId().getDrivContact());

               parcelDTO.setDriverDTO(drivDTO);

               SupplierDTO suppDTO = new SupplierDTO(p.getSupplierId().getSupplierId(),
                                                     p.getSupplierId().getSuppName(),
                                                     p.getSupplierId().getSuppPwd(),
                                                     p.getSupplierId().getSuppAddress(),
                                                     p.getSupplierId().getSuppContact());
               parcelDTO.setSupplierDTO(suppDTO);

               StatusDTO statusDTO = new StatusDTO(p.getStatusId().getStatusId(),
                                                   p.getStatusId().getStatusName());

               parcelDTO.setStatusDTO(statusDTO); 

               parcelList.add(parcelDTO);                               
        }        
        return parcelList;
    }
    
    
    
    @Override
    public void createParcelReturn(int parcelId, Timestamp date, int statusId)
    {
        try { 
            
             Parcel parcel = em.find(Parcel.class, parcelId);             
             
             parcel.setAwaitingReturnCollectionDate(date);
             
             Query sq = em.createNamedQuery("Status.findByStatusId");
             sq.setParameter("statusId", statusId);
             Status stat = (Status)sq.getSingleResult();
             
             parcel.setStatusId(stat);             
            
        }
        catch (Exception e)
        {
            throw new EJBException("Problem booking parcel return", e);
        }        
    }
    
    @Override
    public StatusDTO bookParcelReturnRestfull(int parcelId, int statusId)
    {
        try {
             Timestamp date = new Timestamp(new Date().getTime());
            
             Parcel parcel = em.find(Parcel.class, parcelId);             
             
             parcel.setAwaitingReturnCollectionDate(date);
             
             Query sq = em.createNamedQuery("Status.findByStatusId");
             sq.setParameter("statusId", statusId);
             Status stat = (Status)sq.getSingleResult();
             
             parcel.setStatusId(stat);

             StatusDTO statusDTO = new StatusDTO(stat.getStatusId(),
                                                 stat.getStatusName());             
             return statusDTO;
        }
        catch (Exception e)
        {
            throw new EJBException("Problem booking parcel return", e);
        }                
    }
    
    @Override
    public StatusDTO updateParcelStatusRestfull(int parcelId, int statusId)
    {
        try {
             Timestamp date = new Timestamp(new Date().getTime());
            
             Parcel parcel = em.find(Parcel.class, parcelId);             
             
            if(statusId == 2) {
               parcel.setCollectionDate(date);
            } 
            else if (statusId == 3) {
                parcel.setDeliveryDate(date);
            }
            else if (statusId == 5) {
                parcel.setCollectionReturnDate(date);
            }
            else {
                parcel.setReturnDeliveryDate(date);
            }            
             
             Query sq = em.createNamedQuery("Status.findByStatusId");
             sq.setParameter("statusId", statusId);
             Status stat = (Status)sq.getSingleResult();
             
             parcel.setStatusId(stat);

             StatusDTO statusDTO = new StatusDTO(stat.getStatusId(),
                                                 stat.getStatusName());             
             return statusDTO;
        }
        catch (Exception e)
        {
            throw new EJBException("Problem booking parcel return", e);
        }                
    }
    
    @Override
    public void updateParcelStatus(int parcelId, Timestamp Date, int statusId)
    {
        try {     
            
             Parcel parcel = em.find(Parcel.class, parcelId);
             
            if(statusId == 2) {
               parcel.setCollectionDate(Date);
            } 
            else if (statusId == 3) {
                parcel.setDeliveryDate(Date);
            }
            else if (statusId == 5) {
                parcel.setCollectionReturnDate(Date);
            }
            else {
                parcel.setReturnDeliveryDate(Date);
            }             
             Query sq = em.createNamedQuery("Status.findByStatusId");
             sq.setParameter("statusId", statusId);
             Status stat = (Status)sq.getSingleResult();
             
             parcel.setStatusId(stat);            
        }
        catch (Exception e)
        {
            throw new EJBException("Problem updating parcel status", e);
        }        
    }
}
