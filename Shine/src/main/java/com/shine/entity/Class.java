package com.shine.entity;

import java.util.List;

public class Class {
    private Integer cid;

    private String cname;

    private Teacher teacher;

   private List<Stu> students;
//    private String teacherId;
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Stu> getStudents() {
        return students;
    }

    public void setStudents(List<Stu> students) {
        this.students = students;
    }

    //    public String getTeacherId() {
//        return teacherId;
//    }
//
//    public void setTeacherId(String teacherId) {
//        this.teacherId = teacherId;
//    }


    @Override
    public String toString() {
        return "Class{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", teacher=" + teacher +
                ", students=" + students +
                '}';
    }
}