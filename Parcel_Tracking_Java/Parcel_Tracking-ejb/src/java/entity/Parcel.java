/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Krzychu
 */
@Entity
@Table(name = "PARCEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcel.findAll", query = "SELECT p FROM Parcel p")
    , @NamedQuery(name = "Parcel.findByParcelId", query = "SELECT p FROM Parcel p WHERE p.parcelId = :parcelId")
    , @NamedQuery(name = "Parcel.findByAwaitingCollectionDate", query = "SELECT p FROM Parcel p WHERE p.awaitingCollectionDate = :awaitingCollectionDate")
    , @NamedQuery(name = "Parcel.findByCollectionDate", query = "SELECT p FROM Parcel p WHERE p.collectionDate = :collectionDate")
    , @NamedQuery(name = "Parcel.findByDeliveryDate", query = "SELECT p FROM Parcel p WHERE p.deliveryDate = :deliveryDate")
    , @NamedQuery(name = "Parcel.findByAwaitingReturnCollectionDate", query = "SELECT p FROM Parcel p WHERE p.awaitingReturnCollectionDate = :awaitingReturnCollectionDate")
    , @NamedQuery(name = "Parcel.findByCollectionReturnDate", query = "SELECT p FROM Parcel p WHERE p.collectionReturnDate = :collectionReturnDate")
    , @NamedQuery(name = "Parcel.findByReturnDeliveryDate", query = "SELECT p FROM Parcel p WHERE p.returnDeliveryDate = :returnDeliveryDate")})
public class Parcel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PARCEL_ID")
    private Integer parcelId;
    @Column(name = "AWAITING_COLLECTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date awaitingCollectionDate;
    @Column(name = "COLLECTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date collectionDate;
    @Column(name = "DELIVERY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Column(name = "AWAITING_RETURN_COLLECTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date awaitingReturnCollectionDate;
    @Column(name = "COLLECTION_RETURN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date collectionReturnDate;
    @Column(name = "RETURN_DELIVERY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDeliveryDate;
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "DRIVER_ID", referencedColumnName = "DRIVER_ID")
    @ManyToOne(optional = false)
    private Driver driverId;
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusId;
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    @ManyToOne(optional = false)
    private Supplier supplierId;

    public Parcel() {
    }

    public Parcel(Integer parcelId) {
        this.parcelId = parcelId;
    }

    public Integer getParcelId() {
        return parcelId;
    }

    public void setParcelId(Integer parcelId) {
        this.parcelId = parcelId;
    }

    public Date getAwaitingCollectionDate() {
        return awaitingCollectionDate;
    }

    public void setAwaitingCollectionDate(Date awaitingCollectionDate) {
        this.awaitingCollectionDate = awaitingCollectionDate;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getAwaitingReturnCollectionDate() {
        return awaitingReturnCollectionDate;
    }

    public void setAwaitingReturnCollectionDate(Date awaitingReturnCollectionDate) {
        this.awaitingReturnCollectionDate = awaitingReturnCollectionDate;
    }

    public Date getCollectionReturnDate() {
        return collectionReturnDate;
    }

    public void setCollectionReturnDate(Date collectionReturnDate) {
        this.collectionReturnDate = collectionReturnDate;
    }

    public Date getReturnDeliveryDate() {
        return returnDeliveryDate;
    }

    public void setReturnDeliveryDate(Date returnDeliveryDate) {
        this.returnDeliveryDate = returnDeliveryDate;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Driver getDriverId() {
        return driverId;
    }

    public void setDriverId(Driver driverId) {
        this.driverId = driverId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parcelId != null ? parcelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcel)) {
            return false;
        }
        Parcel other = (Parcel) object;
        if ((this.parcelId == null && other.parcelId != null) || (this.parcelId != null && !this.parcelId.equals(other.parcelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Parcel[ parcelId=" + parcelId + " ]";
    }
    
}
