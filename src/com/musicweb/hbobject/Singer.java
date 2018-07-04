package com.musicweb.hbobject;

import javax.persistence.*;

/**
 * Created by WJY on 2018/5/5.
 */
@Entity
public class Singer {

    private int singerId;
    private String name;
    private String iamge;
    private String introdution;

    public Singer() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    @Column(length = 2000)
    public String getIntrodution() {
        return introdution;
    }

    public void setIntrodution(String introdution) {
        this.introdution = introdution;
    }

    public String formatIntrodution()
    {
        String formatIntrodution=introdution.replace("\r\n","<br>");
        return formatIntrodution;
    }
}
