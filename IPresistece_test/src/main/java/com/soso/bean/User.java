package com.soso.bean;

public class User {

    private Integer id;
    private String username;

    @Override
    public String toString() {
        return "com.soso.bean.User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

