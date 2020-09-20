package com.example.parceltracking2.dto;

public class CustomerDTO {

    private int custId;
    private String custName;
    private String custPwd;
    private String custAddress;
    private int custTelNum;
    private boolean credentialsOk;

    public CustomerDTO() {
    }

    public CustomerDTO(int custId, String custName, String custPwd, String custAddress, int custTelNum) {
        this.custId = custId;
        this.custName = custName;
        this.custPwd = custPwd;
        this.custAddress = custAddress;
        this.custTelNum = custTelNum;
    }

//    public CustomerDTO(String custName, String custPwd) {
//        this.custName = custName;
//        this.custPwd = custPwd;
//    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
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

    public int getCustTelNum() {
        return custTelNum;
    }

    public void setCustTelNum(int custTelNum) {
        this.custTelNum = custTelNum;
    }

    public boolean isCredentialsOk() {
        return credentialsOk;
    }

    public void setCredentialsOk(boolean credentialsOk) {
        this.credentialsOk = credentialsOk;
    }
}
