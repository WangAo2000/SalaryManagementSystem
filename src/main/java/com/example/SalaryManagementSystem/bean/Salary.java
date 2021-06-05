package com.example.SalaryManagementSystem.bean;

public class Salary {
    private String userId;
    private String name;
    private float basicSalary;
    private float bonus;
    private float fine;
    private float tax;
    private float total;
    private String date;

    public Salary() {
    }

    public Salary(String userId, String name, float basicSalary, float bonus, float fine, String date) {
        this.userId = userId;
        this.name = name;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.fine = fine;
        this.tax = (float) ((basicSalary+bonus) * 0.1);
        this.total = basicSalary +bonus-fine-this.tax;
        this.date = date;
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

    public float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", basicSalary=" + basicSalary +
                ", bonus=" + bonus +
                ", fine=" + fine +
                ", tax=" + tax +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
