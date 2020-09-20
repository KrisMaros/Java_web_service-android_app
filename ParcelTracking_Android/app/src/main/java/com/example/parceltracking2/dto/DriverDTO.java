package com.example.parceltracking2.dto;

public class DriverDTO {

    private int driverId;
    private String drivName;
    private String drivPwd;
    private String drivAddress;
    private String drivCar;
    private int drivContact;
    private boolean credentialsOk;

    public DriverDTO() {
    }

    public DriverDTO(String drivName, String drivPwd) {
        this.drivName = drivName;
        this.drivPwd = drivPwd;
    }

    public boolean isCredentialsOk() {
        return credentialsOk;
    }

    public void setCredentialsOk(boolean credentialsOk) {
        this.credentialsOk = credentialsOk;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
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
}
