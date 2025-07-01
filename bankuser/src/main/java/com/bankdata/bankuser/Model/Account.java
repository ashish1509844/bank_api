package com.bankdata.bankuser.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acNo;
    @NotBlank(message = "account holder name is required")
    private String name;
    @NotBlank(message = "account type is required")
    private String acType;
    @Positive(message = "total amount is required")
    private double tAmt;

    public Account(Long acNo, String name, String acType, double tAmt) {
        this.acNo = acNo;
        this.name = name;
        this.acType = acType;
        this.tAmt = tAmt;
    }

    public Account() {

    }

    public Long getAcNo() {
        return acNo;
    }

    public void setAcNo(Long acNo) {
        this.acNo = acNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public double gettAmt() {
        return tAmt;
    }

    public void settAmt(double tAmt) {
        this.tAmt = tAmt;
    }

    public String toString() {
        return "Account [acNo=" + acNo + ", name=" + name + ", acType=" + acType + ", tAmt=" + tAmt + "]";
    }

}
