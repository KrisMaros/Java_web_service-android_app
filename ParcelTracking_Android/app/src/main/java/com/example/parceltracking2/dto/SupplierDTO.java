package com.example.parceltracking2.dto;

public class SupplierDTO {

    private int supplierId;
    private String suppName;
    private String suppPwd;
    private String suppAddress;
    private int suppContact;

    public SupplierDTO() {
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
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
}
