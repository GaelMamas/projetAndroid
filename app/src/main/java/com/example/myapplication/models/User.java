package com.example.myapplication.models;

public class User {
    private String id;
    private int num;
    private int code;
    private String role;

    public User(int num, int code , String role)  {

    }
    public User() {
        this.id = id;
        this.num = num;
        this.code = code;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) { this.num = num; }
    public int getCode(){return code ;}
    public void setCode(int code){ this.code = code ;}

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}
