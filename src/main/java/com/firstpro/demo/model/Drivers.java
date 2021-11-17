package com.firstpro.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity (name = "drivers")
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  Id;
    @NotNull
    private String Driver_name;
    @NotNull
    private long Driver_id;
    @Min(10)
    private long PhoneNum;
    private long SimNum;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd")
    private LocalDate dateOfInsertion;

    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd")
    private LocalDate lastWithdrawal ;
    private boolean CardWithdrawn;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd")
    private LocalDate dateOfWithdrawal;

    public Drivers() {
    }

    public Integer getId() {
        return Id;
    }
    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getDriver_name() {
        return Driver_name;
    }
    public void setDriver_name(String Driver_name) {
        this.Driver_name = Driver_name;
    }

    public long getDriver_id() {
        return Driver_id;
    }
    public void setDriver_id(long Driver_id) {
        this.Driver_id = Driver_id;
    }


    public long getPhoneNum() {
        return PhoneNum;
    }
    public void setPhoneNum(long PhoneNum) {
        this.PhoneNum = PhoneNum;
    }



    public long getSimNum() {
        return SimNum;
    }
    public void setSimNum(long SimNum) {
        this.SimNum = SimNum;
    }


    public LocalDate getLastWithdrawal() {
        return lastWithdrawal;
    }

    public void setLastWithdrawal(LocalDate lastWithdrawal) {
        this.lastWithdrawal = lastWithdrawal;
    }

    public LocalDate getDateOfInsertion() {
        return dateOfInsertion;
    }

    public void setDateOfInsertion(LocalDate dateOfInsertion) {
        this.dateOfInsertion = dateOfInsertion;
    }


    public LocalDate getDateOfWithdrawal() {
        return dateOfWithdrawal;
    }

    public void setDateOfWithdrawal(LocalDate dateOfWithdrawal) {
        this.dateOfWithdrawal = dateOfWithdrawal;
    }


    public boolean getCardWithdrawn() {
        return CardWithdrawn;
    }

    public void setCardWithdrawn(boolean CardWithdrawn) {
        this.CardWithdrawn = CardWithdrawn;
    }
}
