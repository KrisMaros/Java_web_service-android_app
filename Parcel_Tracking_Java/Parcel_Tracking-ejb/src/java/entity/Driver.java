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
@Table(name = "DRIVER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d")
    , @NamedQuery(name = "Driver.findByDriverId", query = "SELECT d FROM Driver d WHERE d.driverId = :driverId")
    , @NamedQuery(name = "Driver.findByDrivName", query = "SELECT d FROM Driver d WHERE d.drivName = :drivName")
    , @NamedQuery(name = "Driver.findByDrivPwd", query = "SELECT d FROM Driver d WHERE d.drivPwd = :drivPwd")
    , @NamedQuery(name = "Driver.findByDrivAddress", query = "SELECT d FROM Driver d WHERE d.drivAddress = :drivAddress")
    , @NamedQuery(name = "Driver.findByDrivCar", query = "SELECT d FROM Driver d WHERE d.drivCar = :drivCar")
    , @NamedQuery(name = "Driver.findByDrivContact", query = "SELECT d FROM Driver d WHERE d.drivContact = :drivContact")})
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DRIVER_ID")
    private Integer driverId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DRIV_NAME")
    private String drivName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DRIV_PWD")
    private String drivPwd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DRIV_ADDRESS")
    private String drivAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DRIV_CAR")
    private String drivCar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DRIV_CONTACT")
    private int drivContact;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driverId")
    private Collection<Parcel> parcelCollection;

    public Driver() {
    }

    public Driver(Integer driverId) {
        this.driverId = driverId;
    }

    public Driver(Integer driverId, String drivName, String drivPwd, String drivAddress, String drivCar, int drivContact) {
        this.driverId = driverId;
        this.drivName = drivName;
        this.drivPwd = drivPwd;
        this.drivAddress = drivAddress;
        this.drivCar = drivCar;
        this.drivContact = drivContact;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDrivName() {
        return drivName;
    }

    public void setDrivName(String drivName) {
        this.drivName = drivName;
    }

    public String getDrivPwd() {
        return drivPwd;
    }

    public void setDrivPwd(String drivPwd) {
        this.drivPwd = drivPwd;
    }

    public String getDrivAddress() {
        return drivAddress;
    }

    public void setDrivAddress(String drivAddress) {
        this.drivAddress = drivAddress;
    }

    public String getDrivCar() {
        return drivCar;
    }

    public void setDrivCar(String drivCar) {
        this.drivCar = drivCar;
    }

    public int getDrivContact() {
        return drivContact;
    }

    public void setDrivContact(int drivContact) {
        this.drivContact = drivContact;
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
        hash += (driverId != null ? driverId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) object;
        if ((this.driverId == null && other.driverId != null) || (this.driverId != null && !this.driverId.equals(other.driverId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Driver[ driverId=" + driverId + " ]";
    }
    
}
