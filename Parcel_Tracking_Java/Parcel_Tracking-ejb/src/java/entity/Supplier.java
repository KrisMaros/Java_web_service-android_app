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
@Table(name = "SUPPLIER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s")
    , @NamedQuery(name = "Supplier.findBySupplierId", query = "SELECT s FROM Supplier s WHERE s.supplierId = :supplierId")
    , @NamedQuery(name = "Supplier.findBySuppName", query = "SELECT s FROM Supplier s WHERE s.suppName = :suppName")
    , @NamedQuery(name = "Supplier.findBySuppPwd", query = "SELECT s FROM Supplier s WHERE s.suppPwd = :suppPwd")
    , @NamedQuery(name = "Supplier.findBySuppAddress", query = "SELECT s FROM Supplier s WHERE s.suppAddress = :suppAddress")
    , @NamedQuery(name = "Supplier.findBySuppContact", query = "SELECT s FROM Supplier s WHERE s.suppContact = :suppContact")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUPPLIER_ID")
    private Integer supplierId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SUPP_NAME")
    private String suppName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SUPP_PWD")
    private String suppPwd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SUPP_ADDRESS")
    private String suppAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUPP_CONTACT")
    private int suppContact;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierId")
    private Collection<Parcel> parcelCollection;

    public Supplier() {
    }

    public Supplier(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier(Integer supplierId, String suppName, String suppPwd, String suppAddress, int suppContact) {
        this.supplierId = supplierId;
        this.suppName = suppName;
        this.suppPwd = suppPwd;
        this.suppAddress = suppAddress;
        this.suppContact = suppContact;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getSuppPwd() {
        return suppPwd;
    }

    public void setSuppPwd(String suppPwd) {
        this.suppPwd = suppPwd;
    }

    public String getSuppAddress() {
        return suppAddress;
    }

    public void setSuppAddress(String suppAddress) {
        this.suppAddress = suppAddress;
    }

    public int getSuppContact() {
        return suppContact;
    }

    public void setSuppContact(int suppContact) {
        this.suppContact = suppContact;
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
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Supplier[ supplierId=" + supplierId + " ]";
    }
    
}
