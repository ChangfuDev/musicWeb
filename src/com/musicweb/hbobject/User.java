package com.musicweb.hbobject;

import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by WJY on 2018/3/29.
 */
@XmlRootElement(name = "User")
@Entity
public class User {
    private int userId;
    private String name;
    private String password;
    private boolean sex;
    private int age;
    private String image;
    private String introdution;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserId() {
        return userId;
    }

    @XmlElement
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column
    public String getName() {
        return name;
    }

    @Column
    public String getPassword() {
        return password;
    }
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    @Column
    public boolean isSex() {
        return sex;
    }
    @XmlElement
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    @Column
    public int getAge() {
        return age;
    }
    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }
    @Column
    public String getImage() {
        return image;
    }
    @XmlElement
    public void setImage(String image) {
        this.image = image;
    }
    @Column
    public String getIntrodution() {
        return introdution;
    }
    @XmlElement
    public void setIntrodution(String introdution) {
        this.introdution = introdution;
    }
}

