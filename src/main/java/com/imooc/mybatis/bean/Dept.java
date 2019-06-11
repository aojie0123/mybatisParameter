package com.imooc.mybatis.bean;

import java.util.List;

public class Dept {
    private Integer id;
    private String departmentName;
    private List<Person> emps;

    public Dept() {
    }

    public Dept(Integer id) {
        this.id = id;
    }

    public Dept(Integer id, String departmentName, List<Person> emps) {
        this.id = id;
        this.departmentName = departmentName;
        this.emps = emps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Person> getEmps() {
        return emps;
    }

    public void setEmps(List<Person> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", emps=" + emps +
                '}';
    }
}
