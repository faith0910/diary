package com.shine.entity;/**
 * @author wuchunjie
 * @date 2020/6/19
 */

/**
 * @program:Shine
 * @description:
 * @author:Juwenchao
 * @date:2020-06-19 21:05:30
 */
public class Stu {

    private Integer sid;

    private String sname;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                '}';
    }
}
