package com.example.SalaryManagementSystem.bean;

public class User {

    private String userId;
    private String name;
    private String password;
    private String dId;
    private String profession;
    private String idNumber;
    private String bankId;

    public User() {
    }

    public User(String userId, String name, String password, String dId, String profession, String idNumber, String bankId) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.dId = dId;
        this.profession = profession;
        this.idNumber = idNumber;
        this.bankId = bankId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dId='" + dId + '\'' +
                ", profession='" + profession + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", bankId='" + bankId + '\'' +
                '}';
    }
}
