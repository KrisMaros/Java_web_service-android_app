package com.example.parceltracking2.dto;

public class StatusDTO {

    private int statusId;
    private String statusName;

    public StatusDTO() {
    }

    public StatusDTO(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
