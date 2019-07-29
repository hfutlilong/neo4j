package com.example.biglong.entity;

import java.util.Date;

/**
 * @Description TODO
 * @Author lilong
 * @Date 2019-04-25 21:22
 */
public class User {
    private Long uid;
    private String name;
    private Integer age;
    private Date birthday;
    private Double salary;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
