package com.musicweb.hbobject;

import javax.persistence.*;

/**
 * Created by WJY on 2018/4/27.
 */
@Entity
public class Admin {

    private int  adminId;

   private String adminName;

   private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Column
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
