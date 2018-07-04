package com.musicweb.hbobject.test;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by WJY on 2018/4/6.
 */
@Entity
public class Student implements Serializable {
    private int studenid;
    private String name;
    private int age;
    private Course course;
   private Set<Stuandcou> stuandcous;

    public Student() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getStudenid() {
        return studenid;
    }

    public void setStudenid(int id) {
        this.studenid = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //@OneToMany(mappedBy = "student",cascade=CascadeType.ALL)
    @OneToMany(cascade=CascadeType.ALL)
   @JoinColumn(name = "stuid")
   public Set<Stuandcou> getStuandcous() {
        return stuandcous;
    }

    public void setStuandcous(Set<Stuandcou> stuandcous) {
        this.stuandcous = stuandcous;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cou_id",unique = true)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
