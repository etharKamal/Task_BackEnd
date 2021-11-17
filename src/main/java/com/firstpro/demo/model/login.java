package com.firstpro.demo.model;
import javax.persistence.*;


@Entity
public class login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  Id;
    private String UserName;
    private String password;
    private boolean isActive;
    private boolean isAdmin;
   /* private Date createdOn ;
    private Date lastLogin;
    private String resetToken;*/
    public  Integer getId() {
        return Id;
    }
    public void setId(Integer id) { this.Id = Id;}

    public  String getUserName() {
        return UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return "null";
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
/*
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }*/
}
