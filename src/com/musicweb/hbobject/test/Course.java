package com.musicweb.hbobject.test;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by WJY on 2018/4/6.
 */
@Entity
public class Course  implements Serializable{
    private int courseid;
    private  String name;
    //private Set<Stuandcou> stuandcous;
   // private Student student;

    public Course() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="stu_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }*/

    // @OneToMany(mappedBy = "course",cascade=CascadeType.ALL)
  /* @OneToMany(cascade=CascadeType.ALL)
   @JoinColumn(name = "couid")
    public Set<Stuandcou> getStuandcous() {
        return stuandcous;
    }

    public void setStuandcous(Set<Stuandcou> stuandcous) {
        this.stuandcous = stuandcous;
    }*/
}
