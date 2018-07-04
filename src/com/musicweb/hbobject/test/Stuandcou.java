package com.musicweb.hbobject.test;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WJY on 2018/4/6.
 */
@Entity
public class Stuandcou implements Serializable {
   //private int stuandcouid;
   private Course course;
   private Student student;
   // private int grade;


    public Stuandcou() {
    }
/*
 @Id
 @GeneratedValue(strategy =GenerationType.AUTO)
    public int getStuandcouid() {
        return stuandcouid;
    }

    public void setStuandcouid(int id) {
        this.stuandcouid = id;
    }

*/

    /*
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "course_id", unique = true)
    */
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Course_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    //@ManyToOne(cascade=CascadeType.ALL)
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


   /* @Column
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }*/
}
