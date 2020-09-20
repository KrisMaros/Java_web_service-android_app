package com.example.parceltracking2.dto;

import java.util.Date;

public class ParcelDTO {

    private int parcelId;
    private Date awaiting_collection_date;
    private Date collection_date;
    private Date delivery_date;
    private Date awaiting_return_collection_date;
    private Date collection_return_date;
    private Date return_delivery_date;
    private CustomerDTO customerDTO;
    private DriverDTO driverDTO ;
    private StatusDTO statusDTO;
    private SupplierDTO supplierDTO;

    public ParcelDTO() {
    }

    public ParcelDTO(int parcelId, StatusDTO statusDTO) {
        this.parcelId = parcelId;
        this.statusDTO = statusDTO;
    }

    public int getParcelId() {
        return parcelId;
    }

    public void setParcelId(int parcelId) {
        this.parcelId = parcelId;
    }

    public Date getAwaiting_collection_date() {
        return awaiting_collection_date;
    }

    public void setAwaiting_collection_date(Date awaiting_collection_date) {
        this.awaiting_collection_date = awaiting_collection_date;
    }

    public Date getCollection_date() {
        return collection_date;
    }

    public void setCollection_date(Date collection_date) {
        this.collection_date = collection_date;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Date getAwaiting_return_collection_date() {
        return awaiting_return_collection_date;
    }

    public void setAwaiting_return_collection_date(Date awaiting_return_collection_date) {
        this.awaiting_return_collection_date = awaiting_return_collection_date;
    }

    public Date getCollection_return_date() {
        return collection_return_date;
    }

    public void setCollection_return_date(Date collection_return_date) {
        this.collection_return_date = collection_return_date;
    }

    public Date getReturn_delivery_date() {
        return return_delivery_date;
    }

    public void setReturn_delivery_date(Date return_delivery_date) {
        this.return_delivery_date = return_delivery_date;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public DriverDTO getDriverDTO() {
        return driverDTO;
    }

    public void setDriverDTO(DriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }

    public StatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }

    public SupplierDTO getSupplierDTO() {
        return supplierDTO;
    }

    public void setSupplierDTO(SupplierDTO supplierDTO) {
        this.supplierDTO = supplierDTO;
    }
}
