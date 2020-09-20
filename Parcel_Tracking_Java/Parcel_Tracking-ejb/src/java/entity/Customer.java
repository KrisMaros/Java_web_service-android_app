/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Krzychu
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findByCustomerId", query = "SELECT c FROM Customer c WHERE c.customerId = :customerId")
    , @NamedQuery(name = "Customer.findByCustName", query = "SELECT c FROM Customer c WHERE c.custName = :custName")
    , @NamedQuery(name = "Customer.findByCustPwd", query = "SELECT c FROM Customer c WHERE c.custPwd = :custPwd")
    , @NamedQuery(name = "Customer.findByCustAddress", query = "SELECT c FROM Customer c WHERE c.custAddress = :custAddress")
    , @NamedQuery(name = "Customer.findByCustContact", query = "SELECT c FROM Customer c WHERE c.custContact = :custContact")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
    @Column(name = "CUST_NAME")
    private String custName;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
    @Column(name = "CUST_PWD")
    private String custPwd;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
    @Column(name = "CUST_ADDRESS")
    private String custAddress;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "CUST_CONTACT")
    private int custContact;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Parcel> parcelCollection;

    public Customer() {
    }

    public Customer(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer(Integer customerId, String custName, String custPwd, String custAddress, int custContact) {
        this.customerId = customerId;
        this.custName = custName;
        this.custPwd = custPwd;
        this.custAddress = custAddress;
        this.custContact = custContact;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public int getCustContact() {
        return custContact;
    }

    public void setCustContact(int custContact) {
        this.custContact = custContact;
    }

    @XmlTransient
    public Collection<Parcel> getParcelCollection() {
        return parcelCollection;
    }

    public void setParcelCollection(Collection<Parcel> parcelCollection) {
        this.parcelCollection = parcelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ customerId=" + customerId + " ]";
    }
    
}
